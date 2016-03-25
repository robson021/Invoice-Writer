package robert.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Salesman extends AbstractPerson {
    private String regon;
    private String phoneNo;
    private String bankName;
    private String bankAccNo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user = null;


    public Salesman() {
        super(null, null, null, null, null, null, null, null);
    }

    public Salesman(long id, String name, String surname, String companyName, String streetName, String homeNo,
                    String postCode, String city, String nipNo, String regon, String phoneNo, String bankName,
                    String bankAccNo) {
        super(name, surname, companyName, streetName, homeNo, postCode, city, nipNo);
        this.regon = regon;
        this.phoneNo = phoneNo;
        this.bankName = bankName;
        this.bankAccNo = bankAccNo;
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

    @Override
    public String toString() {
        return super.toString() + " Salesman [regon=" + regon + ", phoneNo=" + phoneNo + ", bankName=" + bankName
                + ", bankAccNo=" + bankAccNo + "]";
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

}
