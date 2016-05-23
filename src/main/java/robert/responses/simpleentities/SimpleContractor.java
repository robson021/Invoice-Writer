package robert.responses.simpleentities;

import robert.entities.Contractor;

/**
 * Created by robert on 09.04.16.
 */
public class SimpleContractor {
    private String name, surname;
    private String companyName;
    private String streetName;
    private String homeNo;
    private String postCode;
    private String city;
    private String nipNo = "1111";

    public SimpleContractor(String name, String surname, String companyName, String streetName, String homeNo, String postCode, String city, String nipNo) {
        this.name = name;
        this.surname = surname;
        this.companyName = companyName;
        this.streetName = streetName;
        this.homeNo = homeNo;
        this.postCode = postCode;
        this.city = city;
        this.nipNo = nipNo;
    }

    public SimpleContractor() {
        super();
    }

    public SimpleContractor(Contractor contractor) {
        this.name = contractor.getName();
        this.surname = contractor.getSurname();
        this.companyName = contractor.getCompanyName();
        this.streetName = contractor.getStreetName();
        this.homeNo = contractor.getHomeNo();
        this.postCode = contractor.getPostCode();
        this.city = contractor.getCity();
        this.nipNo = contractor.getNipNo();
    }

    public String getName() {
        return name;
    }

    public String getNipNo() {
        return nipNo;
    }

    public void setNipNo(String nipNo) {
        this.nipNo = nipNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHomeNo() {
        return homeNo;
    }

    public void setHomeNo(String homeNo) {
        this.homeNo = homeNo;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "SimpleContractor{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", companyName='" + companyName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", homeNo='" + homeNo + '\'' +
                ", postCode='" + postCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}