package com.example.job_app_tutorial.job;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService implements IJobService {
    private List<Job> jobs = new ArrayList<>();
    private Long id = 1L;

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        job.setId(id++);
        jobs.add(job);
    }
}
