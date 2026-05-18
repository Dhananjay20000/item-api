package com.example.itemapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for Item Management API
 * This is a RESTful API for managing a collection of items
 * (e.g., ecommerce products like Flipkart, movies like Netflix, etc.)
 */
@SpringBootApplication
public class ItemApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemApiApplication.class, args);
    }
}



