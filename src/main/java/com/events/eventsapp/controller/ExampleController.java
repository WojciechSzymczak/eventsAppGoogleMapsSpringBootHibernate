package com.events.eventsapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/example")
public class ExampleController {


    @GetMapping(path="/getmethod")
    public @ResponseBody String getMessage(@RequestParam(required = false,defaultValue = "defaultValue from Examplecontroller.") String [] p1) {

        StringBuilder strBld = new StringBuilder();

        for(int i = 0; i<p1.length; i++) {
            strBld.append(p1[i]);
        }

        return "Examplecontroller GET returns: " + strBld.toString();

    }

    @PostMapping(path="/postmethod")
    public @ResponseBody String getMessage2(@RequestParam(required = false,defaultValue = "afs") String [] p1) {

        StringBuilder strBld = new StringBuilder();

        for(int i = 0; i<p1.length; i++) {
            strBld.append(p1[i]);
        }

        return "Examplecontroller POST returns:  " + strBld.toString();

    }

}