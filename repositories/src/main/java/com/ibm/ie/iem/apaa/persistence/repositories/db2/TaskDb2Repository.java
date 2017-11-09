/**
 * 
 */
package com.ibm.ie.iem.apaa.persistence.repositories.db2;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.ibm.ie.iem.apaa.model.db2.TaskDto;




/**
 * @author jviegas
 * 
 */
@Component
public interface TaskDb2Repository extends PagingAndSortingRepository<TaskDto, Long> {

}
