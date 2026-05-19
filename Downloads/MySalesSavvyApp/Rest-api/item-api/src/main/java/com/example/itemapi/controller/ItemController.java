package com.example.itemapi.controller;

import com.example.itemapi.model.Item;
import com.example.itemapi.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * REST Controller for Item Management API
 * Provides endpoints for managing items in the collection
 */
@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "https://login-app-delta-rosy.vercel.app") // Allow CORS for frontend integration
public class ItemController {
    
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * POST /api/items
     * Add a new item to the collection
     * 
     * Request Body (JSON):
     * {
     *   "name": "Item Name",
     *   "description": "Item Description",
     *   "price": 99.99,
     *   "category": "Category Name" (optional),
     *   "stock": 100 (optional)
     * }
     * 
     * @param item The item to add (validated)
     * @return ResponseEntity with the created item and HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<Item> addItem(@Valid @RequestBody Item item) {
        Item createdItem = itemService.addItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    /**
     * GET /api/items/{id}
     * Get a single item by ID
     * 
     * @param id The ID of the item to retrieve
     * @return ResponseEntity with the item if found (HTTP 200), or HTTP 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Optional<Item> item = itemService.getItemById(id);
        
        if (item.isPresent()) {
            return ResponseEntity.ok(item.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET /api/items
     * Get all items in the collection
     * 
     * @return ResponseEntity with list of all items
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllItems() {
        Map<String, Object> response = new HashMap<>();
        response.put("items", itemService.getAllItems());
        response.put("total", itemService.getItemCount());
        return ResponseEntity.ok(response);
    }

    /**
     * Exception handler for validation errors
     * Returns detailed error messages when input validation fails
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}



