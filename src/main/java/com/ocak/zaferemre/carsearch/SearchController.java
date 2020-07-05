/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocak.zaferemre.carsearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Emre
 */

@Controller
@Component
public class SearchController {
    
    @Autowired
    private SearchBox searchBox;
    
    @GetMapping("/print")
    @ResponseBody
    public String print() {
        try {
            searchBox.print();
            
        } catch (IOException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return String.format("All available models printed out on console!");
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "/criteria", consumes = "application/json")
    @ResponseBody
    public ResponseEntity viaBody(@RequestBody Car car) throws JsonProcessingException {
        System.out.println("Received: ");
        System.out.println(String.format("%s\t%s\t%s", car.getBrand(), car.getModel(), car.getType()));
        boolean result = searchBox.search_car(car.getBrand(), car.getModel(), car.getType());        
        
        if(result == true)
            return ResponseEntity.ok(HttpStatus.FOUND);
        else
            return ResponseEntity.ok(HttpStatus.NOT_FOUND);
        
    }
    
    @GetMapping("/search")
    @ResponseBody
    public String viaKey(@RequestParam(value = "key", defaultValue = "A") String key) {
        System.out.println("Received: " + key);
        System.out.println(searchBox.search_key(key));
        return String.format("%s", searchBox.search_key(key));
    }
}
