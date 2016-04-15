package by.bsuir.mpp.transpony.entity;

public class Provider {

    protected Integer id;
    protected String name;
    protected String phone;
    protected String address;
    protected String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(Provider provider) {
        if (provider.getAddress().equals(getAddress())
                && provider.getEmail().equals(getEmail())
                && provider.getName().equals(getName())
                && provider.getPhone().equals(getPhone())) {
            return true;
        }
        return false;
    }
}
