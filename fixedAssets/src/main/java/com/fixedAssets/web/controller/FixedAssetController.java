package com.fixedAssets.web.controller;


import com.fixedAssets.domain.FixedAssetDo;
import com.fixedAssets.domain.service.FixedAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fixedAsset")
public class FixedAssetController {

    @Autowired
    private FixedAssetService fixedAssetService;

    @GetMapping("/all")
    public ResponseEntity<List<FixedAssetDo>> getAll(){
        return new ResponseEntity<>(fixedAssetService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{personIdD}")
    public ResponseEntity<List<FixedAssetDo>> getByResponsiblePersonDo(@PathVariable("personIdD") String personIdD){
        return fixedAssetService.getByResponsiblePersonDo(personIdD).map(fixedAssetDo -> new ResponseEntity<>(fixedAssetDo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/value/{acquisitionValueD}")
    public ResponseEntity<List<FixedAssetDo>> getMinimumQuantityDo(@PathVariable("acquisitionValueD") int acquisitionValueD){
        return fixedAssetService.getMinimumQuantityDo(acquisitionValueD).map(fixedAssetDo -> new ResponseEntity<>(fixedAssetDo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<FixedAssetDo> save(@RequestBody FixedAssetDo fixedAssetDo){
        return new ResponseEntity<>(fixedAssetService.save(fixedAssetDo), HttpStatus.CREATED);
    }

}
