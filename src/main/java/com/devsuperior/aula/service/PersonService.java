package com.devsuperior.aula.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.aula.dto.PersonDepartmentDTO;
import com.devsuperior.aula.entities.Department;
import com.devsuperior.aula.entities.Person;
import com.devsuperior.aula.repositories.DepartmentRepository;
import com.devsuperior.aula.repositories.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository repository;
	
	@Autowired
	private DepartmentRepository departamentRepository;

/* primeiro, transformo um DTO em uma entidade para inserir no banco
 * segundo, como as entidades são associadas eu busco no banco 
 * o Id referente do departamento	
 */
	public PersonDepartmentDTO insert(PersonDepartmentDTO dto) {		
		Person entity = new Person();
		entity.setName(dto.getName());
		entity.setSalary(dto.getSalary());

		Department dept = departamentRepository.getReferenceById(dto.getDepartment().getId());
		// Department dept = new Department();
		//dept.setId(dto.getDepartment().getId());
		
		entity.setDepartment(dept);
		
		entity = repository.save(entity);
		return new PersonDepartmentDTO(entity);
		
	}
}
