package com.example.GroceryAdmin.GroceryApp.Service;

import com.example.GroceryAdmin.GroceryApp.Dao.AdminDao;
import com.example.GroceryAdmin.GroceryApp.Model.GroceryItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    AdminDao adminDao;

    public ResponseEntity<List<GroceryItems>> getAllItems(){
        try{
            return new ResponseEntity<>(adminDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addGroceryItems(GroceryItems groceryItems){
        try {
            adminDao.save(groceryItems);
            return new ResponseEntity<>("Added Successfully",HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteGroceryItem(Integer itemId){
        try{
            adminDao.deleteById(itemId);
            return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateGroceryItem(Integer id, GroceryItems groceryItems){
        try{
            List<GroceryItems> groceryItemsList = adminDao.findAll();
            for(GroceryItems groceryItems1:groceryItemsList){
                if(groceryItems1.getId()==id){
                    groceryItems1.setName(groceryItems.getName());
                    groceryItems1.setPrice(groceryItems.getPrice());
                    groceryItems1.setQuantityPresent(groceryItems.getQuantityPresent());
                }
                adminDao.save(groceryItems1);
            }
            return new ResponseEntity<>("Successfully updated",HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }
}
