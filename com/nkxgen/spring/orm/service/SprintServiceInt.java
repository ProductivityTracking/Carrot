package com.nkxgen.spring.orm.service;

import java.util.List;

import com.nkxgen.spring.orm.model.FunctionalUnit;
import com.nkxgen.spring.orm.model.ModuleDTO;
import com.nkxgen.spring.orm.model.Sprint;
import com.nkxgen.spring.orm.model.SprintResource;
import com.nkxgen.spring.orm.model.SprintTasks;
import com.nkxgen.spring.orm.model.Task;
import com.nkxgen.spring.orm.model.User;

public interface SprintServiceInt {
	List<Sprint> getBacklogs();

	Sprint getSprintDetails(int sprintId);

	List<Task> getTasks(int modlId);

	List<Sprint> getAllSprints();

	List<SprintTasks> getAllTasksBySprintId(Sprint sprintId);

	Sprint storeSprint(Sprint sprint);

	List<ModuleDTO> getSprintModulesByProjectId(int projectId);

	List<FunctionalUnit> getFunctionalUnitsByModId(int modlId);

	public Task storeTask(Task task);

	public List<User> getAllUsers();

	public void storeSprintResource(SprintResource src);

	public void storeSprintTasks(SprintTasks sprintTask);
}