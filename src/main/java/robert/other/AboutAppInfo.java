package robert.other;

/**
 * Created by robert on 04.05.16.
 */
public class AboutAppInfo {
    private final String title = "About application";
    private final String text = "This is the application for writing invoices." +
            "\nAfter registration you can store your data in the application's database." +
            "\nThat means you have access to it anywhere you have internet connection!";

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
