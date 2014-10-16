package com.ibm.ie.iem.apaa.persistence.repositories;

import java.util.Iterator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ibm.ie.iem.apaa.model.dto.mongo.TaskDto;
import com.ibm.ie.iem.apaa.persistence.repositories.config.Config;
import com.ibm.ie.iem.apaa.persistence.repositories.mongo.TaskMongoRepository;

/**
 * Unit test for simple App.
 */
public class TaskMongoRepositoryIntegrationTest {

	private static final int NUM_OF_ADDITIONS = 2;
	
	AnnotationConfigApplicationContext context;

	@Test
	public void testDocSizeTwoAdditionsToDB() {
		
		
		TaskMongoRepository repository = (TaskMongoRepository) context
				.getBean(TaskMongoRepository.class);
		
		Iterator<TaskDto> it = repository.findAll().iterator();
		int initialCount = countIteratorSize(it);
		
		for(int i = 0 ; i < NUM_OF_ADDITIONS ; i++)
			repository.save(new TaskDto("dosomething", "o baby please do something"));
		
		it = repository.findAll().iterator();
		int finalCount = countIteratorSize(it);
		
		Assert.assertEquals(initialCount + NUM_OF_ADDITIONS, finalCount);

	}
	
	
	@Test
	public void testDocSaved() {
		
		TaskMongoRepository rep = (TaskMongoRepository) context
				.getBean(TaskMongoRepository.class);
		TaskDto taskExpected = rep.save(new TaskDto("dosomething", "o baby please do something"));
		TaskDto actualTask = rep.findOne(taskExpected.getObjectId());
		Assert.assertEquals(taskExpected, actualTask);

	}


	@Before
	public void setUp() throws Exception {
		context = new AnnotationConfigApplicationContext();
		context.register(Config.class);
		context.refresh();
	}


	@After
	public void tearDown() throws Exception {
		context = null;
	}
	
	private int countIteratorSize( Iterator<?> it){
		int result = 0;
		
		while(it.hasNext()){
			it.next();
			result++;
		}
		
		return result;
	}
	

}
