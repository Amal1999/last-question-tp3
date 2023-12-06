package com.mycompany.obitemservice.repository;

import com.mycompany.obitemservice.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemRepository extends MongoRepository<Item, String> {
    public void deleteAllByCategoryId(String categoryId);
    public List<Item> findByCategoryId(String categoryId);
}
