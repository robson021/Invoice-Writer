package robert.responses;

import robert.entities.Contractor;

import java.util.List;

/**
 * Created by robert on 09.04.16.
 */
public class ContractorsResponse extends BasicResponse {
    private List<Contractor> contractors = null;

    public List<Contractor> getContractors() {
        return contractors;
    }

    public void setContractors(List<Contractor> contractors) {
        this.contractors = contractors;
    }
}
