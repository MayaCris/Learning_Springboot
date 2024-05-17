package com.fixedAssets.web.controller;

import com.fixedAssets.domain.DepreciationDo;
import com.fixedAssets.domain.service.DepreciationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/depreciation")
public class DepreciationController {

    @Autowired
    private DepreciationService depreciationService;

    @GetMapping("/all")
    public ResponseEntity<List<DepreciationDo>> getAll(){
        return new ResponseEntity<>(depreciationService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/DepreciationId/{DepreciationId}")
    public ResponseEntity<List<DepreciationDo>> findByDepreciationIdD(@PathVariable("DepreciationId") int depreciationIdD){
        return depreciationService.findByDepreciationIdD(depreciationIdD).map(depreciationDo -> new ResponseEntity<>(depreciationDo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/byAssetId/{assetIdD}")
    public ResponseEntity<List<DepreciationDo>> findByAssetIdD (@PathVariable("assetIdD") int assetIdD){
        return depreciationService.findByAssetIdD(assetIdD).map(depreciationDo -> new ResponseEntity<>(depreciationDo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<DepreciationDo> save(@RequestBody DepreciationDo depreciationDo){
        return new ResponseEntity<>(depreciationService.save(depreciationDo), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{depreciationIdD}")
    public ResponseEntity delete(@PathVariable("depreciationIdD") int depreciationIdD) {
        if (depreciationService.delete(depreciationIdD)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
