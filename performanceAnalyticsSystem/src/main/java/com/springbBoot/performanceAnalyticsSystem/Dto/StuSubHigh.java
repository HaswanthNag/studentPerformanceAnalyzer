package com.springbBoot.performanceAnalyticsSystem.Dto;

public class StuSubHigh
{
    public StuSubHigh(Integer id, String name, Long attempts, Integer highMarks)
    {
        this.name = name;
        this.id = id;
        this.highMarks = highMarks;
        this.attempts = attempts;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

  public Integer getHighMarks() {
        return highMarks;
    }


    public Long getAttempts() {
        return attempts;
    }
    private String name;
    private Integer id;
    private Integer highMarks;
    private Long attempts;
}
