package com.papeleria.elcompas.service;

import com.papeleria.elcompas.exception.ResourceNotFoundException;
import com.papeleria.elcompas.model.Producto;
import com.papeleria.elcompas.repository.CategoriaRepository;
import com.papeleria.elcompas.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    // Inyección de dependencias por constructor
    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Producto getProductoById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
    }

    public Producto createProducto(Producto producto) {
        // Lógica de negocio: Asegurarnos de que la categoría del producto exista.
        Long categoriaId = producto.getCategoria().getId();
        categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new ResourceNotFoundException("La categoría especificada no existe: " + categoriaId));

        // Lógica de negocio: No permitir stock o precio negativos.
        if (producto.getPrecio() < 0 || producto.getStock() < 0) {
            throw new IllegalArgumentException("El precio y el stock no pueden ser negativos.");
        }
        
        return productoRepository.save(producto);
    }
    
    public List<Producto> getProductosConPocoStock(int cantidadMinima) {
        return productoRepository.findByStockLessThan(cantidadMinima);
    }
}