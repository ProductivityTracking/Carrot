package com.nkxgen.spring.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.nkxgen.spring.orm.model.FunctionalTask;
import com.nkxgen.spring.orm.model.FunctionalUnit;
import com.nkxgen.spring.orm.model.FunctionalUnitdto;
import com.nkxgen.spring.orm.model.ModuleDTO;
import com.nkxgen.spring.orm.model.ProjectDto;
import com.nkxgen.spring.orm.model.Sprint;
import com.nkxgen.spring.orm.model.SprintInput;
import com.nkxgen.spring.orm.model.SprintResource;
import com.nkxgen.spring.orm.model.SprintResourceInput;
import com.nkxgen.spring.orm.model.SprintTasks;
import com.nkxgen.spring.orm.model.SprintTasksInput;
import com.nkxgen.spring.orm.model.Task;
import com.nkxgen.spring.orm.model.TaskDto;
import com.nkxgen.spring.orm.model.TaskInput;
import com.nkxgen.spring.orm.model.User;
import com.nkxgen.spring.orm.service.ModuleService;
import com.nkxgen.spring.orm.service.ProjectService;
import com.nkxgen.spring.orm.service.SprintService;
import com.nkxgen.spring.orm.service.TaskService;

@Controller
public class SprintController {

	SprintService sd;
	ProjectService pd;
	ModuleService md;
	TaskService taskService;
	static int sprintid=0;

	@Autowired
	public SprintController(SprintService sprintService, ProjectService projectService, ModuleService ModuleService,
			TaskService taskService) {
		super();
		this.sd = sprintService;
		pd = projectService;
		this.md = ModuleService;
		this.taskService = taskService;

	}

	@RequestMapping(value = "/ShowFunctionalUnits", method = RequestMethod.POST)
	public String createTask(@Validated SprintInput sprintInput,
			@ModelAttribute SprintResourceInput SprintResourceInput, Model model) throws ParseException {
		   Sprint s=sd.storeSprint(sprintInput.toEntity());
	   SprintResource sr=SprintResourceInput.toEntity();
	    sprintid=s.getSprintId();
	     sr.setSprintId(s.getSprintId());
		sd.storeSprintResource(sr);
		System.out.println("Create Tasks Requested" + sprintInput);

		List<FunctionalUnit> flist = sd.getFunctionalUnitsByModId(sprintInput.getModuleId());
		List<FunctionalUnitdto> funlistDto = new ArrayList<>();

		for (FunctionalUnit functionalUnit : flist) {
			FunctionalUnitdto funUnitDto = FunctionalUnitdto.fromEntity(functionalUnit);
			funlistDto.add(funUnitDto);
		}

		model.addAttribute("funlist", funlistDto);
		model.addAttribute("pro_id", sprintInput.getProjectId());
		return "ShowFunctionalUnits";
	}

	@RequestMapping(value = "/ShowFunUnits", method = RequestMethod.POST)
	public String showFunctionalUnits(@RequestParam("modid") int modid, @RequestParam("prodid") int prodid, Model model)
			throws ParseException {
		List<FunctionalUnit> flist = sd.getFunctionalUnitsByModId(modid);
		List<FunctionalUnitdto> funlistDto = new ArrayList<>();

		for (FunctionalUnit functionalUnit : flist) {
			FunctionalUnitdto funUnitDto = FunctionalUnitdto.fromEntity(functionalUnit);
			funlistDto.add(funUnitDto);
		}
		model.addAttribute("funlist", funlistDto);
		model.addAttribute("pro_id", prodid);
		return "ShowFunctionalUnits";
	}

	@RequestMapping(value = "/sprint", method = RequestMethod.GET)
	public String sprint(Model model) {
		System.out.println("project called");
		List<Sprint> allSprints = sd.getAllSprints();
		model.addAttribute("allSprints", allSprints);
		System.out.println(allSprints);
		return "sprint_home";
	}

//	@RequestMapping(value = "/sprint_details", method = RequestMethod.GET)
//	public String getSprintDetails(Model model, @RequestParam int sprintId) {
//		// System.out.println("Sprint Details JSP Requested");
//		Sprint sprint = sd.getSprintDetails(sprintId);
//		// Retrieve the selected sprint details from the database and add them to the model
//		model.addAttribute("sprint", sprint);
//
//		List<SprintTasks> tasksByIdSprints = sd.getAllTasksBySprintId(sprintId);
//		model.addAttribute("tasksByIdSprints", tasksByIdSprints);
//		return "sprint_details";
//	}
	
