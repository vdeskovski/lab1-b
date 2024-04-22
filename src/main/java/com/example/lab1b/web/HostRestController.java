package com.example.lab1b.web;

import com.example.lab1b.model.Country;
import com.example.lab1b.model.Host;
import com.example.lab1b.model.dto.HostDTO;
import com.example.lab1b.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/host")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class HostRestController {
    private final HostService hostService;

    public HostRestController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping
    public List<Host> findAll(){
        return hostService.fetchAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Host> findById(@PathVariable Long id){
        return hostService.findById(id)
                .map(m -> ResponseEntity.ok().body(m))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Postman
//    @PostMapping("/add")
//    public ResponseEntity<Host> save(@RequestParam String name, @RequestParam String surname,
//                                        @RequestParam Long countryId){
//        return hostService.save(name,surname,countryId)
//                .map(m -> ResponseEntity.ok().body(m))
//                .orElseGet(() -> ResponseEntity.badRequest().build());
//
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        hostService.deleteById(id);
        if (hostService.findById(id).isEmpty()){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Host> save(@PathVariable Long id, @RequestParam String name, @RequestParam String surname,
                                     @RequestParam Long countryId) {
        return this.hostService.edit(id, name,surname,countryId)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Host> save(@RequestBody HostDTO hostDTO){
        return this.hostService.save(hostDTO)
                .map(m -> ResponseEntity.ok().body(m))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


}
