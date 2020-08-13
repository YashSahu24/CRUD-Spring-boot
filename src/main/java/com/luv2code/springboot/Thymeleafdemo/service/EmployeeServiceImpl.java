package com.luv2code.springboot.Thymeleafdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.Thymeleafdemo.dao.EmployeeRepository;
import com.luv2code.springboot.Thymeleafdemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRep;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository theEmployeeRep)
	{
		employeeRep = theEmployeeRep;
	}
	
	@Override
	@Transactional
	public List<Employee> findAll() {
		return employeeRep.findAllByOrderByLastNameAsc();
	}

	@Override
	@Transactional
	public Employee findById(int theId) {
		Optional<Employee> result = employeeRep.findById(theId);
		Employee theEmployee=null;
		if(result.isPresent())
		{
			theEmployee=result.get();
		}
		else
		{
			throw new RuntimeException("Employee not found of id : "+theId);
		}
		return theEmployee;
	}

	@Override
	@Transactional
	public void save(Employee theEmployee) {
        employeeRep.save(theEmployee);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
         employeeRep.deleteById(theId);
       
	}

}
