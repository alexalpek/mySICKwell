package com.alex.mysickwell.controller;

import com.alex.mysickwell.service.InsertService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/insert")
public class InsertController {

    private InsertService insertService;

    @PutMapping
    public ResponseEntity<?> insertToTable(@RequestBody String query) {
        System.out.println("query is " + query);
        return insertService.insertToTable(query);
    }
}
