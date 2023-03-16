package com.example.proyecto.services;

import com.example.proyecto.entities.Tool;
import com.example.proyecto.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ToolService {

    @Autowired
    ToolRepository toolRepository;

    public List<Tool> getAllTools(){
        return toolRepository.findAll();
    }

    public Tool getToolById(Long id){
        return toolRepository.findById(id).orElse(null);
    }

    public void newTool(Tool tool){
        toolRepository.save(tool);
    }

    public void updateTool(Tool tool , Long id){
        Optional<Tool> herramientaExistente = toolRepository.findById(id);
        if (herramientaExistente.isPresent()) {
            Tool updatedTool = herramientaExistente.get();
            updatedTool.setNombre(tool.getNombre());
            updatedTool.setDescripcion(tool.getDescripcion());
            toolRepository.save(updatedTool);
        } else {
            System.out.println("No existe la herramienta");
        }
    }

    public void deleteTool(Long id){
        Optional<Tool> herramientaExistente = toolRepository.findById(id);
        if (herramientaExistente.isPresent()) {
            toolRepository.delete(herramientaExistente.get());
        } else {
            System.out.println("No existe la herramienta");
        }
    }

}

