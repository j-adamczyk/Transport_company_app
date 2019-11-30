package model;

import java.time.LocalDate;
import java.util.UUID;

public class Driver {
    private UUID _id;
    private String name;
    private LocalDate birthDate;
    private LocalDate hireDate;
    private String phone;
    private Address address;
    private Double salary;

    public Driver(String name, LocalDate birthDate, LocalDate hireDate, String phone, Address address, Double salary) {
        this._id = UUID.randomUUID();
        this.name = name;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.phone = phone;
        this.address = address;
        this.salary = salary;
    }

    public UUID get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
