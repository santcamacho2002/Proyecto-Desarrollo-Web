package com.example.proyecto.filtros;

import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;
import java.util.stream.Collectors;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";
    private final String SECRET = "secretKey";

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain){
        try{
            if(checkJWTToken(request,response)){
                Claims claims = validateToken(request);
                if(claims.get("authorities") != null){
                    setUpSpringAuthentication(claims);
                }
            }
            else {
                SecurityContextHolder.clearContext();
            }
            chain.doFilter(request,response);

        }catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }
    }

    private Claims validateToken(HttpServletRequest request){
        String jwtToken = request.getHeader(HEADER).replace(PREFIX,"");
        return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
    }

    private void setUpSpringAuthentication(Claims claims){
        @SuppressWarnings("unchecked")
        List<String> authorities = (List) claims.get("authorities");
        String role = (String) claims.get("role");

        if (role != null && !role.isEmpty()) {
            authorities.add(role);
        }

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                claims.getSubject(),
                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private boolean checkJWTToken(HttpServletRequest request, HttpServletResponse response){
        String authenticationHeader = request.getHeader(HEADER);
        if(authenticationHeader == null || !authenticationHeader.startsWith(PREFIX)){
            return false;
        }
        return true;
    }
}

