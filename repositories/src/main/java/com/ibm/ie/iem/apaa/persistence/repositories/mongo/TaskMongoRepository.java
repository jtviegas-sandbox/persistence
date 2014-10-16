/**
 * 
 */
package com.ibm.ie.iem.apaa.persistence.repositories.mongo;

import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.ibm.ie.iem.apaa.model.dto.mongo.TaskDto;



/**
 * @author jviegas
 * 
 */
@Component
public interface TaskMongoRepository extends PagingAndSortingRepository<TaskDto, ObjectId> {

	TaskDto findOne(ObjectId id);

}
