package com.springbBoot.performanceAnalyticsSystem.Model;

import jakarta.persistence.*;

@Entity
public class Physics
{
    public Integer getAttemptNumber() {
        return attemptNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attemptNumber;

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public Student getStu() {
        return stu;
    }

    public void setStu(Student stu) {
        this.stu = stu;
    }

    private Integer marks;
    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student stu;
}
