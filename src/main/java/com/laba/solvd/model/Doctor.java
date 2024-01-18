package com.laba.solvd.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Doctor {
    @JsonProperty("doctorId")
    @XmlElement
    private int doctorId;
    @JsonProperty("person")
    @XmlElement
    private Person person;
    @JsonProperty("specialties")
    @XmlElement
    private List<DoctorSpecialty> specialties;

    public Doctor() {
    }

    public Doctor(int doctorId, Person person, List<DoctorSpecialty> specialties) {
        this.doctorId = doctorId;
        this.person = person;
        this.specialties = specialties;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<DoctorSpecialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<DoctorSpecialty> specialties) {
        this.specialties = specialties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return doctorId == doctor.doctorId && Objects.equals(person, doctor.person) && Objects.equals(specialties, doctor.specialties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, person, specialties);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", person=" + person +
                ", specialties=" + specialties +
                '}';
    }
}
