package robert.entities;

import robert.responses.simpleentities.SimpleService;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by robert on 28.03.16.
 */
@Entity
public class TheService extends AbstractEntity {
    private String name;
    @Column(unique = true)
    private String symbol;
    private int vatPercentage;
    private double nettoValue;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public TheService(String name, String symbol, int vatPercentage, double nettoValue) {
        super();
        this.name = name;
        this.symbol = symbol;
        this.vatPercentage = vatPercentage;
        this.nettoValue = nettoValue;

    }

    public TheService(SimpleService service) {
        this.name = service.getName();
        this.symbol = service.getSymbol();
        this.vatPercentage = Integer.parseInt(service.getVatPercentage());
        this.nettoValue = Double.parseDouble(service.getNettoValue());
    }

    public TheService() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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




    @Override
    public String toString() {
        return "TheService{" +
                "name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", vatPercentage=" + vatPercentage +
                ", nettoValue=" + nettoValue +
                '}';
    }

    public SimpleService getSimpleService() {
        return new SimpleService(this);
    }
}