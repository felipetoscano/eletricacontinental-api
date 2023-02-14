package br.com.eletricacontinentalmotores.api.controllers;

import br.com.eletricacontinentalmotores.api.models.CustomerModel;
import br.com.eletricacontinentalmotores.api.repositories.CustomerRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/customers", produces = "application/json")
public class CustomersController {

    @Autowired
    public CustomerRepository customerRepository;

    @GetMapping
    @ApiOperation(value = "Retorna a lista de todos os clientes")
    public ResponseEntity<List<CustomerModel>> findAll(){
        List<CustomerModel> customers = customerRepository.findAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna um cliente de acordo com o ID")
    public ResponseEntity<CustomerModel> findById(@PathVariable("id") int id){
        Optional<CustomerModel> customer = customerRepository.findById(id);
        return ResponseEntity.of(customer);
    }

    @PostMapping
    @ApiOperation(value = "Cadastra um novo cliente")
    public ResponseEntity<Void> create(@RequestBody @Valid CustomerModel customer){
        customerRepository.save(customer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customer.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Altera um cliente de acordo com o ID")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody @Valid CustomerModel customer){
        customer.setId(id);

        customerRepository.save(customer);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta um cliente de acordo com o ID")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        CustomerModel customer = new CustomerModel();
        customer.setId(id);

        customerRepository.delete(customer);

        return ResponseEntity.ok().build();
    }
}
