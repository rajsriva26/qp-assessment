package com.example.GroceryAdmin.GroceryApp.Controller;

import com.example.GroceryAdmin.GroceryApp.Model.GroceryItems;
import com.example.GroceryAdmin.GroceryApp.Service.AdminService;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/allItem")
    public ResponseEntity<List<GroceryItems>> getAllItems(){
        return adminService.getAllItems();
    }

    @PostMapping("/addItem")
    public ResponseEntity<String> addGroceryItem(@RequestBody GroceryItems groceryItems){
        return adminService.addGroceryItems(groceryItems);
    }

    @DeleteMapping("/deleteItem/{id}")
    public ResponseEntity<String> deleteGroceryItem(@PathVariable Integer id){
        return adminService.deleteGroceryItem(id);
    }

    @PutMapping("updateItem/{id}")
    public ResponseEntity<String> updateGroceryItem(@PathVariable Integer id,@RequestBody GroceryItems groceryItems){
        return adminService.updateGroceryItem(id,groceryItems);
    }

}
