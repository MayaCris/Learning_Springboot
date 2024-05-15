package com.tallerPlatzi.domain.repository;

import com.tallerPlatzi.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    //Nombre de los metodos que cualquier repositorio que vaya a trabajar con productos, tenga que implementar

    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScarseProducts(int quantity);
    Optional<Product> getProduct(int producto);
    Product save (Product product);
    void delete (int productId);


}
