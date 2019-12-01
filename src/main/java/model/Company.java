package model;

import org.bson.types.ObjectId;

import java.util.Objects;
import java.util.UUID;

public class Company {
    private ObjectId _id;
    private String name;
    private Address address;
    private String phone;
    private String mail;
    private String representative;

    public Company(){};
    public Company(String name, Address address, String phone, String mail, String representative) {
        this._id = new ObjectId();
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
        this.representative = representative;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return get_id().equals(company.get_id()) &&
                Objects.equals(getName(), company.getName()) &&
                Objects.equals(getAddress(), company.getAddress()) &&
                Objects.equals(getPhone(), company.getPhone()) &&
                Objects.equals(getMail(), company.getMail()) &&
                Objects.equals(getRepresentative(), company.getRepresentative());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_id(), getName(), getAddress(), getPhone(), getMail(), getRepresentative());
    }

    @Override
    public String toString() {
        return "Company{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                ", representative='" + representative + '\'' +
                '}';
    }
}
