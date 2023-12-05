package com.mycompany.obitemservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

@Document(collection = "ItemCategoryModel")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCategoryModel {
    private String id;
    private String name;
}
