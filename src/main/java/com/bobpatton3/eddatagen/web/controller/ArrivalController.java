package com.bobpatton3.eddatagen.web.controller;

import java.util.UUID;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import com.bobpatton3.eddatagen.persistence.model.Arrival;
import com.bobpatton3.eddatagen.service.IArrivalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/arrivals")
public class ArrivalController {

    
    @Autowired
    private IArrivalService arrivalService;
    
    // URL:  http://localhost:8080/arrivals/2022-01-02%2000:00/2022-07-01%2000:00/30%20minutes/A1%20Emergency%20Physicians/Memorial%20Hospital/Main%20ED

    @PutMapping("/{department_id}/{factor}")
    public void createArrivalsData
    (
        @PathVariable UUID department_id,
        @PathVariable float factor
    ) {
        generateAllBasicData( department_id, factor);
    }
    
    
    /*
     * 
     * This is used to generate demo dummy data. 
     * 
     */

    
    private void generateAllBasicData(UUID department_id, float factor) {

        for (int i = 0; i < 1440; i++) {
            
            int arrivals_to_generate = interpolateArrivalsCurve(i, factor);
            
            for (int j = 0; j < arrivals_to_generate; j++ ) {
                String cpt = randomCPT();
                BigDecimal rvus = BigDecimal.valueOf(randomRVUs(cpt));
                Instant inst = Instant.ofEpochMilli( 1640476800000l + 604800000l*(long)(54.0*Math.random()) + 86400000l*randomDOW() + i*60000l );
                ZonedDateTime zonedArrival = ZonedDateTime.ofInstant(inst, ZoneId.of("UTC"));

                arrivalService.save(new Arrival(
                    UUID.randomUUID(),
                    department_id,
                    zonedArrival,
                    rvus,
                    cpt,
                    0));
            }
        }

    }
    
    private int interpolateArrivalsCurve(int minute_of_day, float factor) {
        double[] values = {6, 3.5, 3.5, 2.5, 3, 4, 6, 8, 9, 12, 17, 21, 23, 24, 23, 21, 22, 21.5, 23, 22, 19, 13, 11, 7, 6};
        
        int hod = (int)( Math.floor(minute_of_day / 60.0) );
        double portion_of_hour = (minute_of_day%60)/60.0;
        
        int retVal = (int) (factor * 5.0 * (values[hod] * (1 - portion_of_hour)  + values[hod+1] * portion_of_hour) );
        
        return retVal;
    }
    
    private String randomCPT() {
        double rand = Math.random();
        if (rand < 0.05) return "99282";
        else if (rand < 0.3) return "99283";
        else if (rand < 0.75) return "99284";
        else if (rand < 0.92) return "99285";
        else return "99291";
    }
    
    private int randomDOW() {
        double rand = Math.random();
        if (rand < 0.16) return 1;
        else if (rand < 0.315) return 2;
        else if (rand < 0.46) return 3;
        else if (rand < 0.605) return 4;
        else if (rand < 0.750) return 5;
        else if (rand < 0.875) return 6;
        else return 0;
    }
    
    private double randomRVUs(String cpt) {
        double rand = Math.random();
        if(cpt.equals("99282")) return 1.0 + 1.0 * rand;
        else if(cpt.equals("99283")) return 1.4 + 1.5 * rand;
        else if(cpt.equals("99284")) return 2.6 + 2.5 * rand;
        else if(cpt.equals("99285")) return 3.8 + 3.5 * rand;
        else return 4.5 + 4 * rand;
    }
    
}
