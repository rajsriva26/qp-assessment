package com.example.GroceryAdmin.GroceryApp.Service;

import com.example.GroceryAdmin.GroceryApp.Dao.AdminDao;
import com.example.GroceryAdmin.GroceryApp.Dao.UserDao;
import com.example.GroceryAdmin.GroceryApp.Model.GroceryItems;
import com.example.GroceryAdmin.GroceryApp.Model.OrderItem;
import com.example.GroceryAdmin.GroceryApp.Model.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    AdminDao adminDao;
    public ResponseEntity<List<GroceryItems>> getAvailableGroceryItems() {
        try {
            List<GroceryItems> availableItems = new ArrayList<>();
            List<GroceryItems> groceryItems = adminDao.findAll();
            for (GroceryItems items : groceryItems) {
                if (items.getQuantityPresent() > 0) {
                    availableItems.add(items);
                }
            }
            return new ResponseEntity<>(availableItems, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> placeOrder(Integer id,List<OrderItem> orderItem) {
        try{
            OrderRequest userOrder = new OrderRequest();
            List<GroceryItems> orderPlaced = new ArrayList<>();
            for(OrderItem order : orderItem){

                GroceryItems item = adminDao.findById(order.getId()).get();
                if(item.getQuantityPresent()>order.getQuantity()){
                    Integer remQuantity= item.getQuantityPresent()-order.getQuantity();
                    item.setQuantityPresent(remQuantity);
                    adminDao.save(item);
                    orderPlaced.add(item);

                }else{
                    System.out.println("Order cannot be placed for Order Id "+item.getId()+" due to insufficient quantity");
                }
                userOrder.setOrderId(id);
                userOrder.setGroceryItems(orderPlaced);
                userDao.save(userOrder);
            }

            return new ResponseEntity<>("Order Placed Successfully" ,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }
}
