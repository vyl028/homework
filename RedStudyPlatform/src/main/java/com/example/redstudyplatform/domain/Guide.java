package com.example.redstudyplatform.domain;

public class Guide {
    private int guideID;//讲解员ID
    private Study study;//讲解员所带用户
    private Base_Administrator baseAdministrator;//讲解员所属基地
    private String name;//讲解员姓名
    private String contact;//联系方式
    private String employeeID;//讲解员工号
    private String joinTime;//入职时间

    public int getGuideID() {
        return guideID;
    }

    public void setGuideID(int guideID) {
        this.guideID = guideID;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public Base_Administrator getBaseAdministrator() {
        return baseAdministrator;
    }

    public void setBaseAdministrator(Base_Administrator baseAdministrator) {
        this.baseAdministrator = baseAdministrator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(String joinTime) {
        this.joinTime = joinTime;
    }

    @Override
    public String toString() {
        return "guide{" +
                "guideID=" + guideID +
                ", study=" + study +
                ", baseAdministrator=" + baseAdministrator +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", employeeID='" + employeeID + '\'' +
                ", joinTime='" + joinTime + '\'' +
                '}';
    }
}
