package com.mycompany.obitemservice;
import com.mycompany.obitemservice.controller.ItemCategoryController;
import com.mycompany.obitemservice.model.ItemCategory;
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
        ItemCategory category1 = new ItemCategory();
        category1.setId("1");
        category1.setName("Category1");

        ItemCategory category2 = new ItemCategory();
        category2.setId("2");
        category2.setName("Category2");

        List<ItemCategory> categories = Arrays.asList(category1, category2);

        when(itemCategoryRepository.findAll()).thenReturn(categories);

        List<ItemCategory> result = itemCategoryController.getAllItemCategory();

        assertEquals(categories.size(), result.size());
    }

    @Test
    void testGetItemCategory() {
        String categoryId = "1";
        ItemCategory category = new ItemCategory();
        category.setId(categoryId);
        category.setName("Category1");

        when(itemCategoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        ItemCategory result = itemCategoryController.getItemCategory(categoryId);

        assertEquals(categoryId, result.getId());
    }
}
