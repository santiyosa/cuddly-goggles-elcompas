    package com.papeleria.elcompas.repository;

import com.papeleria.elcompas.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    // Consulta derivada: Encuentra todos los productos con stock menor a un valor dado.
    List<Producto> findByStockLessThan(int stock);

    // Consulta derivada: Encuentra todos los productos de una categoría específica.
    List<Producto> findByCategoriaId(Long categoriaId);
}