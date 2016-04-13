package robert.responses.simpleentities;

import robert.entities.Salesman;

/**
 * Created by robert on 13.04.16.
 */
public class SimpleSalesman {
    private String name, surname;
    private String companyName;
    private String streetName;
    private String homeNo;
    private String postCode;
    private String city;
    private String regon;
    private String phoneNo;
    private String bankName;
    private String bankAccNo;
    private String nipNo;

    public SimpleSalesman() {
        super();
    }

    public SimpleSalesman(Salesman salesman) {
        this.name = salesman.getName();
        this.surname = salesman.getSurname();
        this.streetName = salesman.getStreetName();
        this.homeNo = salesman.getHomeNo();
        this.postCode = salesman.getHomeNo();
        this.city = salesman.getCity();
        this.regon = salesman.getRegon();
        this.phoneNo = salesman.getPhoneNo();
        this.bankAccNo = salesman.getBankAccNo();
        this.bankName = salesman.getBankName();
        this.nipNo = salesman.getNipNo();
        this.companyName = salesman.getCompanyName();
    }

    public SimpleSalesman(String name, String surname, String companyName, String streetName, String homeNo, String postCode, String city, String regon, String phoneNo, String bankName, String bankAccNo, String nipNo) {
        this.name = name;
        this.surname = surname;
        this.companyName = companyName;
        this.streetName = streetName;
        this.homeNo = homeNo;
        this.postCode = postCode;
        this.city = city;
        this.regon = regon;
        this.phoneNo = phoneNo;
        this.bankName = bankName;
        this.bankAccNo = bankAccNo;
        this.nipNo = nipNo;
    }

    public SimpleSalesman(String name, String surname, String nipNo) {
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

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccNo() {
        return bankAccNo;
    }

    public void setBankAccNo(String bankAccNo) {
        this.bankAccNo = bankAccNo;
    }

    public String getNipNo() {
        return nipNo;
    }

    public void setNipNo(String nipNo) {
        this.nipNo = nipNo;
    }
}
