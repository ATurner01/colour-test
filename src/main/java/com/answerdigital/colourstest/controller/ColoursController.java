package com.answerdigital.colourstest.controller;

import com.answerdigital.colourstest.model.Colour;
import com.answerdigital.colourstest.repository.ColoursRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/Colours")
public class ColoursController {

    @Autowired
    private ColoursRepository coloursRepository;

    @GetMapping
    public ResponseEntity<List<Colour>> getColours() {
        return new ResponseEntity(coloursRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colour> getColour(@PathVariable("id") long id){
      Colour colour = coloursRepository.findById(id).orElse(null);

      if (colour == null){
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity(colour, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Colour> newColour(@RequestBody Colour colour){
      coloursRepository.save(colour);

      return new ResponseEntity(colour, HttpStatus.CREATED);
    }



}
