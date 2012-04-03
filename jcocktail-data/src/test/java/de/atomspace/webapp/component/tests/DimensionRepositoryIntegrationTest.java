package de.atomspace.webapp.component.tests;

import java.math.BigDecimal;

import static org.junit.Assert.*;

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


import de.atomspace.webapp.component.dimension.Dimension;
import de.atomspace.webapp.component.dimension.DimensionRepository;
import de.atomspace.webapp.component.dimension.DimensionRow;
import de.atomspace.webapp.component.unit.Unit;
import de.atomspace.webapp.component.unit.service.UnitRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:repository-integration-tests-context.xml")
public class DimensionRepositoryIntegrationTest {
	
	@Autowired
	MongoOperations operations;

	@Autowired
	UnitRepository repository1;
	
	@Autowired
	DimensionRepository repository2;

	
	@Before
	public void setUp() {
		operations.remove(new Query(new Criteria()) , Unit.class);
		operations.remove(new Query(new Criteria()) , Dimension.class);
	}


	@Test
	public void createUnit() throws Exception {
		
		Unit entity = new Unit();
		entity.setName("kg");
		entity.setDetached(false);
		entity.setPublished(true);
		assertNull(entity.getId());
		entity = repository1.save(entity);
		assertNotNull(entity.getId());
		
		Unit entity2 = new Unit();
		entity2.setName("g");
		entity2.setDetached(false);
		entity2.setPublished(true);
		assertNull(entity2.getId());
		entity2 = repository1.save(entity2);
		assertNotNull(entity2.getId());
		
		Dimension entity3 = new Dimension();
		entity3.setDetached(false);
		entity3.setPublished(true);
		entity3.setName("Mass");
		entity3.setUnit(entity);
		entity3.getDimensionRows().add(new DimensionRow(entity2, new BigDecimal(1000)));
		assertNull(entity3.getId());
		entity3 = repository2.save(entity3);
		assertNotNull(entity3.getId());
		
		Iterable<Dimension> list = repository2.findAll();
		int i=0;
		for (Dimension dimension : list) {
			assertNotNull(dimension);
			i++;
		}
		assertEquals(1, i);
		
	}
	
	@Test(expected=DuplicateKeyException.class)
	public void duplicateDimension() throws Exception {
		Dimension dimension001 = new Dimension();
		dimension001.setName("AAA");
		dimension001 = repository2.save(dimension001);

		Dimension dimension002 = new Dimension();
		dimension002.setName("AAA");
		dimension002 = repository2.save(dimension002);
	}
	
	

}
