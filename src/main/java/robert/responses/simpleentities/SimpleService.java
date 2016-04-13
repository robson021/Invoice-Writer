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
    private String bruttoValue;
    private String vatValue;

    public SimpleService(TheService service) {
        name = service.getName();
        symbol = service.getSymbol();
        vatPercentage = String.valueOf(service.getVatPercentage());
        nettoValue = String.valueOf(service.getNettoValue());
        bruttoValue = String.valueOf(service.getBruttoValue());
        vatValue = String.valueOf(service.getVatValue());
    }

    public SimpleService() {
        super();
    }

    public SimpleService(String name, String symbol, String vatPercentage, String nettoValue, String bruttoValue, String vatValue) {
        this.name = name;
        this.symbol = symbol;
        this.vatPercentage = vatPercentage;
        this.nettoValue = nettoValue;
        this.bruttoValue = bruttoValue;
        this.vatValue = vatValue;
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

    public String getBruttoValue() {
        return bruttoValue;
    }

    public void setBruttoValue(String bruttoValue) {
        this.bruttoValue = bruttoValue;
    }

    public String getVatValue() {
        return vatValue;
    }

    public void setVatValue(String vatValue) {
        this.vatValue = vatValue;
    }
}
