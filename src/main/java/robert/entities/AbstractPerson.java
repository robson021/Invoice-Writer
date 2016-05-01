package robert.entities;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * Created by robert on 29.03.16.
 */
@MappedSuperclass
public abstract class AbstractPerson extends AbstractEntity {

    private String name, surname;
    private String companyName;
    private String streetName;
    private String homeNo;
    private String postCode;
    private String city;

    //@Column(unique = true) //TODO uniqe nip number causes sql exception
    @NotNull
    private String nipNo;

    public AbstractPerson() {
        super();
    }

    public AbstractPerson(String name, String surname, String companyName, String streetName, String homeNo,
                          String postCode, String city, String nipNo) {

        this.name = name;
        this.surname = surname;
        this.companyName = companyName;
        this.streetName = streetName;
        this.homeNo = homeNo;
        this.postCode = postCode;
        this.city = city;
        this.nipNo = nipNo;
    }

    // basic constructor
    public AbstractPerson(String name, String surname, String nipNo) {
        super();
        this.name = name;
        this.surname = surname;
        this.nipNo = nipNo;
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