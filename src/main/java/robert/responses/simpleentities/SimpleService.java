package robert.responses.simpleentities;

import robert.entities.TheService;

/**
 * Created by robert on 13.04.16.
 */
public class SimpleService {
    private String name;
    private String symbol;
    private int vatPercentage;
    private double nettoValue;
    private int count;

    public SimpleService(TheService service) {
        name = service.getName();
        symbol = service.getSymbol();
        vatPercentage = service.getVatPercentage();
        nettoValue = service.getNettoValue();
        count = 1;
    }

    public SimpleService() {
        super();
    }

    public SimpleService(String name, String symbol, String vatPercentage, String nettoValue) {
        this.name = name;
        this.symbol = symbol;
        this.vatPercentage = Integer.parseInt(vatPercentage);
        this.nettoValue = Double.parseDouble(nettoValue);
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String calculateBruttoAsString() {
        return String.format("%.2f", calculateBrutto()) + "$";
    }

    public double calculateBrutto() {
        double brutto = nettoValue * count * vatPercentage / 100; //vat value
        brutto += nettoValue * count; // netto value
        return brutto;
    }


    @Override
    public String toString() {
        return "SimpleService{" +
                "name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", vatPercentage='" + vatPercentage + '\'' +
                ", nettoValue='" + nettoValue + '\'' +
                '}';
    }
}