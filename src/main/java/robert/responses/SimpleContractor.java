package robert.responses;

/**
 * Created by robert on 09.04.16.
 */
public class SimpleContractor extends BasicResponse {
    private String text = "dsada";

    public SimpleContractor() {
        super();
    }

    public SimpleContractor(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
