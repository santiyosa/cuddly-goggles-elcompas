package com.papeleria.elcompas.repository;

import com.papeleria.elcompas.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional; // Importa Optional

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    /**
     * Busca una categoría por su nombre.
     * Devuelve un Optional para manejar de forma segura el caso de que no se encuentre.
     * @param nombre El nombre de la categoría a buscar.
     * @return Un Optional que contiene la Categoria si se encuentra, o un Optional vacío si no.
     */
    Optional<Categoria> findByNombre(String nombre); 

}