package com.stockmanager.stockmanager.repository;

import com.stockmanager.stockmanager.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
