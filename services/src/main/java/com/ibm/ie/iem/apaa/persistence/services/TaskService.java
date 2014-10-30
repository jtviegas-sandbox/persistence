package com.ibm.ie.iem.apaa.persistence.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ibm.ie.iem.apaa.model.Task;

public interface TaskService {

	public abstract Iterable<Task> findAll(Sort sort);

	public abstract Page<Task> findAll(Pageable pageable);

	public abstract Task save(Task entity);

	public abstract Iterable<Task> save(Iterable<Task> entities);

	public abstract boolean exists(String id);

	public abstract Iterable<Task> findAll();

	public abstract Iterable<Task> findAll(Iterable<String> ids);

	public abstract long count();

	public abstract void delete(String id);

	public abstract void delete(Task entity);

	public abstract void delete(Iterable<Task> entities);

	public abstract void deleteAll();

	public abstract Task findOne(String id);

}