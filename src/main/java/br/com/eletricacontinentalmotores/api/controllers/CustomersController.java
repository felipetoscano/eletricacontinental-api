package br.com.eletricacontinentalmotores.api.controllers;

import br.com.eletricacontinentalmotores.api.models.CustomerModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    @GetMapping
    public ResponseEntity<CustomerModel[]> findAll(){
        CustomerModel[] customers = new CustomerModel[]{
                new CustomerModel("Felipe Toscano")
        };

        return ResponseEntity.ok(customers);
    }
}
