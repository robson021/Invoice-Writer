package robert.responses.simpleentities;

import java.util.List;

/**
 * Created by robert on 13.04.16.
 */
public class DataHolderResponse {
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
}
