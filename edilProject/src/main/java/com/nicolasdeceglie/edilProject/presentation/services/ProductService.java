package com.nicolasdeceglie.edilProject.presentation.services;

import com.nicolasdeceglie.edilProject.persistences.entitites.Product;
import com.nicolasdeceglie.edilProject.persistences.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product getById(long id) {
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isEmpty()) throw new IllegalStateException("Imagine non trobvata");
        return optionalProduct.get();
    }

    public Product create(Product newProduct) {
        if (newProduct.getProject() == null) {
            throw new IllegalStateException("Non può non avere un lavoro collegato");
        }
        newProduct = repository.save(newProduct);
        return newProduct;
    }

    public Product update(long id, Product toUpdate) {
        if (toUpdate.getProject() == null){
            throw new IllegalStateException("Non può avere il campo idWork nullo");
        }
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isEmpty()) throw new IllegalStateException("Prodotto non trovato");
        Product entityToUpdate = optionalProduct.get();
        toUpdate.setId(entityToUpdate.getId());
        toUpdate = repository.save(toUpdate);
        return toUpdate;
    }
    public Product delete(long id){
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isEmpty()) throw new IllegalStateException("Artist not found");
        repository.delete(optionalProduct.get());
        return optionalProduct.get();
    }
}
