package robert.entities;

import robert.responses.simpleentities.SimpleContractor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by robert on 29.03.16.
 */
@Entity
public class Contractor extends AbstractPerson {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user = null;

    public Contractor() {
        super();
    }

    public Contractor(String name, String surname, String companyName, String streetName, String homeNo, String postCode, String city, String nipNo) {
        super(name, surname, companyName, streetName, homeNo, postCode, city, nipNo);
    }

    public Contractor(SimpleContractor c) {
        super(c.getName(), c.getSurname(), c.getCompanyName(), c.getStreetName(), c.getHomeNo(),
                c.getPostCode(), c.getCity(), c.getHomeNo());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SimpleContractor getSimpleContractor() {
        return new SimpleContractor(this);
    }
}