package com.springbBoot.performanceAnalyticsSystem.Controller;

import com.springbBoot.performanceAnalyticsSystem.Dto.ApiResponse;
import com.springbBoot.performanceAnalyticsSystem.Dto.StuSubHigh;
import com.springbBoot.performanceAnalyticsSystem.Dto.SubjectStudent;
import com.springbBoot.performanceAnalyticsSystem.Model.Maths;
import com.springbBoot.performanceAnalyticsSystem.Model.Physics;
import com.springbBoot.performanceAnalyticsSystem.Model.Student;
import com.springbBoot.performanceAnalyticsSystem.Repository.studentRepo;
import com.springbBoot.performanceAnalyticsSystem.Service.adminService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Admin
{
    @Autowired
    adminService service;
    @GetMapping("/admin")
    public List<Student> getAll()
    {

        return service.getAll();
    }
    @GetMapping("/admin/{id}")
    public ResponseEntity<ApiResponse<Optional<Student>>> getById(@PathVariable Integer id)
    {
        Optional<Student> stu=service.getById(id);
        if(stu.isPresent())
        {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(200,"Data Fetched Succesfully",stu));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(404,"Data not fpund",stu));
    }
    @PostMapping("/admin")
    public ResponseEntity<ApiResponse<Student>> saveStu(@RequestBody Student stu)
    {
        try
        {   service.save(stu);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                    201,
                    "Data Entered Succesfully",
                    stu
            ));
        }
        catch(DataIntegrityViolationException e)
        {
            System.out.println("hi");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(400,"Duplicate data not allowed",null));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(500,"SomeThingWentWrong",null));
        }

    }
    @PutMapping("/admin/{id}")
    public ResponseEntity<ApiResponse<Student>> putById(@RequestBody Student stu,@PathVariable Integer id)
    {
        Student stud=service.putById(id,stu);
        if(stud!=null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(200,"Data modified succesfully",stud));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(404,"Check the data once",null));
    }
    @DeleteMapping("/admin/{id}")
    public String deleteById(@PathVariable Integer id)
    {
        boolean ifFound =service.deleteById(id);
        if(ifFound)
        {
            return "Successfully Deleted student with Id:"+id;
        }
        return "There is no student with Id:"+id;
    }
    @PostMapping("/admin/maths/{id}")
    public ResponseEntity<ApiResponse<Maths>> addMaths(@PathVariable Integer id, @RequestBody Maths maths)
    {
        Maths math=service.addMaths(id,maths.getMarks());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Maths marks saved", math));}
    @GetMapping("/admin/maths")
    public ResponseEntity<List<SubjectStudent>> getAllMathMarks()
    {
        return ResponseEntity.ok(service.getAllMathMarks());
    }
    @GetMapping("admin/maths/{id}")
    public ResponseEntity<List<SubjectStudent>> getMathById(@PathVariable Integer id)
    {
        return ResponseEntity.ok(service.getMathById(id));
    }
    @GetMapping("admin/maths/highest/{id}")
    public ResponseEntity<StuSubHigh> getMathByIdHigh(@PathVariable Integer id)
    {
        return service.getMathByIdHigh(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/admin/physics")
    public ResponseEntity<List<SubjectStudent>> getAllPyMarks()
    {
        return ResponseEntity.ok(service.getAllPyMarks());
    }
    @GetMapping("admin/physics/{id}")
    public ResponseEntity<List<SubjectStudent>> getPyById(@PathVariable Integer id)
    {
        return ResponseEntity.ok(service.getPyById(id));
    }
    @GetMapping("admin/physics/highest/{id}")
    public ResponseEntity<StuSubHigh> getPyByIdHigh(@PathVariable Integer id)
    {
        return service.getPyByIdHigh(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/admin/physics/{id}")
    public ResponseEntity<ApiResponse<Physics>> addPhysics(@PathVariable Integer id, @RequestBody Physics py)
    {
        Physics pyy=service.addPy(id,py.getMarks());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Physics marks saved", pyy));}

}
