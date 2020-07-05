/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ocak.zaferemre.carsearch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author Emre
 */
@Component
public class SearchBox {
    
    List<Car> carList;
    
    protected boolean search_car(String key_brand, String key_model, String key_type) {
        
        try {
            read();
            if (!carList.isEmpty()) {
                
                for (Car i : carList) {
                    if (i.getBrand().equals(key_brand) && i.getModel().equals(key_model) && i.getType().equals(key_type)) {
                       //Car result = new Car(i.getBrand(), i.getModel(), i.getType());
                       return true;
                    }
                }
                return false;
            }
        } catch (IOException ex) {
            Logger.getLogger(SearchBox.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    protected String search_key(String key) {
        try {
            read();
            if (!carList.isEmpty() && key != null) {
                List<Car> resultList = new ArrayList<>();
                for (Car i : carList) {
                    if (i.getBrand().contains(key) || i.getModel().contains(key) || i.getType().contains(key)) {
                        resultList.add(i);
                    }
                }
                return getJSON(resultList);
            }
        } catch (IOException ex) {
            Logger.getLogger(SearchBox.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private String getJSON(List<Car> input) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode arrayMapper = objectMapper.createArrayNode();
        ObjectNode node;
        String json; 
        
        
        for(Car i : input) {
            node = objectMapper.createObjectNode();
            node.put("brand", i.getBrand());
            node.put("model", i.getModel());
            node.put("type", i.getType());
            arrayMapper.add(node);
        }
        
        json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayMapper);
        return json;
    }
    
    private void read() throws IOException {
        
        Car new_car;
        this.carList = new ArrayList<>();
        carList.clear();
        
        Resource resource = new ClassPathResource("classpath:models.txt");
        InputStream inputStream = resource.getInputStream();
        
        //File file = new File(getClass().getClassLoader().getResource("models.txt").getFile());
        Scanner scanner = new Scanner(inputStream);
        String line;
        String[] splitted;
        while(scanner.hasNextLine()) {
            line = scanner.nextLine();
            splitted = line.split(";");
            new_car = new Car();
            new_car.setBrand(splitted[0]);
            new_car.setModel(splitted[1]);
            new_car.setType(splitted[2]);
            carList.add(new_car);
        }
    }
    
    protected void print() throws IOException {
        
        read();
        
        for(Car i : carList)
            System.out.println(i.getBrand() + " ; " + i.getModel() + " ; " + i.getType());
    }
    
}
