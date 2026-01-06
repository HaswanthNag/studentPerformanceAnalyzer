package com.springbBoot.performanceAnalyticsSystem.Dto;

public class SubjectStudent
{
    private Integer studentId;

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }




    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    private Integer marks;

    private String studentName;


    public SubjectStudent(Integer studentId, Integer marks,  String studentName) {
        this.studentId = studentId;
        this.marks = marks;
        this.studentName = studentName;
    }
}
