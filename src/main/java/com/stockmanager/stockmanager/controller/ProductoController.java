package com.stockmanager.stockmanager.controller;

import com.stockmanager.stockmanager.model.Producto;
import com.stockmanager.stockmanager.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository repo;

    @GetMapping
    public List<Producto> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return repo.save(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable int id, @RequestBody Producto datos) {
        datos.setId(id);
        return repo.save(datos);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        repo.deleteById(id);
    }
}