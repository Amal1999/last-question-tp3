package com.mycompany.obitemservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.obitemservice.controller.ItemCategoryController;
import com.mycompany.obitemservice.model.ItemCategory;
import com.mycompany.obitemservice.repository.ItemCategoryRepository;
import com.mycompany.obitemservice.repository.ItemRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest(ItemCategoryController.class)
public class ItemCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemCategoryRepository itemCategoryRepository;

    @Test
    public void testViewHomePage() throws Exception {
        // Mock your repository data
        List<ItemCategory> categories = new ArrayList<>();
        // Add some sample data
        categories.add(new ItemCategory("1", "Category 1", "Description 1"));
        categories.add(new ItemCategory("2", "Category 2", "Description 2"));

        // Setup the mock behavior
        Mockito.when(itemCategoryRepository.findAll()).thenReturn(categories);

        // Perform the request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("categories"))
                .andExpect(MockMvcResultMatchers.model().attribute("categories", categories))
                .andExpect(MockMvcResultMatchers.model().attribute("category", new ItemCategory()));
    }

    @Test
    public void testUpdateItemCategory() throws Exception {
        // Mock your repository data
        ItemCategory existingItem = new ItemCategory("1", "Category 1", "Description 1");
        ItemCategory updatedItem = new ItemCategory("1", "Updated Category", "Updated Description");

        // Setup the mock behavior
        Mockito.when(itemCategoryRepository.findById("1")).thenReturn(Optional.of(existingItem));
        Mockito.when(itemCategoryRepository.save(Mockito.any(ItemCategory.class))).thenReturn(updatedItem);

        // Perform the request and validate the response
        mockMvc.perform(MockMvcRequestBuilders.put("/categories/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedItem)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Category"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Updated Description"));

        // Verify that the repository method was called
        Mockito.verify(itemCategoryRepository, Mockito.times(1)).findById("1");
        Mockito.verify(itemCategoryRepository, Mockito.times(1)).save(Mockito.any(ItemCategory.class));
    }

    // Helper method to convert an object to JSON string
    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
