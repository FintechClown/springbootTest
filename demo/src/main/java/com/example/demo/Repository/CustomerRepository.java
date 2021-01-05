package com.example.demo.Repository;

import com.example.demo.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
    List<Customer> findByLastName(String LastName);
    Customer findById(long id);
}
