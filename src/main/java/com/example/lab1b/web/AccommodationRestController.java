package com.example.lab1b.web;

import com.example.lab1b.model.Accommodation;
import com.example.lab1b.model.Category;
import com.example.lab1b.model.Country;
import com.example.lab1b.model.Host;
import com.example.lab1b.model.dto.AccommodationDTO;
import com.example.lab1b.model.dto.HostDTO;
import com.example.lab1b.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/accommodation")
public class AccommodationRestController {
    private final AccommodationService accommodationService;

    public AccommodationRestController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }
    @GetMapping
    public List<Accommodation> findAll(){
        return accommodationService.findAll();
        //return accommodationService.findAllUnRented();
    }
    @GetMapping("/rented")
    public List<Accommodation> findAllRented(){
       return accommodationService.findAllRented();
    }
    @GetMapping("/unrented")
    public List<Accommodation> findAllUnRented(){
        return accommodationService.findAllUnRented();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> findById(@PathVariable Long id){
        return accommodationService.findById(id)
                .map(m -> ResponseEntity.ok().body(m))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Postman
//    @PostMapping("/add")
//    public ResponseEntity<Accommodation> save(@RequestParam String name,
//                                              @RequestParam String category,
//                                              @RequestParam Long hostId,
//                                              @RequestParam Integer numRooms){
//        Category categoryEnum = Category.valueOf(category.toUpperCase());
//        return accommodationService.save(name,categoryEnum, hostId, numRooms)
//                .map(m -> ResponseEntity.ok().body(m))
//                .orElseGet(() -> ResponseEntity.badRequest().build());
//
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        try {
            accommodationService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
//        accommodationService.deleteById(id);
//        if (accommodationService.findById(id).isEmpty()){
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.badRequest().build();
    }

//    @PutMapping("/edit/{id}")
//    public ResponseEntity<Accommodation> save(@PathVariable Long id, @RequestParam String name,
//                                              @RequestParam String category,
//                                              @RequestParam Long hostId,
//                                              @RequestParam Integer numRooms) {
//        Category categoryEnum = Category.valueOf(category.toUpperCase());
//        return this.accommodationService.edit(id, name,categoryEnum, hostId, numRooms)
//                .map(product -> ResponseEntity.ok().body(product))
//                .orElseGet(() -> ResponseEntity.badRequest().build());
//    }

    @PutMapping("/rent/{id}")
    public ResponseEntity<Accommodation> rent(@PathVariable Long id) {
        return this.accommodationService.rent(id)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Accommodation> save(@RequestBody AccommodationDTO accommodationDTO){
        return this.accommodationService.save(accommodationDTO)
                .map(m -> ResponseEntity.ok().body(m))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Accommodation> save(@PathVariable Long id,
                                              @RequestBody AccommodationDTO accommodationDTO) {
        return this.accommodationService.edit(id, accommodationDTO)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
