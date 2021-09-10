package com.ctrip.framework.apollo.doc.controller;


import cn.hutool.core.thread.ThreadUtil;
import com.ctrip.framework.apollo.doc.dto.CommonResult;
import com.ctrip.framework.apollo.doc.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Slf4j
@RestController
public class TaskController {

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private TaskService taskService;

    @RequestMapping("task")
    public CommonResult<String> task() {
        log.info("begin task.");
        String task = taskService.task();
        log.info("end task.");
        return new CommonResult<>(task);
    }

    @RequestMapping("taskCallable")
    public Callable<CommonResult<String>> taskCallable() {
        log.info("begin task.");
        Callable<CommonResult<String>> result = (() -> {
            return new CommonResult<>(taskService.task());
        });
        log.info("end task.");
        return result;
    }

    @RequestMapping("taskFuture")
    public Future<CommonResult<String>> taskFuture() {
        log.info("begin task.");
        CompletableFuture<CommonResult<String>> future = taskService.asyncTask();
        log.info("end task.");
        return future;

    }

    @RequestMapping("taskDeferredResult")
    public DeferredResult<CommonResult<String>> taskDeferredResult() {
        log.info("begin task.");
        DeferredResult<CommonResult<String>> result = new DeferredResult<>(10000L, new CommonResult(-1, "超时", null));
        result.onTimeout(() -> {
            log.info("调用超时");
        });

        result.onCompletion(() -> {
            log.info("调用完成");
        });
        taskExecutor.execute(() -> {
            ThreadUtil.sleep(3000);
            result.setResult(new CommonResult<>("complete."));
        });
        log.info("end task.");
        return result;
    }
}
