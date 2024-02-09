package com.example.GroceryAdmin.GroceryApp.Dao;

import com.example.GroceryAdmin.GroceryApp.Model.GroceryItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDao extends JpaRepository<GroceryItems,Integer> {
}
