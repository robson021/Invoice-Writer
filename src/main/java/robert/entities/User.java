package robert.entities;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private List<Salesman> salesmens = new ArrayList<>();

    public User() {
        super();
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
        this.salesmens.add(salesman);
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

    public void setPassword(char[] password) {
        this.password = password;
    }

    public List<TheService> getServices() {
        return services;
    }

    public List<Contractor> getContractors() {
        return contractors;
    }

    public void setContractors(List<Contractor> contractors) {
        this.contractors = contractors;
    }

    public List<Salesman> getSalesmens() {
        return salesmens;
    }

    public void setSalesmens(List<Salesman> salesmens) {
        this.salesmens = salesmens;
    }

    public void setServices(List<TheService> services) {
        this.services = services;
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
                ", salesmens=" + salesmens.toString() +
                '}';
    }
}
