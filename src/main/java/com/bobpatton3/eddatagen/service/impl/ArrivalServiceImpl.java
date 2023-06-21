package com.bobpatton3.eddatagen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bobpatton3.eddatagen.persistence.model.Arrival;
import com.bobpatton3.eddatagen.persistence.repository.IArrivalRepository;
import com.bobpatton3.eddatagen.service.IArrivalService;

@Service
public class ArrivalServiceImpl implements IArrivalService {
    
    @Autowired
    private IArrivalRepository arrivalRepository;

    @Override
    public void save(Arrival arrival) {
        arrivalRepository.save( arrival);
    }


}
