package com.papeleria.elcompas.model;


import lombok.Data;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String nombre;
    
    // Una categoría tiene muchos productos. 'mappedBy' indica que la relación
    // es gestionada por el campo 'categoria' en la entidad Producto.
    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;


}