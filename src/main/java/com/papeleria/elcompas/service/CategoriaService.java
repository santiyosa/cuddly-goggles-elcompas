package com.papeleria.elcompas.service;

import com.papeleria.elcompas.exception.ResourceNotFoundException;
import com.papeleria.elcompas.model.Categoria;
import com.papeleria.elcompas.model.Producto;
import com.papeleria.elcompas.repository.CategoriaRepository;
import com.papeleria.elcompas.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository; // Necesario para la lógica de eliminación

    public CategoriaService(CategoriaRepository categoriaRepository, ProductoRepository productoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
    }

    public Categoria createCategoria(Categoria categoria) {
        // Lógica de negocio: Validar que el nombre no esté repetido
        categoriaRepository.findByNombre(categoria.getNombre()).ifPresent(c -> {
            throw new IllegalArgumentException("La categoría '" + c.getNombre() + "' ya existe.");
        });
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public void deleteCategoria(Long id) {
        // Lógica de negocio: No permitir eliminar si tiene productos asociados
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la categoría con ID: " + id));

        List<Producto> productosAsociados = productoRepository.findByCategoriaId(id);
        if (!productosAsociados.isEmpty()) {
            throw new IllegalStateException("No se puede eliminar la categoría '" + categoria.getNombre() + "' porque tiene productos asociados.");
        }
        
        categoriaRepository.deleteById(id);
    }
}