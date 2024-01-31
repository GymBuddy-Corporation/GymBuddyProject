package beans;



public class GymInfoBean {
    private final String name;
    private final String address;
    private final String city;
    private final String iban;
    private final String country;

    /*This is a constructor without syntax check and is used by controller*/
    public GymInfoBean(String name, String address, String city, String iban, String country) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.iban = iban;
        this.country = country;
    }

    /*This is a constructor used only for searching gym stuff knowing his name;*/
    public GymInfoBean(String name){
        this.name=name;
        this.address = null;
        this.city = null;
        this.iban = null;
        this.country = null;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCity() {
        return this.city;
    }

    public String getIban() {
        return this.iban;
    }
    public String getCountry() {
        return country;
    }


}
