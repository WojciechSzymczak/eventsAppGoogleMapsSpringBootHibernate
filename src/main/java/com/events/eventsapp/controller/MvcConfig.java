package com.events.eventsapp.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {

        //Public views:
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout").setViewName("logout");

        //User views:

        //Admin views:

    }

}