package beans;

public class SearchGymBean {
    private String name;
    private String city;
    private String address;
    private String country;

    public SearchGymBean(String name, String city, String address, String country) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}