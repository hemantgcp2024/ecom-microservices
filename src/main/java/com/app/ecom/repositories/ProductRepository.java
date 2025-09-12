package com.app.ecom.repositories;

import com.app.ecom.dto.ProductResponse;
import com.app.ecom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByActiveTrue();

    @Query("SELECT p FROM products p WHERE p.active =true and p.stockQuantity > 0 AND LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
  // @Query("SELECT p FROM Products p WHERE p.active =true and p.stockQuantity > 0 AND (LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Product> searchProducts(@Param("keyword") String keyword);
}
