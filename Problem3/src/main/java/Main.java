import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String link = "https://pwg.gsfc.nasa.gov/polar/EPO/auroral_poster/aurora_all.pdf";
        String save_location = "/Users/pongsathorn.p/Desktop/OOC-HW/output";
        WebDownload webDownload = new WebDownload();
        webDownload.downloadurl1(link, save_location);
        webDownload.downloadurl2(link, save_location);
        webDownload.downloadurl3(link, save_location);
    }
}
