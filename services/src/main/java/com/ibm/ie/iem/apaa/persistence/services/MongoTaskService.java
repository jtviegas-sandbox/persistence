/**
 * 
 */
package com.ibm.ie.iem.apaa.persistence.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ibm.ie.iem.apaa.model.Task;
import com.ibm.ie.iem.apaa.model.dto.mongo.TaskDto;
import com.ibm.ie.iem.apaa.persistence.repositories.mongo.TaskMongoRepository;

/**
 * @author joao
 * 
 */
@Service
public class MongoTaskService implements TaskService {

	@Autowired
	private TaskMongoRepository taskMongoRepository;
	private BoundMapperFacade<TaskDto, Task> mapper;

	class TaskConverter extends BidirectionalConverter<TaskDto,Task> {

		@Override
		public Task convertTo(TaskDto source, Type<Task> destinationType) {
			
			Task o = new Task();
			
			o.setId(source.getId());
			o.setHost(source.getHost());
			o.setEnd(source.getEnd());
			o.setName(source.getName());
			o.setStart(source.getStart());
			o.setStatus(source.getStatus());
			
			return o;
		}

		@Override
		public TaskDto convertFrom(Task source, Type<TaskDto> destinationType) {
			
			TaskDto o = new TaskDto();
			
			if(null != source.getId())
				o.setObjectId(new ObjectId(source.getId()));
			
			o.setHost(source.getHost());
			o.setEnd(source.getEnd());
			o.setName(source.getName());
			o.setStart(source.getStart());
			o.setStatus(source.getStatus());
			
			return o;
		}
		
	}
	
	public MongoTaskService() {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
				.build();
		ConverterFactory converterFactory = mapperFactory.getConverterFactory();
		converterFactory.registerConverter(new TaskConverter());
		
		mapperFactory.classMap(TaskDto.class, Task.class).byDefault()

				.register();
		mapper = mapperFactory.getMapperFacade(TaskDto.class, Task.class);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.ie.iem.apaa.persistence.services.mongo.QuoteService#findAll(org.
	 * springframework.data.domain.Sort)
	 */

	public Iterable<Task> findAll(Sort sort) {
		return dto2model(taskMongoRepository.findAll(sort));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.ie.iem.apaa.persistence.services.mongo.QuoteService#findAll(org.
	 * springframework.data.domain.Pageable)
	 */

	public Page<Task> findAll(Pageable pageable) {
		Iterator<TaskDto> it = taskMongoRepository.findAll(pageable)
				.iterator();
		List<Task> list = new ArrayList<Task>();
		while (it.hasNext())
			list.add(dto2model(it.next()));

		return new PageImpl<Task>(list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.ie.iem.apaa.persistence.services.mongo.QuoteService#save(S)
	 */

	public Task save(Task entity) {
		return dto2model(taskMongoRepository.save(model2dto(entity)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibm.ie.iem.apaa.persistence.services.mongo.QuoteService#save(java.lang.Iterable
	 * )
	 */

	public Iterable<Task> save(Iterable<Task> entities) {
		return dto2model(taskMongoRepository.save(model2dto(entities)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibm.ie.iem.apaa.persistence.services.mongo.QuoteService#exists(org.bson.
	 * types.ObjectId)
	 */

	public boolean exists(String id) {
		return taskMongoRepository.exists(new ObjectId(id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.ie.iem.apaa.persistence.services.mongo.QuoteService#findAll()
	 */

	public Iterable<Task> findAll() {
		return dto2model(taskMongoRepository.findAll());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibm.ie.iem.apaa.persistence.services.mongo.QuoteService#findAll(java.lang
	 * .Iterable)
	 */

	public Iterable<Task> findAll(Iterable<String> ids) {
		return dto2model(taskMongoRepository.findAll(id2ObjectId(ids)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.ie.iem.apaa.persistence.services.mongo.QuoteService#count()
	 */

	public long count() {
		return taskMongoRepository.count();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibm.ie.iem.apaa.persistence.services.mongo.QuoteService#delete(org.bson.
	 * types.ObjectId)
	 */

	public void delete(String id) {
		taskMongoRepository.delete(new ObjectId(id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibm.ie.iem.apaa.persistence.services.mongo.QuoteService#delete(org.aprestos
	 * .labs.ee.domainmodel.quotes.Quote)
	 */

	public void delete(Task entity) {
		taskMongoRepository.delete(model2dto(entity));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibm.ie.iem.apaa.persistence.services.mongo.QuoteService#delete(java.lang
	 * .Iterable)
	 */

	public void delete(Iterable<Task> entities) {
		taskMongoRepository.delete(model2dto(entities));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.ie.iem.apaa.persistence.services.mongo.QuoteService#deleteAll()
	 */

	public void deleteAll() {
		taskMongoRepository.deleteAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ibm.ie.iem.apaa.persistence.services.mongo.QuoteService#findOne(org.bson
	 * .types.ObjectId)
	 */

	public Task findOne(String id) {
		return dto2model(taskMongoRepository.findOne(new ObjectId(id)));
	}

	protected Object clone() throws CloneNotSupportedException {
		return new CloneNotSupportedException();
	}

	Iterable<TaskDto> model2dto(Iterable<Task> ita) {

		Iterator<Task> it = ita.iterator();
		List<TaskDto> list = new ArrayList<TaskDto>();
		while (it.hasNext())
			list.add(model2dto(it.next()));

		return list;
	}

	Iterable<Task> dto2model(Iterable<TaskDto> ita) {
		Iterator<TaskDto> it = ita.iterator();
		List<Task> list = new ArrayList<Task>();
		while (it.hasNext())
			list.add(dto2model(it.next()));

		return list;
	}
	
	Task dto2model(TaskDto o) {
		return mapper.map(o);
	}
	
	TaskDto model2dto(Task o) {
		return mapper.mapReverse(o);
	}

	Iterable<ObjectId> id2ObjectId(Iterable<String> ids) {

		List<ObjectId> objids = new ArrayList<ObjectId>();
		while (ids.iterator().hasNext())
			objids.add(new ObjectId(ids.iterator().next()));

		return objids;
	}

}
