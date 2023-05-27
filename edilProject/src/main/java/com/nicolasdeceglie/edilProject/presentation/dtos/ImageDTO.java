package com.nicolasdeceglie.edilProject.presentation.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {
    private long id;
    private String name;
    private byte[] image;
    private long idProject;
}