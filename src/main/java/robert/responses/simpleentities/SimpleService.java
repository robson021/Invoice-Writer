package robert.responses.simpleentities;

import robert.entities.TheService;

/**
 * Created by robert on 13.04.16.
 */
public class SimpleService {
    private String name;
    private String symbol;
    private String vatPercentage;
    private String nettoValue;

    public SimpleService(TheService service) {
        name = service.getName();
        symbol = service.getSymbol();
        vatPercentage = String.valueOf(service.getVatPercentage());
        nettoValue = String.valueOf(service.getNettoValue());
    }

    public SimpleService() {
        super();
    }

    public SimpleService(String name, String symbol, String vatPercentage, String nettoValue) {
        this.name = name;
        this.symbol = symbol;
        this.vatPercentage = vatPercentage;
        this.nettoValue = nettoValue;
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

    public String getVatPercentage() {
        return vatPercentage;
    }

    public void setVatPercentage(String vatPercentage) {
        this.vatPercentage = vatPercentage;
    }

    public String getNettoValue() {
        return nettoValue;
    }

    public void setNettoValue(String nettoValue) {
        this.nettoValue = nettoValue;
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