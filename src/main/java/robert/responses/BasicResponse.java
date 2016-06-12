package robert.responses;

/**
 * Created by robert on 26.03.16.
 */
public class BasicResponse /*implements Serializable*/ {
    private String text = null;
    private boolean result = false;
    private String token = null;

    public BasicResponse(String text) {
        this.text = text;
    }

    public BasicResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setResult() {
        this.result = true;
    }

    @Override
    public String toString() {
        return "BasicResponse{" +
                "text='" + text + '\'' +
                ", result=" + result +
                '}';
    }
}
