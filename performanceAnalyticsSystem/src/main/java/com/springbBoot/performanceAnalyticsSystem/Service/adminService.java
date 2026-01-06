package com.springbBoot.performanceAnalyticsSystem.Service;

import com.springbBoot.performanceAnalyticsSystem.Dto.StuSubHigh;
import com.springbBoot.performanceAnalyticsSystem.Dto.SubjectStudent;
import com.springbBoot.performanceAnalyticsSystem.Model.Maths;
import com.springbBoot.performanceAnalyticsSystem.Model.Physics;
import com.springbBoot.performanceAnalyticsSystem.Model.Student;

import com.springbBoot.performanceAnalyticsSystem.Repository.MathsRepo;
import com.springbBoot.performanceAnalyticsSystem.Repository.PhysicsRepo;
import com.springbBoot.performanceAnalyticsSystem.Repository.studentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class adminService
{
    @Autowired
    studentRepo jpa;



    public List<Student> getAll()
    {
        return jpa.findAll();
    }
    public void save(Student stu) {
        if (jpa.existsByEmail(stu.getEmail())) {
            throw new DataIntegrityViolationException("Email already exists");
        }
        jpa.save(stu);
    }
    public Optional<Student> getById(Integer id)
    {
        return jpa.findById(id);
    }
    public Student putById(Integer id,Student stu)
    {
        if(jpa.existsById(id))
        {
            Optional<Student> opStudent=jpa.findById(id);
            Student existingStudent=opStudent.get();
            if(stu.getPassword()!=null)
            {
                existingStudent.setPassword(stu.getPassword());
            }
            if(stu.getEmail()!=null)
            {
                existingStudent.setEmail(stu.getEmail());
            }
            if(stu.getCheMarks()>-1)
            {
                existingStudent.setCheMarks(stu.getCheMarks());
            }
            if(stu.getPyMarks()>-1)
            {
                existingStudent.setPyMarks(stu.getPyMarks());
            }
            if(stu.getMathMarks()>-1)
            {
                existingStudent.setMathMarks(stu.getMathMarks());
            }
            if(stu.getName()!=null)
            {
                existingStudent.setName(stu.getName());
            }
            return existingStudent;

        }
        return null;
    }
    public boolean deleteById(Integer id)
    {
        if(jpa.existsById(id))
        {
            jpa.deleteById(id);
            return true;
        }
        return false;
    }
    public Student getTop()
    {
        List<Student> toppp=jpa.findAll();
        if(toppp.isEmpty())
        {
            throw new RuntimeException("No students found");
        }
        int topMarks=Integer.MIN_VALUE;
        Student topStu=null;
        for(Student s:toppp)
        {
            int present;
            present=s.getCheMarks()+s.getPyMarks()+s.getMathMarks();
            if(topMarks<present)
            {
                topMarks=present;
                topStu=s;
            }
        }
        return topStu;
    }
    public Student getLeast()
    {
        List<Student> toppp=jpa.findAll();
        if(toppp.isEmpty())
        {
            throw new RuntimeException("No students found");
        }
        int topMarks=Integer.MAX_VALUE;
        Student topStu=null;
        for(Student s:toppp)
        {
            int present;
            present=s.getCheMarks()+s.getPyMarks()+s.getMathMarks();
            if(topMarks>present)
            {
                topMarks=present;
                topStu=s;
            }
        }
        return topStu;
    }
    @Autowired
    MathsRepo mathRepo;
    public Maths addMaths(Integer id,Integer marks)
    {
        Student student = jpa.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Maths maths = new Maths();
        maths.setMarks(marks);
        maths.setStu(student);
        student.setMathMarks(marks);

        return mathRepo.save(maths);
    }
    public List<SubjectStudent> getAllMathMarks()
    {
        List<SubjectStudent> result = new ArrayList<>();

        for (Maths m : mathRepo.findAll()) {

            SubjectStudent dto = new SubjectStudent(
                    m.getMarks(),
                    m.getStu().getId(),
                    m.getStu().getName()
            );
            result.add(dto);
            System.out.println(dto);
        }

        return result;

    }

    public List<SubjectStudent> getMathById(Integer id)
    {
        List<SubjectStudent> result=new ArrayList<>();
        for(Maths m:mathRepo.findByStu_Id(id))
        {
            SubjectStudent dto = new SubjectStudent(
                    m.getStu().getId(),
                    m.getMarks(),
                    m.getStu().getName());
            result.add(dto);
        }
        return result;
    }

    public Optional<StuSubHigh> getMathByIdHigh(Integer id)
    {
        Optional<StuSubHigh> result=mathRepo.findHighMarks(id);
        return result;
    }
    @Autowired
    PhysicsRepo pyRepo;
    public Physics addPy(Integer id,Integer marks)
    {
        Student student = jpa.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Physics py = new Physics();
        py.setMarks(marks);
        py.setStu(student);
        student.setPyMarks(marks);

        return pyRepo.save(py);
    }
    public List<SubjectStudent> getAllPyMarks()
    {
        List<SubjectStudent> result = new ArrayList<>();

        for (Physics m : pyRepo.findAll()) {

            SubjectStudent dto = new SubjectStudent(
                    m.getMarks(),
                    m.getStu().getId(),
                    m.getStu().getName()
            );
            result.add(dto);
            System.out.println(dto);
        }

        return result;

    }

    public List<SubjectStudent> getPyById(Integer id)
    {
        List<SubjectStudent> result=new ArrayList<>();
        for(Physics m:pyRepo.findByStu_Id(id))
        {
            SubjectStudent dto = new SubjectStudent(
                    m.getStu().getId(),
                    m.getMarks(),
                    m.getStu().getName());
            result.add(dto);
        }
        return result;
    }

    public Optional<StuSubHigh> getPyByIdHigh(Integer id)
    {
        Optional<StuSubHigh> result=pyRepo.findHighMarks(id);
        return result;
    }
}
