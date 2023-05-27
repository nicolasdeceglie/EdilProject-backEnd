package com.nicolasdeceglie.edilProject.presentation.services;

import com.nicolasdeceglie.edilProject.persistences.entitites.Image;
import com.nicolasdeceglie.edilProject.persistences.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    private ImageRepository repository;

    public List<Image> getAll() {
        return repository.findAll();
    }

    public Image getById(long id) {
        Optional<Image> optionalImage = repository.findById(id);
        if (optionalImage.isEmpty()) throw new IllegalStateException("Imagine non trobvata");
        return optionalImage.get();
    }


    public Image create(Image newImage) {
        if (newImage.getProject() == null) {
            throw new IllegalStateException("Non può non avere un lavoro collegato");
        }
        newImage = repository.save(newImage);
        return newImage;

    }

    public Image update(long id, Image toUpdate) {
        if (toUpdate.getProject() == null) {
            throw new IllegalStateException("Non può avere il campo idWork vuoto");
        }
        Optional<Image> optionalImage = repository.findById(id);
        if (optionalImage.isEmpty()) throw new IllegalStateException("Immagine non trovata");
        Image entityToUpdate = optionalImage.get();
        toUpdate.setId(entityToUpdate.getId());
        toUpdate = repository.save(toUpdate);
        return toUpdate;
    }
    public Image delete(long id){
        Optional<Image> optionalImage = repository.findById(id);
        if (optionalImage.isEmpty()) throw new IllegalStateException("Image not found");
        repository.delete(optionalImage.get());
        return optionalImage.get();
    }
}

