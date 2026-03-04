package com.stockmanager.stockmanager.repository;

import com.stockmanager.stockmanager.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
