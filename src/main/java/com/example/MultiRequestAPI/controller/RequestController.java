package com.example.MultiRequestAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@RestController
@RequestMapping("/api")
public class RequestController {

    @Autowired
    ExecutorService executorService;


    @GetMapping("/accept/{param}")
    public CompletableFuture<String> acceptRequestConcurrently(@PathVariable String param){
        return CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getName() + "printing: " + param;
        },executorService);
    }

}
