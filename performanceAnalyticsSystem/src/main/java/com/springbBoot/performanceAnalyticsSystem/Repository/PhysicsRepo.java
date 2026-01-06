package com.springbBoot.performanceAnalyticsSystem.Repository;

import com.springbBoot.performanceAnalyticsSystem.Dto.StuSubHigh;
import com.springbBoot.performanceAnalyticsSystem.Model.Physics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PhysicsRepo extends JpaRepository<Physics,Integer>
{
    List<Physics> findByStu_Id(Integer id);
    @Query("""
    SELECT new com.springbBoot.performanceAnalyticsSystem.Dto.StuSubHigh(
        s.id,
        s.name,
        COUNT(m),
        MAX(m.marks)
    )
    FROM Physics m
    JOIN m.stu s
    WHERE s.id = :studentId
    GROUP BY s.id, s.name
""")
    Optional<StuSubHigh> findHighMarks(@Param("studentId") Integer studentId);
}
