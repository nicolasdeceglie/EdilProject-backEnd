package com.nicolasdeceglie.edilProject.persistences.entitites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Lob
    @Column(name = "image", length = 1000, columnDefinition = "MEDIUMBLOB")
    private byte[] image;
    @ManyToOne
    @JoinColumn(name = "id_project")
    private Project project;
}