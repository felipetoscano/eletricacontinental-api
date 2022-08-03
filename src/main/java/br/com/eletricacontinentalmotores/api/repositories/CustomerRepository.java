package br.com.eletricacontinentalmotores.api.repositories;

import br.com.eletricacontinentalmotores.api.models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerModel, Integer> {

}
