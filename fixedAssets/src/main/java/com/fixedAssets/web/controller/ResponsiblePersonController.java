package com.fixedAssets.web.controller;

import com.fixedAssets.domain.ResponsiblePersonDo;
import com.fixedAssets.domain.service.ResponsiblePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responsiblePerson")
public class ResponsiblePersonController {

    @Autowired
    private ResponsiblePersonService responsiblePersonService;

    @GetMapping("/all")
    public ResponseEntity<List<ResponsiblePersonDo>> getAll(){
        return new ResponseEntity<>(responsiblePersonService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/personId/{personIdD}")
    public ResponseEntity<List<ResponsiblePersonDo>> getBypersonIdD(@PathVariable String personIdD){
        return responsiblePersonService.getBypersonIdD(personIdD).map(responsiblePersonDo -> new ResponseEntity<>(responsiblePersonDo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{personIdD}")
    public ResponseEntity delete(@PathVariable("personIdD") String personIdD) {
        if (responsiblePersonService.deletePerson(personIdD)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<ResponsiblePersonDo> save(@RequestBody ResponsiblePersonDo responsiblePersonDo){
        return new ResponseEntity<>(responsiblePersonService.savePerson(responsiblePersonDo), HttpStatus.CREATED);
    }

}
