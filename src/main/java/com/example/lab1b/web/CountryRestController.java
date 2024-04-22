package com.example.lab1b.web;

import com.example.lab1b.model.Country;
import com.example.lab1b.model.dto.CountryDTO;
import com.example.lab1b.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class CountryRestController {
    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> findAll(){
        return countryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id){
        return countryService.findById(id)
                .map(m -> ResponseEntity.ok().body(m))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Postman
//    @PostMapping("/add")
//    public ResponseEntity<Country> save(@RequestParam String name, @RequestParam String continent){
//        return countryService.save(name,continent)
//                .map(m -> ResponseEntity.ok().body(m))
//                .orElseGet(() -> ResponseEntity.badRequest().build());
//
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        countryService.deleteById(id);
        if (countryService.findById(id).isEmpty()){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Country> save(@PathVariable Long id, @RequestParam String name, @RequestParam String continent) {
        return this.countryService.edit(id, name,continent)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Country> saveDTO(@RequestBody CountryDTO productDto) {
        return this.countryService.save(productDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
