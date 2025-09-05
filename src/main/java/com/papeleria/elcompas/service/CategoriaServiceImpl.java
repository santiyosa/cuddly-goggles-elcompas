package com.papeleria.elcompas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.papeleria.elcompas.exception.ResourceNotFoundException;
import com.papeleria.elcompas.model.Categoria;
import com.papeleria.elcompas.model.Producto;
import com.papeleria.elcompas.repository.CategoriaRepository;
import com.papeleria.elcompas.repository.ProductoRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    private ProductoRepository productoRepository;

    @Override
    public Categoria createCategoria(Categoria categoria) {
        // Lógica de negocio: Validar que el nombre no esté repetido
        categoriaRepository.findByNombre(categoria.getNombre()).ifPresent(c -> {
            throw new IllegalArgumentException("La categoría '" + c.getNombre() + "' ya existe.");
        });
        return categoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public void deleteCategoria(Long id, Categoria categoria) {
        // Lógica de negocio: No permitir eliminar si tiene productos asociados

        categoriaRepository.findByNombre(categoria.getNombre()).ifPresentOrElse(c -> {
            List<Producto> productosAsociados = productoRepository.findByCategoriaId(id);
            if (!productosAsociados.isEmpty()) {
                throw new IllegalStateException("No se puede eliminar la categoría '" + categoria.getNombre()
                        + "' porque tiene productos asociados.");
            }
            categoriaRepository.deleteById(id);
        },
                () -> {
                    
                    throw new IllegalArgumentException("La categoría '" + categoria.getNombre() + "' no existe.");
                });

    }

}