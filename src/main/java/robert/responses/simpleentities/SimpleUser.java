package robert.responses.simpleentities;

import robert.entities.User;

import javax.validation.constraints.NotNull;

/**
 * Created by robert on 12.04.16.
 */
public class SimpleUser {
    private String firstname, surname;

    @NotNull
    private String email;

    @NotNull
    private String password;
    private String repassword;

    public SimpleUser(String firstname, String surname, String email, String password) {
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public SimpleUser(User user) {
        this.firstname = user.getFirstName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        //this.password = user.getPassword().toString();
        String passwd = "";
        for (char c : user.getPassword()) {
            passwd += c;
        }
        this.password = passwd;
    }

    public SimpleUser() {
        super();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SimpleUser{" +
                "firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
