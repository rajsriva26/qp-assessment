package com.example.GroceryAdmin.GroceryApp.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class OrderRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;

    @ManyToMany
    private List<GroceryItems> groceryItems;
}
