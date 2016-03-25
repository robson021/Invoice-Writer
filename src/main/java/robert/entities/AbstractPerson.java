package robert.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public abstract class AbstractPerson {

    @Id
    @GeneratedValue
    long id;
    private String name, surname;
    private String companyName;
    private String streetName;
    private String homeNo;
    private String postCode;
    private String city;
    private String nipNo;


    public AbstractPerson(String name, String surname, String companyName, String streetName, String homeNo,
                          String postCode, String city, String nipNo) {

        //this.id = id;
        this.name = name;
        this.surname = surname;
        this.companyName = companyName;
        this.streetName = streetName;
        this.homeNo = homeNo;
        this.postCode = postCode;
        this.city = city;
        this.nipNo = nipNo;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
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

    public String getNipNo() {
        return nipNo;
    }

    public void setNipNo(String nipNo) {
        this.nipNo = nipNo;
    }

    @Override
    public String toString() {
        return "AbstractPerson [name=" + name + ", surname=" + surname + ", companyName=" + companyName
                + ", streetName=" + streetName + ", homeNo=" + homeNo + ", postCode=" + postCode + ", city=" + city
                + ", nipNo=" + nipNo + "]";
    }

}
