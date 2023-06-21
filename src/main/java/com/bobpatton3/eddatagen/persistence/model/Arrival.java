package com.bobpatton3.eddatagen.persistence.model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "arrivals")
public class Arrival {

    @Id
    private UUID id;
    private UUID department_id;
    private ZonedDateTime arrival_datetime;
    private BigDecimal RVUs;
    private String CPT;
    private int age;

    public Arrival() {
        super();
    }

    public Arrival(
            UUID id,
            UUID department_id, 
            ZonedDateTime arrival_datetime, 
            BigDecimal rVUs, 
            String cPT, 
            int age
        ) {
        super();
        this.id = id;
        this.department_id = department_id;
        this.arrival_datetime = arrival_datetime;
        RVUs = rVUs;
        CPT = cPT;
        this.age = age;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ZonedDateTime getArrival_datetime() {
        return arrival_datetime;
    }

    public void setArrival_datetime(ZonedDateTime arrival_datetime) {
        this.arrival_datetime = arrival_datetime;
    }

    public BigDecimal getRVUs() {
        return RVUs;
    }

    public void setRVUs(BigDecimal rVUs) {
        RVUs = rVUs;
    }

    public String getCPT() {
        return CPT;
    }

    public void setCPT(String cPT) {
        CPT = cPT;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UUID getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(UUID department_id) {
        this.department_id = department_id;
    }

    @Override
    public String toString() {
        return "Arrival [id=" + id + ", department_id=" + department_id + ", arrival_datetime=" + arrival_datetime + ", RVUs=" + RVUs + ", CPT=" + CPT + ", age=" + age
            + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(CPT, RVUs, age, arrival_datetime, department_id, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Arrival other = (Arrival) obj;
        return Objects.equals(CPT, other.CPT) && Objects.equals(RVUs, other.RVUs) && age == other.age && Objects.equals(arrival_datetime, other.arrival_datetime)
            && Objects.equals(department_id, other.department_id) && Objects.equals(id, other.id);
    }
    
    

}
