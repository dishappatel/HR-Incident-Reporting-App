package com.example.myapplication.Model;

public class Employee {
    private Integer Id;
    private String Title;
    private String IncidentDate;
    private String Emp_Number;
    private String Emp_Name;
    private String Gender;
    private String Shift;
    private  String Emp_Department;
    private  String Emp_Position;
    private  String Incident_Type;
    private  String Injured_Body_Part;


    public Employee(){}

    public  Employee(Integer id,String title,String incidentdate,String emp_number,String emp_name,String gender,String shift,String emp_department,String emp_position,String incident_type,String injuredbodypart)
    {
        this.Id = id;
        this.Title = title;
        this.IncidentDate = incidentdate;
        this.Emp_Number = emp_number;
        this.Emp_Name = emp_name;
        this.Gender = gender;
        this.Shift = shift;
        this.Emp_Department = emp_department;
        this.Emp_Position = emp_position;
        this.Incident_Type = incident_type;
        this.Injured_Body_Part = injuredbodypart;
    }

    public Integer getId(){
        return  this.Id;
    }
    public String getTitle(){
        return  this.Title;
    }
    public String getIncidentDate(){
        return  this.IncidentDate;
    }
    public String getEmp_Number(){
        return  this.Emp_Number;
    }

    public String getEmp_Name() {
        return Emp_Name;
    }

    public String getGender() {
        return Gender;
    }

    public String getShift() {
        return Shift;
    }

    public String getEmp_Department() {
        return Emp_Department;
    }

    public String getEmp_Position() {
        return Emp_Position;
    }

    public String getIncident_Type() {
        return Incident_Type;
    }

    public String getInjured_Body_Part() {
        return Injured_Body_Part;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setIncidentDate(String incidentDate) {
        IncidentDate = incidentDate;
    }

    public void setEmp_Number(String emp_Number) {
        Emp_Number = emp_Number;
    }

    public void setEmp_Name(String emp_Name) {
        Emp_Name = emp_Name;
    }

    public void setEmp_Department(String emp_Department) {
        Emp_Department = emp_Department;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setShift(String shift) {
        Shift = shift;
    }

    public void setEmp_Position(String emp_Position) {
        Emp_Position = emp_Position;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setIncident_Type(String incident_Type) {
        Incident_Type = incident_Type;
    }

    public void setInjured_Body_Part(String injured_Body_Part) {
        Injured_Body_Part = injured_Body_Part;
    }
}
