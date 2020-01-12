package demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class Main {
	public static void main(String [] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPU");
		EntityManager em = emf.createEntityManager();
	
		
		DAOTest dao = new DAOTest(em);
		dao.createEmployee("Thaw Thaw", 250000, "HR");
		dao.createEmployee("Thuzar Thaw", 250000, "HR");
		dao.createEmployee("John", 400000, "Marketing");
		dao.createEmployee("Su Su", 450000, "HR");

//	    TypedQuery<Employee> query = em.createQuery("select e from Employee e",Employee.class);
//	    List<Employee> employees = query.getResultList();
		
		List<Employee> employees = em.createQuery("select e from Employee e where e.name like 'T%'",Employee.class).getResultList();
		employees = em.createQuery("select e from Employee e where e.department =?1",Employee.class).setParameter(1, "Marketing").getResultList();
		
		String query = "select e from Employee e where e.salary = (select max(e.salary) from Employee e)";
		Employee employee = em.createQuery(query,Employee.class).getSingleResult();
//		System.out.println(employee);
		
		query = "select e from Employee e where e.salary > :salary";
		employees = em.createQuery(query,Employee.class).setParameter("salary", 300000).getResultList(); 
		
		
		employees.stream().forEach(System.out::println);
		em.close();
		emf.close();
	}

}
