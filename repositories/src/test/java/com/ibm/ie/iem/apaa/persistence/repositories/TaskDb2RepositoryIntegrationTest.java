package com.ibm.ie.iem.apaa.persistence.repositories;

import java.util.Iterator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ibm.ie.iem.apaa.model.db2.TaskDto;
import com.ibm.ie.iem.apaa.persistence.repositories.config.DB2Config;
import com.ibm.ie.iem.apaa.persistence.repositories.db2.TaskDb2Repository;

/**
 * Unit test for simple App.
 */
public class TaskDb2RepositoryIntegrationTest {

	private static final int NUM_OF_ADDITIONS = 2;
	
	AnnotationConfigApplicationContext context;

	@Test
	public void testDocSizeTwoAdditionsToDB() {
		
		
		TaskDb2Repository repository = (TaskDb2Repository) context
				.getBean(TaskDb2Repository.class);
		
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
		
		TaskDb2Repository rep = (TaskDb2Repository) context
				.getBean(TaskDb2Repository.class);
		TaskDto taskExpected = rep.save(new TaskDto("dosomething", "o baby please do something"));
		TaskDto actualTask = rep.findOne(taskExpected.getId());
		Assert.assertEquals(taskExpected, actualTask);

	}


	@Before
	public void setUp() throws Exception {
		context = new AnnotationConfigApplicationContext();
		context.register(DB2Config.class);
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
