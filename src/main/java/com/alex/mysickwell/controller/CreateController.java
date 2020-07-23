package com.alex.mysickwell.controller;

import com.alex.mysickwell.service.CreateTableService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/create")
public class CreateController {

    private final CreateTableService createTableService;

    @PutMapping
    public ResponseEntity<?> createNewTable(@RequestBody String query){
        System.out.println("query is " + query);
        return createTableService.createTable(query);
    }

}
