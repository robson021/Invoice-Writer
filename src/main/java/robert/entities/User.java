package robert.entities;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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

    @NotNull
    private char[] password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Set<TheService> services = new HashSet<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public Set<TheService> getServices() {
        return Collections.unmodifiableSet(this.services);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email.toString() + '\'' +
                ", password=" + Arrays.toString(password) +
                '}';
    }
}
