package de.atomspace.webapp.component.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.atomspace.webapp.component.unit.Unit;
import de.atomspace.webapp.component.unit.service.UnitRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:repository-integration-tests-context.xml")
public class UnitRepositoryIntegrationTest {
	
	@Autowired
	MongoOperations operations;

	@Autowired
	UnitRepository repository;

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
		entity = repository.save(entity);
		assertNotNull(entity.getId());
		
		Unit entity2 = new Unit();
		entity2.setName("cm");
		entity2.setDetached(true);
		assertNull(entity2.getId());
		entity2 = repository.save(entity2);
		assertNotNull(entity2.getId());
		
		List<Unit> listUnit;
		listUnit = repository.findByDetached(false);
		assertEquals(1,listUnit.size());
		
		Page<Unit> pageUnit = repository.findByDetached(new PageRequest(0, 5),false);
		assertEquals(1,pageUnit.getContent().size());
		
		
		repository.delete(entity.getId());
		entity = repository.findOne(entity.getId());
		assertNull(entity);
		
	}

}
