package demo;

import javax.persistence.EntityManager;

public class DAOTest {
	private EntityManager em;
	public DAOTest(EntityManager em) {
		super();
		this.em = em;
	}
	public void createEmployee(String name,double salary, String department) {
		em.getTransaction().begin();
		em.persist(new Employee(name,salary,department));
		em.getTransaction().commit();
	}

}
