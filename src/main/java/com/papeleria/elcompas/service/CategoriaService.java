package com.papeleria.elcompas.service;

import java.util.List;

import com.papeleria.elcompas.model.Categoria;

public interface CategoriaService {

    public Categoria createCategoria(Categoria categoria);
    public List<Categoria> getAllCategorias();
    public void deleteCategoria(Long id, Categoria categoria);

}