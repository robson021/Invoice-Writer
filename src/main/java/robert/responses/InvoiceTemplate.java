package robert.responses;

/**
 * Created by robert on 27.05.16.
 */
public class InvoiceTemplate {
    private boolean copyOnMail = false;

    public boolean isCopyOnMail() {
        return copyOnMail;
    }

    public void setCopyOnMail(boolean copyOnMail) {
        this.copyOnMail = copyOnMail;
    }
}
