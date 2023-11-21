package com.mycompany.obitemservice;
import com.mycompany.obitemservice.controller.ItemCategoryController;
import com.mycompany.obitemservice.model.ItemCategoryModel;
import com.mycompany.obitemservice.repository.ItemCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ItemCategoryControllerTest {

    @Mock
    private ItemCategoryRepository itemCategoryRepository;

    @InjectMocks
    private ItemCategoryController itemCategoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllItemCategory() {
        // Arrange
        ItemCategoryModel category1 = new ItemCategoryModel();
        category1.setId("1");
        category1.setName("Category1");

        ItemCategoryModel category2 = new ItemCategoryModel();
        category2.setId("2");
        category2.setName("Category2");

        List<ItemCategoryModel> categories = Arrays.asList(category1, category2);

        when(itemCategoryRepository.findAll()).thenReturn(categories);

        List<ItemCategoryModel> result = itemCategoryController.getAllItemCategory();

        assertEquals(categories.size(), result.size());
    }

    @Test
    void testGetItemCategory() {
        String categoryId = "1";
        ItemCategoryModel category = new ItemCategoryModel();
        category.setId(categoryId);
        category.setName("Category1");

        when(itemCategoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        ItemCategoryModel result = itemCategoryController.getItemCategory(categoryId);

        assertEquals(categoryId, result.getId());
    }
}
