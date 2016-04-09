package robert.responses;

import robert.entities.Contractor;
import robert.entities.Salesman;
import robert.entities.TheService;
import robert.entities.User;

import java.util.List;

/**
 * Created by robert on 09.04.16.
 */
public class UserDataResponse extends BasicResponse {
    private List<Contractor> contractors = null;
    private List<TheService> services = null;
    private List<Salesman> salesmen = null;

    // constructors
    public UserDataResponse() {
        super();
    }

    public UserDataResponse(User user) {
        super();
        contractors = user.getContractors();
        salesmen = user.getSalesmens();
        services = user.getServices();
        this.setResult(true);
    }

    // methods

    public List<Contractor> getContractors() {
        return contractors;
    }

    public void setContractors(List<Contractor> contractors) {
        this.contractors = contractors;
    }

    public List<TheService> getServices() {
        return services;
    }

    public void setServices(List<TheService> services) {
        this.services = services;
    }

    public List<Salesman> getSalesmen() {
        return salesmen;
    }

    public void setSalesmen(List<Salesman> salesmen) {
        this.salesmen = salesmen;
    }

    @Override
    public String toString() {
        return "UserDataResponse{" +
                "contractors=" + contractors +
                ", services=" + services +
                ", salesmen=" + salesmen +
                '}';
    }
}
