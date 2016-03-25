package robert.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String firstName;
    @NotNull
    private String surname;
    @Column(unique = true)
    @NotNull
    private String email;
    @NotNull
    private char[] password;

    @OneToMany(mappedBy = "user")
    private List<Service_> services = new ArrayList<Service_>();

    @OneToMany(mappedBy = "user")
    private List<Contractor> contractors = new ArrayList<Contractor>();

    @OneToMany(mappedBy = "user")
    private List<Salesman> salesmans = new ArrayList<Salesman>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public List<Contractor> getContractors() {
        return contractors;
    }

    public void setContractors(List<Contractor> contractors) {
        this.contractors = contractors;
    }

    public List<Service_> getServices() {
        return services;
    }

    public void setServices(List<Service_> services) {
        this.services = services;
    }

    public List<Salesman> getSalesmans() {
        return salesmans;
    }

    public void setSalesmans(List<Salesman> salesmans) {
        this.salesmans = salesmans;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName + ", surname=" + surname + ", email=" + email
                + ", password=" + Arrays.toString(password) + "]";
    }

}
