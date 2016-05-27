package robert.responses;

import robert.responses.simpleentities.SimpleContractor;
import robert.responses.simpleentities.SimpleSalesman;
import robert.responses.simpleentities.SimpleService;

import java.util.Arrays;

/**
 * Created by robert on 27.05.16.
 */
public class InvoiceTemplate {
    private boolean copyOnMail = false;
    private SimpleSalesman salesman;
    private SimpleContractor contractor;
    private SimpleService[] selectedServices;


    public boolean isCopyOnMail() {
        return copyOnMail;
    }

    public void setCopyOnMail(boolean copyOnMail) {
        this.copyOnMail = copyOnMail;
    }

    public SimpleSalesman getSalesman() {
        return salesman;
    }

    public void setSalesman(SimpleSalesman salesman) {
        this.salesman = salesman;
    }

    public SimpleContractor getContractor() {
        return contractor;
    }

    public void setContractor(SimpleContractor contractor) {
        this.contractor = contractor;
    }

    public SimpleService[] getSelectedServices() {
        return selectedServices;
    }

    public void setSelectedServices(SimpleService[] selectedServices) {
        this.selectedServices = selectedServices;
    }

    @Override
    public String toString() {
        return "InvoiceTemplate{" +
                "copyOnMail=" + copyOnMail +
                ", salesman=" + salesman +
                ", contractor=" + contractor +
                ", selectedServices=" + Arrays.toString(selectedServices) +
                '}';
    }

    public boolean validate() {
        if (salesman == null || contractor == null || selectedServices == null || selectedServices.length == 0)
            return false;
        return true;
    }
}
