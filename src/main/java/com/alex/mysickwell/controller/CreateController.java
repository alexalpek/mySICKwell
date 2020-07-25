package com.alex.mysickwell.controller;

import com.alex.mysickwell.service.CreateTableService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/create")
public class CreateController {

    private final Logger logger = LoggerFactory.getLogger(CreateController.class);
    private final CreateTableService createTableService;

    @PutMapping
    public ResponseEntity<?> createNewTable(@RequestBody String query) throws Exception {
        logger.info("query is " + query);
        createTableService.createTable(query);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Autowired
    public CreateController(CreateTableService createTableService) {
        this.createTableService = createTableService;
    }
}
