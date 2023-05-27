package com.nicolasdeceglie.edilProject.auth.controller.admin;


import com.nicolasdeceglie.edilProject.persistences.entitites.Image;
import com.nicolasdeceglie.edilProject.persistences.entitites.Product;
import com.nicolasdeceglie.edilProject.presentation.dtos.ImageDTO;
import com.nicolasdeceglie.edilProject.presentation.dtos.ProductDTO;
import com.nicolasdeceglie.edilProject.presentation.dtos.ProjectDTO;
import com.nicolasdeceglie.edilProject.presentation.services.ImageService;
import com.nicolasdeceglie.edilProject.presentation.services.ProductService;
import com.nicolasdeceglie.edilProject.persistences.entitites.Project;
import com.nicolasdeceglie.edilProject.presentation.services.ProjectService;
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
public class ProjectAdminController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("/projects")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<ProjectDTO> get() {
        return projectService.getAll().stream()
                .map(this::convertToDto)
                .toList();
    }
    @GetMapping("/project/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ProjectDTO getById(@PathVariable long id) {
        return convertToDto(projectService.getById(id));
    }
    @PostMapping("/create/project")
    @PreAuthorize("hasAuthority('admin:create')")
    public ProjectDTO createProject (@RequestBody ProjectDTO newProject) {
        Project project = convertToEntity(newProject);
        project = projectService.create(project);

        return convertToDto(project);
    }
    @PutMapping("/update/project/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    @Hidden
    public ProjectDTO update(@PathVariable long id, @RequestBody ProjectDTO updateProject) {
        Project project = convertToEntity(updateProject);
        project = projectService.update(id, project);
        return convertToDto(project);
    }
    @DeleteMapping("/delete/project/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    @Hidden
    public ProjectDTO delete(@PathVariable long id) {
        Project project = projectService.getById(id);
        project.getImages().forEach(image -> imageService.delete(image.getId()));
        project.getProducts().forEach(product -> productService.delete(product.getId()));

        return convertToDto(projectService.delete(id));
    }
    @DeleteMapping("/delete/images/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    @Hidden
    public List<ImageDTO> deleteImages(@PathVariable long id) {
        Project project = projectService.getById(id);
        return project.getImages().stream()
                .map(image -> convertToDto(imageService.delete(image.getId()))).toList();
    }
    @DeleteMapping("/delete/products/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    @Hidden
    public List<ProductDTO> deleteProducts(@PathVariable long id) {
        Project project = projectService.getById(id);
        return project.getProducts().stream()
                .map(product -> convertToDto(productService.delete(product.getId()))).toList();
    }
    @GetMapping("/project/images/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<ImageDTO> getImages(@PathVariable long id){
        Project project = projectService.getById(id);
        return project.getImages().stream().map(this::convertToDto).toList();
    }
    @GetMapping("/project/products/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<ProductDTO> getProducts(@PathVariable long id){
        Project project = projectService.getById(id);
        return project.getProducts().stream().map(this::convertToDto).toList();
    }

    private ProjectDTO convertToDto(Project project){
        return modelMapper.map(project, ProjectDTO.class);
    }
    private Project convertToEntity(ProjectDTO dto){
        return modelMapper.map(dto,Project.class);
    }
    private Product convertToEntity(ProductDTO dto){
        return modelMapper.map(dto,Product.class);
    }

    private ImageDTO convertToDto(Image image){
        return modelMapper.map(image,ImageDTO.class);
    }
    private ProductDTO convertToDto(Product product){
        return modelMapper.map(product,ProductDTO.class);
    }
}