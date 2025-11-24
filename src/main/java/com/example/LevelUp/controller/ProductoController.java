package com.example.LevelUp.controller;

import com.example.LevelUp.model.CartItemDTO;
import com.example.LevelUp.model.ProductoEntity;
import com.example.LevelUp.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("api/v1/producto")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<ProductoEntity> listarProductos() {
        return productoService.findAll();
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<ProductoEntity> obtenerProducto(@PathVariable Long idProducto) {
        try {
            ProductoEntity producto = productoService.findById(idProducto);
            return ResponseEntity.ok(producto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<ProductoEntity> crearProducto(
        @RequestParam("nombre") String nombre,
        @RequestParam("descripcion") String descripcion,
        @RequestParam("precio") double precio,
        @RequestParam("categoria") String categoria,
        @RequestParam("marca") String marca,
        @RequestParam(value = "descuento", defaultValue = "0") double descuento,
        @RequestParam(value = "envioGratis", defaultValue = "false") boolean envioGratis,
        @RequestParam(value = "juego", required = false) String juego,
        @RequestParam(value = "imagen", required = false) MultipartFile imagenFile
    ) {
        ProductoEntity producto = new ProductoEntity();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setCategoria(categoria);
        producto.setMarca(marca);
        producto.setDescuento(descuento);
        producto.setEnvioGratis(envioGratis);
        producto.setJuego(juego);

        if (imagenFile != null && !imagenFile.isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_" + imagenFile.getOriginalFilename();
                Path uploadPath = Paths.get("uploads", fileName);
                Files.createDirectories(uploadPath.getParent());
                Files.copy(imagenFile.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);
                producto.setImagen("/uploads/" + fileName);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        ProductoEntity creado = productoService.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{idProducto}")
    public ResponseEntity<ProductoEntity> actualizarProducto(@PathVariable Long idProducto,
                                                            @RequestBody ProductoEntity productoNuevo) {
        try {
            System.out.println("Authorities: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
            ProductoEntity actualizado = productoService.update(idProducto, productoNuevo);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{idProducto}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long idProducto) {
        System.out.println("Authorities: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        productoService.delete(idProducto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categoria/{categoria}")
    public List<ProductoEntity> productosPorCategoria(@PathVariable String categoria) {
        return productoService.findByCategoria(categoria);
    }

    @GetMapping("/marca/{marca}")
    public List<ProductoEntity> productosPorMarca(@PathVariable String marca) {
        return productoService.findByMarca(marca);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/bulk")
    public ResponseEntity<Void> cargarProductosBulk(@RequestBody List<ProductoEntity> productos) {
        productoService.saveAll(productos);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/descontar-stock")
    public ResponseEntity<?> descontarStock(@RequestBody List<CartItemDTO> items) {
        for (CartItemDTO item : items) {
            System.out.println("idProducto = " + item.getIdProducto() + ", cantidad = " + item.getCantidad());
            boolean exito = productoService.descontarStock(item.getIdProducto(), item.getCantidad());
            if (!exito) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No hay stock suficiente para el producto ID: " + item.getIdProducto());
            }
        }
        return ResponseEntity.ok("Stock descontado exitosamente");
    }


}
