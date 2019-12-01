package model;

import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Driver {
    private ObjectId _id;
    private String name;
    private LocalDate birthDate;
    private LocalDate hireDate;
    private String phone;
    private Address address;
    private Double salary;

    public Driver(){};

    public Driver(String name, LocalDate birthDate, LocalDate hireDate, String phone, Address address, Double salary) {
        this._id = new ObjectId();
        this.name = name;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.phone = phone;
        this.address = address;
        this.salary = salary;
    }

    public ObjectId get_id() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver)) return false;
        Driver driver = (Driver) o;
        return get_id().equals(driver.get_id()) &&
                Objects.equals(getName(), driver.getName()) &&
                Objects.equals(getBirthDate(), driver.getBirthDate()) &&
                Objects.equals(getHireDate(), driver.getHireDate()) &&
                Objects.equals(getPhone(), driver.getPhone()) &&
                Objects.equals(getAddress(), driver.getAddress()) &&
                Objects.equals(getSalary(), driver.getSalary());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_id(), getName(), getBirthDate(), getHireDate(), getPhone(), getAddress(), getSalary());
    }
}
