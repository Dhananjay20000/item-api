package com.example.itemapi.service;

import com.example.itemapi.model.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Service class for managing items using in-memory storage (ArrayList)
 * This provides business logic for CRUD operations on items
 */
@Service
public class ItemService {
    
    // In-memory data store using ArrayList
    private final List<Item> items = new ArrayList<>();
    
    // Atomic counter for generating unique IDs
    private final AtomicLong idGenerator = new AtomicLong(1);

    /**
     * Add a new item to the collection
     * @param item The item to add (must have name, description, and price)
     * @return The added item with generated ID
     */
    public Item addItem(Item item) {
        // Generate unique ID
        item.setId(idGenerator.getAndIncrement());
        item.setCreatedAt(java.time.LocalDateTime.now());
        item.setUpdatedAt(java.time.LocalDateTime.now());
        
        // Add to in-memory store
        items.add(item);
        
        return item;
    }

    /**
     * Get a single item by ID
     * @param id The ID of the item to retrieve
     * @return Optional containing the item if found, empty otherwise
     */
    public Optional<Item> getItemById(Long id) {
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    /**
     * Get all items
     * @return List of all items
     */
    public List<Item> getAllItems() {
        return new ArrayList<>(items);
    }

    /**
     * Get the total count of items
     * @return Number of items in the collection
     */
    public long getItemCount() {
        return items.size();
    }
}



