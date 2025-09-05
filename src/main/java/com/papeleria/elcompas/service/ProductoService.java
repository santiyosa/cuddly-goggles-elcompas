package com.papeleria.elcompas.service;

import java.util.List;

import com.papeleria.elcompas.model.Producto;

public interface ProductoService {

    public List<Producto> getAllProductos();
     public Producto getProductoById(Long id);
     public Producto createProducto(Producto producto);
     public List<Producto> getProductosConPocoStock(int cantidadMinima);


}