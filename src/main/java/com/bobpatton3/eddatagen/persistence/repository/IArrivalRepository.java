package com.bobpatton3.eddatagen.persistence.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.bobpatton3.eddatagen.persistence.model.Arrival;

public interface IArrivalRepository extends CrudRepository<Arrival, UUID> {

}
