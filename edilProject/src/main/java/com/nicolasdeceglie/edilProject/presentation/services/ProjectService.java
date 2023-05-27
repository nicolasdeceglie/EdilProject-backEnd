package com.nicolasdeceglie.edilProject.presentation.services;


import com.nicolasdeceglie.edilProject.persistences.repositories.ProjectRepository;
import com.nicolasdeceglie.edilProject.persistences.entitites.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public Project getById(long id) {
        Optional<Project> optionalWork = projectRepository.findById(id);
        if (optionalWork.isEmpty()) throw new IllegalStateException("Work not found");
        return optionalWork.get();
    }
    public Project create (Project newProject) {
        newProject = projectRepository.save(newProject);
        return newProject;
    }

    public Project update(long id, Project updateProject) {
        Optional<Project> optionalWork = projectRepository.findById(id);
        if (optionalWork.isEmpty()) throw new IllegalStateException("Entità non trovata0");
        Project entityToUpdate = optionalWork.get();
        updateProject.setId(entityToUpdate.getId());
        updateProject = projectRepository.save(updateProject);
        return updateProject;
    }

    public Project delete(long id) {
        Optional<Project> optionalWork = projectRepository.findById(id);
        if (optionalWork.isEmpty()) {
            throw new IllegalStateException("Entità non trovata");
        }
        Project entityToDelete = optionalWork.get();
        projectRepository.delete(entityToDelete);
        return entityToDelete;
    }
    public Project deleteByTitle(String title) {
        Optional<Project> optionalProject = projectRepository.deleteByName(title);
        if (optionalProject.isEmpty()) throw new IllegalStateException("Project not found");
        Project entityToDelete = optionalProject.get();
        projectRepository.delete(entityToDelete);
        return entityToDelete;
    }
}
