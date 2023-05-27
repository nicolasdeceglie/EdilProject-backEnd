package com.nicolasdeceglie.edilProject.presentation.controllers;

import com.nicolasdeceglie.edilProject.persistences.entitites.Product;
import com.nicolasdeceglie.edilProject.persistences.entitites.Project;
import com.nicolasdeceglie.edilProject.presentation.dtos.ProductDTO;
import com.nicolasdeceglie.edilProject.presentation.dtos.ProjectDTO;
import com.nicolasdeceglie.edilProject.presentation.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("/products")
    public List<ProductDTO> getProducts(){
        return productService.getAll()
                .stream().map(this::convertToDto)
                .toList();
    }
    @GetMapping("/product/project/{id}")
    public ProjectDTO getProject(@PathVariable long id){
        Product product = productService.getById(id);
        return convertToDto(product.getProject());
    }

    private ProjectDTO convertToDto(Project project){
        return modelMapper.map(project, ProjectDTO.class);
    }
    private ProductDTO convertToDto(Product product){
        return modelMapper.map(product,ProductDTO.class);
    }
}
