package com.example.GroceryAdmin.GroceryApp.Dao;

import com.example.GroceryAdmin.GroceryApp.Model.OrderRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<OrderRequest,Integer> {
}
