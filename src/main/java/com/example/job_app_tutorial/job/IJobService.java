package com.example.job_app_tutorial.job;

import java.util.List;

public interface IJobService {
    List<Job> findAll();

    void createJob(Job job);

    Job findOne(Long id);

    boolean deleteOne(Long id);

    boolean updateOne(Long id, Job updatedJob);
}
