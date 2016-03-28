package robert.entities;

import javax.persistence.Entity;

/**
 * Created by robert on 28.03.16.
 */
@Entity
public class TheService extends AbstractEntity {
    private String name;
    private String symbol;
    private int vatPercentage;
    private double nettoValue;
    private double bruttoValue;
    private double vatValue;

    public TheService(String name, String symbol, int vatPercentage, double nettoValue) {
        this.name = name;
        this.symbol = symbol;
        this.vatPercentage = vatPercentage;
        this.nettoValue = nettoValue;
        this.vatValue = nettoValue * vatPercentage / 100.;
        this.bruttoValue = nettoValue + vatValue;

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

    public void calculateBruttoValue() {
        this.calculatetVatValue();
        this.bruttoValue = nettoValue + vatValue;
    }

    public double getVatValue() {
        return vatValue;
    }

    public void calculatetVatValue() {
        this.vatValue = nettoValue * vatPercentage;
    }
}