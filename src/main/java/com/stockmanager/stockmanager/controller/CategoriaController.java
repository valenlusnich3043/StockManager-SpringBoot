package com.stockmanager.stockmanager.controller;

import com.stockmanager.stockmanager.model.Categoria;
import com.stockmanager.stockmanager.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository repo;

    @GetMapping
    public List<Categoria> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Categoria crear(@RequestBody Categoria categoria) {
        return repo.save(categoria);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        repo.deleteById(id);
    }
}
