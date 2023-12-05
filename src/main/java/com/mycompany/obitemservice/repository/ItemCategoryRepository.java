package com.mycompany.obitemservice.repository;

import com.mycompany.obitemservice.model.ItemCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemCategoryRepository extends MongoRepository<ItemCategory, String> {
}
