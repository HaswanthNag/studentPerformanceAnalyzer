package com.springbBoot.performanceAnalyticsSystem.Repository;

import com.springbBoot.performanceAnalyticsSystem.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface studentRepo extends JpaRepository<Student,Integer>
{
    boolean existsByEmail(String email);
}
