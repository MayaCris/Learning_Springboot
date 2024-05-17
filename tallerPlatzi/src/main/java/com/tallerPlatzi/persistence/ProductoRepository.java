package com.tallerPlatzi.persistence;

import com.tallerPlatzi.domain.Product;
import com.tallerPlatzi.domain.repository.ProductRepository;
import com.tallerPlatzi.persistence.crud.ProductoCrudRepository;
import com.tallerPlatzi.persistence.entity.Producto;
import com.tallerPlatzi.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {

    private ProductMapper mapper;

    private ProductoCrudRepository productoCrudRepository;

    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity, byte active) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, (byte) 1);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int producto) {
        return productoCrudRepository.findById(producto).map(prod -> mapper.toProduct(prod));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));

    }

    @Override
    public void delete( int productId) {
        productoCrudRepository.deleteById(productId);
    }
}
