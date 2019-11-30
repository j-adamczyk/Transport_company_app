package model;

public class Company {
    private String name;
    private Address address;
    private String phone;
    private String mail;
    private String representative;

    public Company(String name, Address address, String phone, String mail, String representative) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
        this.representative = representative;
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
}
