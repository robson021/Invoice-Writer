package robert.responses.simpleentities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 13.04.16.
 */
public class DataHolderResponse extends SimpleUserData {
    private List<SimpleContractor> contractors;
    private List<SimpleSalesman> salesmen;
    private List<SimpleService> services;

    public List<SimpleService> getServices() {
        return services;
    }

    public void setServices(List<SimpleService> services) {
        this.services = services;
    }

    public List<SimpleSalesman> getSalesmen() {
        return salesmen;
    }

    public void setSalesmen(List<SimpleSalesman> salesmen) {
        this.salesmen = salesmen;
    }

    public List<SimpleContractor> getContractors() {
        return contractors;
    }

    public void setContractors(List<SimpleContractor> contractors) {
        this.contractors = contractors;
    }

    @Override
    public String toString() {
        return "DataHolderResponse{" +
                "contractors=" + contractors +
                ", salesmen=" + salesmen +
                ", services=" + services +
                '}';
    }

    public void checkNulls() {
        if (contractors == null || contractors.size() == 0) {
            contractors = new ArrayList<>();
            SimpleContractor sc = new SimpleContractor();
            sc.setName("Example");
            sc.setSurname("Contractor");
            contractors.add(sc);
        }
        if (salesmen == null || salesmen.size() == 0) {
            salesmen = new ArrayList<>();
            SimpleSalesman ss = new SimpleSalesman();
            ss.setName("Example");
            ss.setSurname("Salesman");
            salesmen.add(ss);
        }
        if (services == null || services.size() == 0) {
            services = new ArrayList<>();
            SimpleService se = new SimpleService();
            se.setName("Example");
            se.setSymbol("e");
            se.setNettoValue("100");
            se.setVatPercentage("8");
            se.setCount(1);
            services.add(se);
        }
    }
}
