package com.example.proyecto.controller;


import com.example.proyecto.entities.Tool;
import com.example.proyecto.services.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/herramientas")
public class ToolController {

    @Autowired
    private ToolService toolService;

    @GetMapping(value = "/allTools",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Tool> toolList(){
        return toolService.getAllTools();
    }

    @GetMapping(value = "/tool/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public Tool getToolById(@PathVariable Long id){
        return toolService.getToolById(id);
    }

    @PostMapping(value = "/newTool",produces = {MediaType.APPLICATION_JSON_VALUE})
    public void createTool(@RequestBody Tool tool) {
        toolService.newTool(tool);
    }

    @PutMapping(value = "/updateTool/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public void updateTool(@PathVariable(value = "id") Long id, @RequestBody Tool herramienta) {
        toolService.updateTool(herramienta,id);}

    @DeleteMapping("/deleteTool/{id}")
    public void deleteTool(@PathVariable(value="id")Long id){
        toolService.deleteTool(id);
    }

}
