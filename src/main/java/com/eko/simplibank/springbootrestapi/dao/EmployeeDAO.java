package com.eko.simplibank.springbootrestapi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eko.simplibank.springbootrestapi.model.Employee;
import com.eko.simplibank.springbootrestapi.repository.EmployeeRepository;

@Service
public class EmployeeDAO {

	@Autowired
	EmployeeRepository employeeRepository;

	private SessionFactory sessionFactory;
	
	 @Autowired
	  public EmployeeDAO(EntityManagerFactory factory) {
	    if(factory.unwrap(SessionFactory.class) == null){
	      throw new NullPointerException("factory is not a hibernate factory");
	    }
	    this.sessionFactory = factory.unwrap(SessionFactory.class);
	  }
	 
	/*save employee*/
	public Employee save(Employee emp) {
		return employeeRepository.save(emp);
	}
	
	/*search all employees*/
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}
	
	
	/*get an employee*/
//	public Employee get(Long employeeId) {
//		return employeeRepository.findOne(employeeId);
//	}
	
	public List<Employee> findByName(String empName){

		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Employee.class)
				.add(Restrictions.like("name", empName+"%"));
			  return (List<Employee>) criteria.list();
	}

	public Employee get(Long employeeId) {
		return employeeRepository.findOne(employeeId);
	}
	
	/*delete an employee*/
	public void delete(Employee emp) {
		employeeRepository.delete(emp);
	}

	@PersistenceContext
    private EntityManager entityManger;

//    public List<Employee> findAllEmployee(){
//        return employeeRepository.createQuery("select e from Employee e", Employee.class).getResultList();
//    }
}
