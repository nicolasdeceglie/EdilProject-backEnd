package com.nicolasdeceglie.edilProject.presentation.controllers;

import com.nicolasdeceglie.edilProject.persistences.entitites.Image;
import com.nicolasdeceglie.edilProject.presentation.dtos.ImageDTO;
import com.nicolasdeceglie.edilProject.presentation.dtos.ProjectDTO;
import com.nicolasdeceglie.edilProject.presentation.services.ImageService;
import com.nicolasdeceglie.edilProject.persistences.entitites.Project;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class ImageController {

    @Autowired
    private ImageService imageService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("/images")
    public List<ImageDTO> getImages(){
        return imageService.getAll()
                .stream().map(this::convertToDto)
                .toList();
    }
    @GetMapping("/image/project/{id}")
    public ProjectDTO getProject(@PathVariable long id){
        Image image = imageService.getById(id);
        return convertToDto(image.getProject());
    }
    private ImageDTO convertToDto(Image image){
        return modelMapper.map(image,ImageDTO.class);
    }
    private ProjectDTO convertToDto(Project project){
        return modelMapper.map(project, ProjectDTO.class);
    }
}
