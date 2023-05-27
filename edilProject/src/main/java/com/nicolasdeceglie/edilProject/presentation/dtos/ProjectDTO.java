package com.nicolasdeceglie.edilProject.presentation.dtos;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private long id;
    private String name;
    private String description;
    private String address;
    private String CAP;
    private String city;
    private List<ImageDTO> images;
    private List<ProductDTO> products;
}
