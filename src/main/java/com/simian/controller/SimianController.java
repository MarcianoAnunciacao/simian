package com.simian.controller;

import com.simian.service.SimianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dna")
public class SimianController {
    @Autowired
    private SimianService simianService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Boolean> isDnaBelongsToASimian(@RequestBody List<String> dnaSequence){
        if(simianService.isSimian(dnaSequence)){
            return ResponseEntity.ok().body(true);
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(false);
        }

    }
}
