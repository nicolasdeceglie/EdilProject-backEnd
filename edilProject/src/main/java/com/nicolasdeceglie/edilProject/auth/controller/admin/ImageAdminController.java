package com.nicolasdeceglie.edilProject.auth.controller.admin;

import com.nicolasdeceglie.edilProject.presentation.services.ImageService;
import com.nicolasdeceglie.edilProject.persistences.entitites.Image;
import com.nicolasdeceglie.edilProject.persistences.entitites.Project;
import com.nicolasdeceglie.edilProject.presentation.dtos.ImageDTO;
import com.nicolasdeceglie.edilProject.presentation.dtos.ProjectDTO;
import io.swagger.v3.oas.annotations.Hidden;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "http://localhost:3000")
public class ImageAdminController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("/images")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<ImageDTO> getImages(){
        return imageService.getAll()
                .stream().map(this::convertToDto)
                .toList();
    }
    @PutMapping("/update/image/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    @Hidden
    public ImageDTO update(@PathVariable long id, @RequestBody ImageDTO updateImage) {
        Image image = convertToEntity(updateImage);
        image = imageService.update(id,image);
        return convertToDto(image);
    }

    @PostMapping("/create/image")
    @PreAuthorize("hasAuthority('admin:create')")
    @Hidden
    public ImageDTO create(@RequestBody ImageDTO newImage) {
        Image image = convertToEntity(newImage);
        image = imageService.create(image);
        return convertToDto(image);
    }
    @DeleteMapping("/delete/image/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    @Hidden
    public ImageDTO deleteImage(@PathVariable long id) {
        return convertToDto(imageService.delete(id));
    }

    @GetMapping("/image/project/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ProjectDTO getProject(@PathVariable long id){
        Image image = imageService.getById(id);
        return convertToDto(image.getProject());
    }
    private Image convertToEntity(ImageDTO dto){
        return modelMapper.map(dto, Image.class);
    }
    private ImageDTO convertToDto(Image image){
        return modelMapper.map(image,ImageDTO.class);
    }
    private ProjectDTO convertToDto(Project project){
        return modelMapper.map(project, ProjectDTO.class);
    }
}
