package de.atomspace.webapp.component.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.atomspace.webapp.component.action.Action;
import de.atomspace.webapp.component.action.service.ActionRepository;
import de.atomspace.webapp.component.dimension.service.DimensionRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:repository-integration-tests-context.xml")
public class ActionRepositoryIntegrationTest {
	
	@Autowired
	MongoOperations operations;

	@Autowired
	ActionRepository actionRepository;
	
	@Autowired
	DimensionRepository repository2;

	
	@Before
	public void setUp() {
		operations.remove(new Query(new Criteria()) , Action.class);
	}


	@Test
	public void createAction() throws Exception {
		
		Action action1 = new Action();
		action1.setName("pour");
		action1.setDetached(false);
		action1.setPublished(true);
		action1.setDescription("");
		assertNull(action1.getId());
		action1 = actionRepository.save(action1);
		assertNotNull(action1.getId());
	}
	
	
	@Test(expected=DuplicateKeyException.class)
	public void duplicateAction() throws Exception {
		Action action1 = new Action();
		action1.setName("mix");
		action1 = actionRepository.save(action1);

		Action action2 = new Action();
		action2.setName("mix");
		action2 = actionRepository.save(action2);
	}
	
	

}
