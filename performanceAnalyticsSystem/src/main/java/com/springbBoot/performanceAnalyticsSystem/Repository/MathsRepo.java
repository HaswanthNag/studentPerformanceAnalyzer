package com.springbBoot.performanceAnalyticsSystem.Repository;

import com.springbBoot.performanceAnalyticsSystem.Dto.StuSubHigh;
import com.springbBoot.performanceAnalyticsSystem.Model.Maths;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MathsRepo extends JpaRepository<Maths,Integer>
{
    List<Maths> findByStu_Id(Integer id);
    @Query("""
    SELECT new com.springbBoot.performanceAnalyticsSystem.Dto.StuSubHigh(
        s.id,
        s.name,
        COUNT(m),
        MAX(m.marks)
    )
    FROM Maths m
    JOIN m.stu s
    WHERE s.id = :studentId
    GROUP BY s.id, s.name
""")
    Optional<StuSubHigh> findHighMarks(@Param("studentId") Integer studentId);

}
