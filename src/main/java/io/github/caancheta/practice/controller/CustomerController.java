/**
 * 
 */
package io.github.caancheta.practice.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.caancheta.practice.entity.Customer;
import io.github.caancheta.practice.service.CustomerService;

/**
 * @author Christopher
 *
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

	static final Logger logger = LogManager.getLogger(CustomerController.class);

	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "/findall", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Customer>> findall() {
		logger.info("Fetching all customers");
		List<Customer> customers = customerService.getAll();
		if(customers.isEmpty()){
			logger.error("No Customers Found");
			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}

	@RequestMapping(value = "/find/{name}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Customer> findByName(@PathVariable("name") String name) {
		logger.info("Fetching all customer with name: " + name);
		List<Customer> customers = customerService.getAllByName(name);
		if(customers.isEmpty()){
			logger.error("Cannot find customer with name: " + name);
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		logger.info("Found customer with name: " + name);
		return new ResponseEntity<Customer>(customers.get(0), HttpStatus.OK);

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Void> create(@RequestBody Customer customer) {
		List<Customer> existingCustomersWtihName = customerService.getAllByName(customer.getName());
		if (!existingCustomersWtihName.isEmpty()) {
			logger.error("Customer already exists in database");
			return new ResponseEntity<Void>(HttpStatus.FOUND);
		}
		logger.info("Creating new customer");
		customerService.create(customer);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

}
