package io.github.caancheta.practice.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.caancheta.practice.dao.CustomerRepository;
import io.github.caancheta.practice.entity.Customer;

@Service
public class CustomerService {

	static final Logger logger = LogManager.getLogger(CustomerService.class);

	@Autowired
	CustomerRepository customerRepository;

	public List<Customer> getAll() {
		return customerRepository.findAll();

	}

	public List<Customer> getAllByName(String name) {
		return customerRepository.findByName(name);
	}

	public void create(Customer c) {
		customerRepository.save(c);
	}

}
