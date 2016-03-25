package robert.entities;

import javax.persistence.*;

@Entity
public class Service_ {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String symbol;
    private int vatPercentage;
    private double nettoValue;
    private double bruttoValue;
    private double vatValue;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user = null;

    public Service_() {
    }

    public Service_(String name, String symbol, int vatPercentage, double nettoValue) {
        this.name = name;
        this.symbol = symbol;
        this.vatPercentage = vatPercentage;
        this.nettoValue = nettoValue;
        this.vatValue = nettoValue * vatPercentage / 100.;
        this.bruttoValue = nettoValue + vatValue;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getVatPercentage() {
        return vatPercentage;
    }

    public void setVatPercentage(int vatPercentage) {
        this.vatPercentage = vatPercentage;
    }

    public double getNettoValue() {
        return nettoValue;
    }

    public void setNettoValue(double nettoValue) {
        this.nettoValue = nettoValue;
    }

    public double getBruttoValue() {
        return bruttoValue;
    }

    public void setBruttoValue(double bruttoValue) {
        this.bruttoValue = bruttoValue;
    }

    public double getVatValue() {
        return vatValue;
    }

    public void setVatValue(double vatValue) {
        this.vatValue = vatValue;
    }

}
