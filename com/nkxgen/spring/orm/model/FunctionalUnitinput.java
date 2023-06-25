package com.nkxgen.spring.orm.model;


public class FunctionalUnitinput {
    private int modlId;
    private int funtId;
    private String funitDesc;
    private String funStatus;
    private int projectId;

    public FunctionalUnitinput() {
        // Default constructor
    }

    public int getModlId() {
        return modlId;
    }

    public void setModlId(int modlId) {
        this.modlId = modlId;
    }

    public int getFuntId() {
        return funtId;
    }

    public void setFuntId(int funtId) {
        this.funtId = funtId;
    }

    public String getFunitDesc() {
        return funitDesc;
    }

    public void setFunitDesc(String funitDesc) {
        this.funitDesc = funitDesc;
    }

    public String getFunStatus() {
        return funStatus;
    }

    public void setprojectId(int proj_id) {
        this.projectId = proj_id;
    }

    public int getprojectId() {
        return projectId;
    }

    public void setFunStatus(String funStatus) {
        this.funStatus = funStatus;
    }

    public FunctionalUnit toEntity() {
        FunctionalUnit functionalUnit = new FunctionalUnit();
        FunctionalUnitId id = new FunctionalUnitId();
        Module module = new Module();
        Project project = new Project();

        id.setFunId(this.funtId);
        module.setId(this.modlId);
       id.setModule(module);
        project.setProjectId(this.projectId);

        functionalUnit.setId(id);
        functionalUnit.setDescription(this.funitDesc);
        functionalUnit.setProjectId(project);
        functionalUnit.setFunStatus(this.funStatus);
       

        return functionalUnit;
    }

	@Override
	public String toString() {
		return "FunctionalUnitinput [modlId=" + modlId + ", funtId=" + funtId + ", funitDesc=" + funitDesc
				+ ", funStatus=" + funStatus + ", projectId=" + projectId + "]";
	}
}
