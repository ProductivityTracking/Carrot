package com.nkxgen.spring.orm.model;

public class ModuleInput {
	private String module_name;
	private String module_desc;
	private int module_proj_id;
	
	
	public int getModule_proj_id() {
		return module_proj_id;
	}
	public void setModule_proj_id(int module_proj_id) {
		this.module_proj_id = module_proj_id;
	}
	
	public String getModule_name() {
		return module_name;
	}
	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}
	public String getModule_desc() {
		return module_desc;
	}
	public void setModule_desc(String module_desc) {
		this.module_desc = module_desc;
	}
	
public Module toEntity() {
	Module module = new Module();
	module.setName(this.module_name);
	module.setDescription(this.module_desc);
	Project project = new Project();
	project.setProjectId((short)this.module_proj_id);
	module.setProject(project);
	return module;
}
}
