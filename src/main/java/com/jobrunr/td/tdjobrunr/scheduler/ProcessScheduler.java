package com.jobrunr.td.tdjobrunr.scheduler;

import com.jobrunr.td.tdjobrunr.service.JobRunrSample;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProcessScheduler {

    @Autowired
    private JobScheduler jobScheduler;
    @Autowired
    private JobRunrSample jobRunrSample;

    @Job(name = "Processo que executa uma única vez")
    public void processOnce() {
        jobScheduler.enqueue(() -> jobRunrSample.processSumRandomNumbers());
    }

    @Job(name = "Processo que executa uma única vez o método com Sleep considerando %0 segundos", retries = 3)
    public void processOnceMethodSleep(int seconds) {
        jobScheduler.enqueue(() -> jobRunrSample.processWithSleep(seconds));
    }

    @Job(name = "Processo que executa uma única vez com agendamento")
    public void processScheduledOnce() {
        System.out.println("chamada do agendamento que será executado daqui 30 Seg. " + LocalDateTime.now().toString());
        jobScheduler.schedule(() -> jobRunrSample.processSumRandomNumbers(), LocalDateTime.now().plusSeconds(30));
    }

    @Job(name = "Processo que executa de forma recorrente com agendamento")
    public void processScheduledRecurrently() {
        jobScheduler.scheduleRecurrently(() -> jobRunrSample.processSumRandomNumbers(), Cron.every5minutes());
    }
}
