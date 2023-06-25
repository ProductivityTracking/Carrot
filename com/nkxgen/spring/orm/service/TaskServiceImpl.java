package com.nkxgen.spring.orm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nkxgen.spring.orm.dao.TaskDao;
import com.nkxgen.spring.orm.model.ResTaskFilter;
import com.nkxgen.spring.orm.model.TFilterCriteria;
import com.nkxgen.spring.orm.model.Task;
import com.nkxgen.spring.orm.model.TaskDto;

@Component
@Transactional
@Service
public class TaskServiceImpl implements TaskService {
	private final TaskDao taskDao;

	@Autowired
	public TaskServiceImpl(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	 
	@Override
	public List<TaskDto> filterTasks(ResTaskFilter  resTaskFilter) {
		return taskDao.filterTasks(resTaskFilter);
	}
	@Override
	public List<TaskDto> getTasksByUserId(int userId) {
		return taskDao.getTasksByUserId(userId);
	}

//	@Override
//	public List<TaskDto> PtfilterTasks(TFilterCriteria filterCriteria) {
//		return taskDao.taskDao(filterCriteria);
//	}

//	@Override
//	public List<Task> filterTasks(short projectFilter, String statusFilter, String categoryFilter, int userId) {
//		// TODO Auto-generated method stub
//		return taskDao.filterTasks(projectFilter, statusFilter, categoryFilter, userId);
//	}

	@Override
	public List<TaskDto> getAllTasks() {
		return taskDao.getAllTasks();
	}

	@Override

	public Task getTaskById(int taskId) {
		return taskDao.getTaskById(taskId);
	}

	@Override
	public Task saveTask(Task task) {
		return taskDao.saveTask(task);
	}

	@Override
	public Boolean updateStatus(int taskId) {
		// TODO Auto-generated method stub
		return taskDao.updateStatus(taskId);
	}
	// Implement other methods of the TaskService interface...


	@Override
	public List<TaskDto> PtfilterTasks(TFilterCriteria filterCriteria) {
		// TODO Auto-generated method stub
		return null;
	}
}