package com.nicolasdeceglie.edilProject.presentation.controllers;

import com.nicolasdeceglie.edilProject.persistences.entitites.Image;
import com.nicolasdeceglie.edilProject.persistences.entitites.Product;
import com.nicolasdeceglie.edilProject.presentation.dtos.ImageDTO;
import com.nicolasdeceglie.edilProject.presentation.dtos.ProductDTO;
import com.nicolasdeceglie.edilProject.presentation.dtos.ProjectDTO;
import com.nicolasdeceglie.edilProject.persistences.entitites.Project;
import com.nicolasdeceglie.edilProject.presentation.services.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/projects")
    public List<ProjectDTO> getProjects() {
        return projectService.getAll().stream()
                .map(this::convertToDto)
                .toList();
    }
    @GetMapping("/project/{id}")
    public ProjectDTO getProject(@PathVariable long id) {
        return convertToDto(projectService.getById(id));

    }
    @GetMapping("/project/images/{id}")
    public List<ImageDTO> getImages(@PathVariable long id){
        Project project = projectService.getById(id);
        return project.getImages().stream().map(this::convertToImageDTO).toList();
    }
    @GetMapping("/project/products/{id}")
    public List<ProductDTO> getProducts(@PathVariable long id){
        Project project = projectService.getById(id);
        return project.getProducts().stream().map(this::convertToProductDto).toList();
    }
    private ProjectDTO convertToDto(Project project){
        return modelMapper.map(project, ProjectDTO.class);
    }
    private ImageDTO convertToImageDTO(Image image){
        return modelMapper.map(image,ImageDTO.class);
    }
    private ProductDTO convertToProductDto(Product product){
        return modelMapper.map(product,ProductDTO.class);
    }
}
