package com.alex.mysickwell.controller;

import com.alex.mysickwell.service.CreateTableService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CreateController {

    private final CreateTableService createTableService;

    @PutMapping("/create")
    public ResponseEntity<?> createNewTable(@RequestBody String query){
        System.out.println("query is " + query);
        return createTableService.createTable(query);
    }

}
