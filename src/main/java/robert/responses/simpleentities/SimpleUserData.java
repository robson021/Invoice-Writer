package robert.responses.simpleentities;

import robert.responses.BasicResponse;

/**
 * Created by robert on 13.04.16.
 */
public abstract class SimpleUserData extends BasicResponse {
    private String email = null;
    private String password = null;

    public SimpleUserData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public SimpleUserData() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
