package com.papeleria.elcompas.controller;

import com.papeleria.elcompas.model.Producto;
import com.papeleria.elcompas.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos") // Endpoint base para todos los productos
public class ProductoController {

    private final ProductoService productoService;

    // Inyectamos el servicio
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // GET /api/productos -> Obtener todos los productos
    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.getAllProductos();
    }

    // GET /api/productos/{id} -> Obtener un producto por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.getProductoById(id));
    }
    
    // POST /api/productos -> Crear un nuevo producto
    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.createProducto(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }
    
    // GET /api/productos/bajo-stock?cantidad=10 -> Obtener productos con stock bajo
    @GetMapping("/bajo-stock")
    public List<Producto> getProductosConPocoStock(@RequestParam(defaultValue = "10") int cantidad) {
        return productoService.getProductosConPocoStock(cantidad);
    }
}