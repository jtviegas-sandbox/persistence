package com.ibm.ie.iem.apaa.persistence.services;

import java.util.Iterator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ibm.ie.iem.apaa.model.Task;
import com.ibm.ie.iem.apaa.model.dto.mongo.TaskDto;
import com.ibm.ie.iem.apaa.persistence.services.config.Config;

/**
 * Unit test for simple App.
 */
public class MongoTaskServiceIntegrationTest {

	private static final int NUM_OF_ADDITIONS = 2;
	
	AnnotationConfigApplicationContext context;

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
	
	@Test
	public void testGetService() {
		TaskService service = (TaskService) context
				.getBean("mongoTaskService");
		Assert.assertNotNull(service);
	}
	
	@Test
	public void testMappingsOne() {
		MongoTaskService service = (MongoTaskService) context
				.getBean("mongoTaskService");
		Task model = new Task("dothingz", "192.168.1.1");
		TaskDto dto = new TaskDto("dothingz", "192.168.1.1");
		
		Assert.assertEquals(dto, service.model2dto(model));
		Assert.assertEquals(model, service.dto2model(dto));

	}
	
	@Test
	public void testDocSizeTwoAdditionsToDB() {
		
		
		TaskService service = (TaskService) context
				.getBean("mongoTaskService");
		
		Iterator<Task> it = service.findAll().iterator();
		int initialCount = countIteratorSize(it);
		
		for(int i = 0 ; i < NUM_OF_ADDITIONS ; i++)
			service.save(new Task("dothingz", "192.168.1.1"));
		
		it = service.findAll().iterator();
		int finalCount = countIteratorSize(it);
		
		Assert.assertEquals(initialCount + NUM_OF_ADDITIONS, finalCount);

	}
	
	
	
	@Test
	public void testDocSaved() {
		
		TaskService service = (TaskService) context
				.getBean("mongoTaskService");
		Task taskExpected = service.save(new Task("dothingz", "192.168.1.1"));
		Task taskActual = service.findOne(taskExpected.getId());
		Assert.assertEquals(taskExpected, taskActual);

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
