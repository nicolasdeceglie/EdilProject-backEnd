package com.nicolasdeceglie.edilProject.auth.controller.admin;

import com.nicolasdeceglie.edilProject.presentation.dtos.ProductDTO;
import com.nicolasdeceglie.edilProject.persistences.entitites.Product;
import com.nicolasdeceglie.edilProject.persistences.entitites.Project;
import com.nicolasdeceglie.edilProject.presentation.dtos.ProjectDTO;
import com.nicolasdeceglie.edilProject.presentation.services.ProductService;
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
public class ProductAdminController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("/products")
    @PreAuthorize("hasAuthority('admin:read')")
    @Hidden
    public List<ProductDTO> getProducts(){
        return productService.getAll()
                .stream().map(this::convertToDto)
                .toList();
    }
    @GetMapping("/product/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
    public ProductDTO getById(@PathVariable long id) {
        return convertToDto(productService.getById(id));
    }
    @PostMapping("/create/product")
    @PreAuthorize("hasAuthority('admin:create')")
    @Hidden
    public ProductDTO createProduct (@RequestBody ProductDTO newProduct) {
        Product project = convertToEntity(newProduct);
        project = productService.create(project);
        return convertToDto(project);
    }
    @PutMapping("/update/product/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    @Hidden
    public ProductDTO updateProduct(@PathVariable long id, @RequestBody ProductDTO updateProduct) {
        Product project = convertToEntity(updateProduct);
        project = productService.update(id, project);
        return convertToDto(project);
    }
    @DeleteMapping("/delete/product/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    @Hidden
    public ProductDTO deleteProduct(@PathVariable long id) {
        return convertToDto(productService.delete(id));
    }
    @GetMapping("/product/project/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
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
    private Product convertToEntity(ProductDTO dto){
        return modelMapper.map(dto, Product.class);
    }
}
