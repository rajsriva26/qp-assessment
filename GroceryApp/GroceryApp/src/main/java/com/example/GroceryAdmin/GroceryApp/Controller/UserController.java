package com.example.GroceryAdmin.GroceryApp.Controller;

import com.example.GroceryAdmin.GroceryApp.Model.GroceryItems;
import com.example.GroceryAdmin.GroceryApp.Model.OrderItem;
import com.example.GroceryAdmin.GroceryApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/availableItems")
    public ResponseEntity<List<GroceryItems>> getAvailableGroceryItems(){
        return userService.getAvailableGroceryItems();
    }

    @PostMapping("/order/{orderId}")
    public ResponseEntity<String> placeOrder(@PathVariable Integer orderId, @RequestBody List<OrderItem> order){
        return userService.placeOrder(orderId,order);
    }
}
