package com.jobrunr.td.tdjobrunr.application;

import com.jobrunr.td.tdjobrunr.scheduler.ProcessScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/samples")
public class JobRunrSampleApplication {

    @Autowired
    ProcessScheduler processScheduler;

    @GetMapping("/")
    public void executeJobProcessOnce() {
        processScheduler.processOnce();
    }

    @GetMapping("/sleep/{seconds}")
    public void executeWithSleep(@PathVariable int seconds) {
        processScheduler.processOnceMethodSleep(seconds);
    }

    @GetMapping("/scheduledOnce")
    public void scheduledOnce() {
        processScheduler.processScheduledOnce();
    }

    @GetMapping("/scheduleRecurrently")
    public void scheduleRecurrently() {
        processScheduler.processScheduledRecurrently();
    }
}
