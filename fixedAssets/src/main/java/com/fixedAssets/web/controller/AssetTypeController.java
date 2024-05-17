package com.fixedAssets.web.controller;

import com.fixedAssets.domain.AssetTypeDo;
import com.fixedAssets.domain.service.AssetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/assetType")
public class AssetTypeController {

    @Autowired
    private AssetTypeService assetTypeService;

    @GetMapping("/allTypes")
    public ResponseEntity<List<AssetTypeDo>> getAll() {
        return new ResponseEntity<>(assetTypeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/findById/{typeIdD}")
    public ResponseEntity<List<AssetTypeDo>> findByTypeIdD(@PathVariable("typeIdD") int typeIdD) {
        return assetTypeService.findByTypeIdD(typeIdD).map(assetTypeDo -> new ResponseEntity<>(assetTypeDo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<AssetTypeDo> save(@RequestBody AssetTypeDo assetTypeDo) {
        return new ResponseEntity<>(assetTypeService.save(assetTypeDo), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{typeIdD}")
    public ResponseEntity delete(@PathVariable("typeIdD") int typeIdD) {
        if (assetTypeService.delete(typeIdD)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
