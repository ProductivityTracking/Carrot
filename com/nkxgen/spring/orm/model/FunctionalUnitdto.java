package com.nkxgen.spring.orm.model;


public class FunctionalUnitdto {
    private int modlId;
    private int funtId;
    private String funitDesc;
    private String funStatus;
    private int projectId;

    public FunctionalUnitdto() {
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

    public void setprojectId(int  proj_id) {
        this.funStatus = funStatus;
    }
    public int getprojectId() {
        return projectId;
    }

    public void setFunStatus(String funStatus) {
        this.funStatus = funStatus;
    }

    public static FunctionalUnitdto fromEntity(FunctionalUnit funit) {
        FunctionalUnitdto funitDto = new FunctionalUnitdto();
        
        funitDto.setModlId(funit.getId().getModule().getId());
        funitDto.setFuntId(funit.getId().getFunId());
        funitDto.setFunitDesc(funit.getDescription());
        FunctionalUnitId f=new FunctionalUnitId();
        funitDto.setFunStatus(funit.getFunStatus());
        funitDto.setModlId(funit.getId().getModule().getId());
        funitDto.setprojectId(funit.getProjectId().getProjectId());
        return funitDto;
    }
}
