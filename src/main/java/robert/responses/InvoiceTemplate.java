package robert.responses;

import robert.responses.simpleentities.SimpleContractor;
import robert.responses.simpleentities.SimpleSalesman;
import robert.responses.simpleentities.SimpleService;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by robert on 27.05.16.
 */
public class InvoiceTemplate {
    private boolean copyOnMail = false;
    private Date sellDate;
    private Date exposureDate, deadDate;
    private String formOfPayment, placeOfexposure, invoiceNumber;
    private SimpleSalesman salesman;
    private SimpleContractor contractor;
    private SimpleService[] selectedServices;


    public boolean isCopyOnMail() {
        return copyOnMail;
    }

    public Date getDeadDate() {
        return deadDate;
    }

    public void setDeadDate(Date deadDate) {
        this.deadDate = deadDate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setCopyOnMail(boolean copyOnMail) {
        this.copyOnMail = copyOnMail;
    }

    public SimpleSalesman getSalesman() {
        return salesman;
    }

    public String getFormOfPayment() {
        return formOfPayment;
    }

    public void setFormOfPayment(String formOfPayment) {
        this.formOfPayment = formOfPayment;
    }

    public String getPlaceOfexposure() {
        return placeOfexposure;
    }

    public void setPlaceOfexposure(String placeOfexposure) {
        this.placeOfexposure = placeOfexposure;
    }

    public void setSalesman(SimpleSalesman salesman) {
        this.salesman = salesman;
    }

    public SimpleContractor getContractor() {
        return contractor;
    }

    public Date getSellDate() {
        return sellDate;
    }

    public void setSellDate(Date sellDate) {
        this.sellDate = sellDate;
    }

    public Date getExposureDate() {
        return exposureDate;
    }

    public void setExposureDate(Date exposureDate) {
        this.exposureDate = exposureDate;
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
                ", sellDate=" + sellDate +
                ", exposureDate=" + exposureDate +
                ", salesman=" + salesman +
                ", contractor=" + contractor +
                ", selectedServices=" + Arrays.toString(selectedServices) +
                '}';
    }

    public boolean validate() {
        return !(salesman == null || contractor == null || selectedServices == null || selectedServices.length == 0);
    }
}
