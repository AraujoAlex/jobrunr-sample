package com.jobrunr.td.tdjobrunr.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class JobRunrSample {

    UUID processID;

    public void processSumRandomNumbers() {
        this.processID = this.processID == null ? UUID.randomUUID() : this.processID;

        System.out.println(String.format("\n%s: Ínicio (%s)", this.processID, LocalDateTime.now().toLocalTime()));
        Random random = new Random();
        int bound = 50;

        int firstNumber = random.nextInt(bound);
        int secondNumber = random.nextInt(bound);
        int sum = firstNumber + secondNumber;

        String strSum = String.format("%s: A soma de %s+%s é: %s", processID, firstNumber, secondNumber, sum);
        System.out.println(strSum);
        System.out.println(String.format("%s: Término (%s)", processID, LocalDateTime.now().toLocalTime()));
    }

    public void processWithSleep(int seconds) throws InterruptedException {
        UUID processID = UUID.randomUUID();
        System.out.println(String.format("\n%s: Ínicio com Sleep (%s)", processID, LocalDateTime.now().toLocalTime()));
        TimeUnit.SECONDS.sleep(seconds);
        this.processID = processID;
        this.processSumRandomNumbers();
        System.out.println(String.format("%s: Término com Sleep (%s)", processID, LocalDateTime.now().toLocalTime()));
    }
}
