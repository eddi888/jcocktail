package de.atomspace.webapp.component.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.atomspace.webapp.component.webuser.Webuser;
import de.atomspace.webapp.component.webuser.service.WebuserRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:repository-integration-tests-context.xml")
public class WebuserRepositoryIntegrationTest {
	
	@Autowired
	MongoOperations operations;

	@Autowired
	WebuserRepository repository;

	@Before
	public void setUp() {

		operations.remove(new Query(new Criteria()) , Webuser.class);
		

	}


	
	@Test
	public void createWebuser() throws Exception {
		Webuser entity = new Webuser();
		entity.setDetached(false);
		entity.setPublished(true);
		entity.setName("TESTNAME");
		entity.setUser("TESTUSER");
		entity.getRoles().add("ROLE_USER");
		assertNull(entity.getId());
		entity = repository.save(entity);
		assertNotNull(entity.getId());

		
	}

}
