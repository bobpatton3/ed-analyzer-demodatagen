package com.bobpatton3.eddatagen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bobpatton3.eddatagen.service.IArrivalService;


@SpringBootApplication
public class EDAnalyzerApp {

    private static final Logger LOG = LoggerFactory.getLogger(EDAnalyzerApp.class);
    
    @Autowired
    IArrivalService arrivalService;

    public static void main(String[] args) {
        SpringApplication.run(EDAnalyzerApp.class, args);
    }

    // @PostConstruct
    public void postConstruct() {
        
        // generateAllBasicData();
    }

}
