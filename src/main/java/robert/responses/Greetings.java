package robert.responses;

import java.util.Calendar;

/**
 * Created by robert on 30.03.16.
 */
public class Greetings {
    private String text;
    private String date;

    public Greetings() {
        text = "hell world";
        date = Calendar.getInstance().getTime().toString();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Greetings{" +
                "text='" + text + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
