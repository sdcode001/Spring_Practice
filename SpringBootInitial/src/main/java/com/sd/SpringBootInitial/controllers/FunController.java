package com.sd.SpringBootInitial.controllers;

import com.sd.SpringBootInitial.services.Coach;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FunController {
    //reading data from application.properties and auto-injection value.
    @Value("${server.port}")
    private int PORT;

    private final Coach coach1;
    private final Coach coach2;
    private final Coach coach3;

    public FunController(
            @Qualifier("cricketCoach") Coach coach1,
            @Qualifier("footballCoach") Coach coach2,
            @Qualifier("footballCoach") Coach coach3
    ){
        this.coach1=coach1;
        this.coach2=coach2;
        this.coach3=coach3;
        System.out.println("FunController bean Initialized...");

        if(this.coach2 != this.coach3){
            System.out.println("footballCoach bean is PROTOTYPE...");
        }
    }

    @GetMapping("/test")
    public String GetTest(){
        return "Hi Souvik Welcome. App running om PORT- "+PORT;
    }
}
