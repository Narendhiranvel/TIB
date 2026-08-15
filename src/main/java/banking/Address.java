package banking;

public class Address {

    private int houseNumber;
    private String street;
    private String city;
    private String country;


    public Address(int houseNumber,
                   String street,
                   String city,
                   String country) {

        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.country = country;
    }


    public int getHouseNumber() {
        return houseNumber;
    }


    public String getStreet() {
        return street;
    }


    public String getCity() {
        return city;
    }


    public String getCountry() {
        return country;
    }


    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }


    public void setStreet(String street) {
        this.street = street;
    }


    public void setCity(String city) {
        this.city = city;
    }


    public void setCountry(String country) {
        this.country = country;
    }
}