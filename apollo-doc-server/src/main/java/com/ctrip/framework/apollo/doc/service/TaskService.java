package com.ctrip.framework.apollo.doc.service;

import com.ctrip.framework.apollo.doc.dto.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class TaskService {

    @Autowired
    private TaskExecutor taskExecutor;

    public String task(){
        log.info("task service start.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        log.info("task service end.");
        return "complete";
    }
    @Async("taskExecutor")
    public CompletableFuture<CommonResult<String>> asyncTask() {
        log.info("task service start.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        log.info("task service end.");
        return CompletableFuture.completedFuture(new CommonResult<>("complete"));
    }

}
