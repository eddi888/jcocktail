package de.atomspace.webapp.component.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.atomspace.webapp.component.unit.Unit;
import de.atomspace.webapp.component.unit.service.UnitService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:service-integration-tests-context.xml")
public class UnitServiceIntegrationTest {
	
	@Autowired
	MongoOperations operations;

	@Autowired
	UnitService service;

	@Before
	public void setUp() {

		operations.remove(new Query(new Criteria()) , Unit.class);
		

	}


	
	@Test
	public void createUnit() throws Exception {
		
		Unit entity = new Unit();
		entity.setName("m");
		entity.setDetached(false);
		entity.setPublished(true);
		assertNull(entity.getId());
		entity = service.save(entity);
		assertNotNull(entity.getId());
		
		Unit entity2 = new Unit();
		entity2.setName("cm");
		entity2.setDetached(false);
		assertNull(entity2.getId());
		entity2 = service.save(entity2);
		assertNotNull(entity2.getId());
		
		Page<Unit> pageUnit;
		pageUnit = service.findAll(new PageRequest(0, 5));
		assertEquals(2,pageUnit.getContent().size());
		
		
		
	}

}
