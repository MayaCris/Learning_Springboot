package com.fixedAssets.web.controller;

import com.fixedAssets.domain.LocationDo;
import com.fixedAssets.domain.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    LocationService locationService;

    @GetMapping("/all")
    public ResponseEntity<List<LocationDo>> getAll() {
        return ResponseEntity.ok(locationService.getAll());
    }

    @GetMapping("/{locationCityD}")
    public ResponseEntity<List<LocationDo>> getByLocationCityD(@PathVariable String locationCityD) {
        return locationService.getByLocationCityD(locationCityD).map(locationDo -> new ResponseEntity<>(locationDo, HttpStatus.OK))
                .orElseGet(() ->ResponseEntity.notFound().build());
    }

    @GetMapping("/id/{locationIdD}")
    public ResponseEntity<List<LocationDo>> findByLocationIdDo(@PathVariable Integer locationIdDo) {
        return locationService.findByLocationIdDo(locationIdDo).map(locationDo -> new ResponseEntity<>(locationDo, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{locationIdD}")
    public ResponseEntity deleteLocation(@PathVariable Integer locationIdD) {
        if (locationService.deleteLocation(locationIdD)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<LocationDo> saveLocation(@RequestBody LocationDo locationDo) {
        return new ResponseEntity<>(locationService.saveLocation(locationDo), HttpStatus.CREATED);
    }

}
