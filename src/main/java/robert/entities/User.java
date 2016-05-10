package robert.entities;

import org.springframework.util.Assert;
import robert.responses.simpleentities.SimpleContractor;
import robert.responses.simpleentities.SimpleSalesman;
import robert.responses.simpleentities.SimpleService;
import robert.responses.simpleentities.SimpleUser;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by robert on 28.03.16.
 */
@Entity
public class User extends AbstractEntity {

    @NotNull
    private String firstName;

    @NotNull
    private String surname;

    @Column(unique = true)
    @NotNull
    private EmailAddress email;
    private String emailAsString = null;

    @NotNull
    private char[] password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<TheService> services = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<Contractor> contractors = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<Salesman> salesmen = new ArrayList<>();

    public User() {
        super();
    }

    public User(SimpleUser user) {
        this.email = new EmailAddress(user.getEmail());
        this.emailAsString = this.email.toString();
        this.firstName = user.getFirstname();
        this.surname = user.getSurname();
        this.password = user.getPassword().toCharArray();
    }

    public User(String firstName, String surname, EmailAddress email, char[] password) {
        super();
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.emailAsString = email.toString();
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void addContractor(Contractor contractor) {
        Assert.notNull(contractor);
        this.contractors.add(contractor);
    }

    public void addSalesman(Salesman salesman) {
        Assert.notNull(salesman);
        this.salesmen.add(salesman);
    }

    public void addService(TheService service) {
        Assert.notNull(service);
        this.services.add(service);
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email.toString();
    }

    public void setEmail(EmailAddress email) {
        this.email = email;
        this.emailAsString = email.toString();
    }

    public char[] getPassword() {
        return password;
    }

    public String getPasswdAsString() {
        String s = "";
        for (char c : this.password) {
            s += c;
        }
        return s;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public List<TheService> getServices() {
        return services;
    }

    public List<SimpleService> getSimpleServices() {
        if (services.isEmpty()) return null;
        List<SimpleService> simpleServices = new ArrayList<>();
        for (TheService s : services) {
            simpleServices.add(new SimpleService(s));
        }
        return simpleServices;
    }

    public List<SimpleContractor> getSimpleContractors() {
        if (contractors.isEmpty()) return null;
        List<SimpleContractor> simpleContractors = new ArrayList<>();
        for (Contractor c : contractors) {
            simpleContractors.add(new SimpleContractor(c));
        }
        return simpleContractors;
    }

    public List<SimpleSalesman> getSimpleSalesmen() {
        if (salesmen.isEmpty()) return null;
        List<SimpleSalesman> simpleSalesmen = new ArrayList<>();
        for (Salesman s : salesmen) {
            simpleSalesmen.add(new SimpleSalesman(s));
        }
        return simpleSalesmen;
    }

    public List<Contractor> getContractors() {
        return contractors;
    }


    public void setContractors(List<Contractor> contractors) {
        this.contractors = contractors;
    }

    public List<Salesman> getSalesmen() {
        return salesmen;
    }

    public void setSalesmen(List<Salesman> salesmen) {
        this.salesmen = salesmen;
    }

    public void setServices(List<TheService> services) {
        this.services = services;
    }


    // TODO: 10.05.16 passwd encode/decode
    public char[] encode(String str) {
        BASE64Encoder encoder = new BASE64Encoder();
        str = new String(encoder.encodeBuffer(str.getBytes()));
        return str.toCharArray();
    }

    public char[] decode(String password) {
        BASE64Decoder decoder = new BASE64Decoder();
        String decodedPassword = null;
        try {
            decodedPassword = new String(decoder.decodeBuffer(password));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return decodedPassword.toCharArray();
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", email=" + email +
                ", password=" + Arrays.toString(password) +
                ", services=" + services.toString() +
                ", contractors=" + contractors.toString() +
                ", salesmen=" + salesmen.toString() +
                '}';
    }
}