	@RequestMapping(value = "/sprint_details", method = RequestMethod.GET)
	public String getSprintDetails(Model model, @RequestParam int sprintId) {
	    Sprint sprint = sd.getSprintDetails(sprintId);
	    model.addAttribute("sprint", sprint);
        Sprint s=new Sprint();
        s.setSprintId(sprintId);
	    List<SprintTasks> tasksByIdSprints = sd.getAllTasksBySprintId(s);
	    model.addAttribute("tasksByIdSprints", tasksByIdSprints);
	    return "sprint_details";
	}

	// @RequestMapping(value = "/projectDetails", method = RequestMethod.GET)
	// public String getProjectDetails(Model model) {
	// // System.out.println("Sprint Details JSP Requested");
	//
	// // Retrieve the selected sprint details from the database and add them to the model
	//
	// return "projectDetails";
	// }

	@RequestMapping(value = "/add_sprint", method = RequestMethod.GET)
	public String addSprint(Model model) {
		List<ProjectDto> pl = pd.getAllProjects();
		model.addAttribute("projects", pl);
		List<User> lu = sd.getAllUsers();
		model.addAttribute("users", lu);

		return "add_sprint";
	}

	@RequestMapping(value = "/FunctionalUnit", method = RequestMethod.GET)
	public String addSprint() {
		// System.out.println("functional unit jsp");

		// Add any necessary data to the model for rendering the add sprint page

		return "FunctionalUnit";
	}

	@RequestMapping(value = "/SubTaskdetails", method = RequestMethod.GET)
	public String SubtaskDetails() {
		// System.out.println("Subtask Details requested");
		return "SubtaskDetails";
	}

	@RequestMapping(value = "/CreateSubTask", method = RequestMethod.GET)
	public String CreateSubtask() {

		return "CreateSubtask";
	}

	@RequestMapping(value = "/backlog", method = RequestMethod.GET)
	public String pastdue(Model model) {
		ArrayList<Sprint> SprintList = (ArrayList<Sprint>) sd.getBacklogs();

		model.addAttribute("sprintList", SprintList);
		return "backlog";
	}

	@RequestMapping(value = "/BacklogTasks", method = RequestMethod.GET)
	public String getBacklogTasks(Model model, @RequestParam("sprnModlId") int sprnModlId,
			@RequestParam("sprnId") int sprnId) {

		Sprint sprint = sd.getSprintDetails(sprnId);
		List<Task> taskList = sd.getTasks(sprnModlId);
		model.addAttribute("sprint", sprint);
		model.addAttribute("taskList", taskList);
		return "BacklogTasks";
	}

	@ResponseBody
	@RequestMapping(value = "/getModuleById", method = RequestMethod.POST, produces = "application/json")
	public String getModuleById(@RequestParam("projectId") int projectId) {
		System.out.println("Inside");
		List<ModuleDTO> moduleList = sd.getSprintModulesByProjectId(projectId);
		System.out.println("moduleList" + moduleList.get(0));
		Gson gson = new Gson();
		String json = gson.toJson(moduleList);
		return json;
	}

	@RequestMapping(value = "/Task", method = RequestMethod.POST)
	public String createTask(@ModelAttribute FunctionalTask ft, Model model) {
		System.out.println("Create Task");
		model.addAttribute("funtask", ft);
		System.out.println("tasksare" + ft);
		List<User> lu = sd.getAllUsers();
		model.addAttribute("users", lu);
		List<TaskDto> tasks = taskService.getAllTasks();
		model.addAttribute("tasks", tasks);
		return "Task";
	}

	@RequestMapping(value = "/TaskAdded", method = RequestMethod.POST)
	public String TaskAdded(@ModelAttribute TaskInput taskInput, @ModelAttribute SprintTasksInput sprintTasksInput,

			Model model) {
		System.out.println("task" + taskInput);
		Task t=sd.storeTask(taskInput.toEntity());
		System.out.println("question"+sprintTasksInput);
		sprintTasksInput.setSprintId(sprintid);
		sprintTasksInput.setTaskId(t.getTaskId());
		
		System.out.println("fellow"+t.getTaskSupervisor().getUserId());
		sprintTasksInput.setUserId(t.getTaskSupervisor().getUserId());
		SprintTasks st;
		st=sprintTasksInput.toEntity();
		sd.storeSprintTasks(st);
		System.out.println(st+"checkcc");
		
		return "TaskAdded";
	}
}
