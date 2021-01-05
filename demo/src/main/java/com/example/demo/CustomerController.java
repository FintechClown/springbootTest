package com.example.demo;

import com.example.demo.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/")
public class CustomerController {
    //拿來連接h2 db的repo interface，要繼承原本的方法，詳見CustomerRepository.java
    @Autowired
    private CustomerRepository customerRepository;
    
    //Post一筆新data
    @PostMapping(path = "/customer")
    public @ResponseBody
    String addNewUser(@RequestParam String fname, @RequestParam String lname){
        Customer c = new Customer(fname,lname);
        customerRepository.save(c);
        return "Saved";
    }
    //用get方法列出所有data
    @GetMapping(path ="/customer")
    public @ResponseBody Iterable<Customer>getAllUsers(){
        return customerRepository.findAll();
    }
    //用get方法尋找特定data
    @GetMapping(path ="/api/customers/{id}")
    @ResponseBody
    public String getCustomerById(@PathVariable(name = "id") long id){
        Customer c = customerRepository.findById(id);
        /*
        long Cid = c.getId();
        String Lname = c.getLastName();
        String Fname = c.getFirstName();
        */
        return c.toString() ;
    }
    
    //用post方法修改data
    @PostMapping(path ="/api/customers/{id}")
    @ResponseBody
    //這裡的pathVar可以抓以網址input的特定參數(id)，新的fname跟lname我放在post本體
    public String updateCustomerById(@PathVariable(name = "id") long id,@RequestParam String fname, @RequestParam String lname){
        Customer c = customerRepository.findById(id);
        c.setFirstName(fname);
        c.setLastName(lname);
        customerRepository.save(c);
        return "Updated";
    }
}

