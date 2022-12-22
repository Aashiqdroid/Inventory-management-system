package com.aashiq.repository;

import com.aashiq.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    List<Item> findByItemName(String itemName);

    List<Item> findByItemBrand(String itemBrand);

    List<Item> findByItemNameOrItemBrand(String itemName,String itemBrand);

    @Query("SELECT p from Item p WHERE " +
            "p.itemName LIKE CONCAT('%',:name,'%')"+
            "OR p.itemBrand LIKE CONCAT('%',:brand,'%')")
    List<Item> searchProducts(String name,String brand);

    @Query("SELECT p FROM Item p WHERE p.price BETWEEN :from AND :to")
    List<Item> filter(Long from,Long to);

}
