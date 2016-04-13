package robert.entities;

import robert.responses.simpleentities.SimpleSalesman;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by robert on 29.03.16.
 */
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
        super();
    }

    public Salesman(String name, String surname, String nipNo) {
        super(name, surname, nipNo);
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

    public SimpleSalesman getSimpleSalesman() {
        return new SimpleSalesman(this);
    }

}
