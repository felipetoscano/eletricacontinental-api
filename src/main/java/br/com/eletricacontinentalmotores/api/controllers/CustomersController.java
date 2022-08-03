package br.com.eletricacontinentalmotores.api.controllers;

import br.com.eletricacontinentalmotores.api.models.CustomerModel;
import br.com.eletricacontinentalmotores.api.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    @Autowired
    public CustomerRepository customerRepository;

    @GetMapping
    public ResponseEntity<List<CustomerModel>> findAll(){
        List<CustomerModel> customers = customerRepository.findAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerModel> findById(@PathVariable("id") int id){
        Optional<CustomerModel> customer = customerRepository.findById(id);
        return ResponseEntity.of(customer);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CustomerModel customer){
        customerRepository.save(customer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customer.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody CustomerModel customer){
        customer.setId(id);

        customerRepository.save(customer);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        CustomerModel customer = new CustomerModel();
        customer.setId(id);

        customerRepository.delete(customer);

        return ResponseEntity.ok().build();
    }
}
