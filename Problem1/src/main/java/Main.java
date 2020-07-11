import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String pathname = "/Users/pongsathorn.p/Desktop/OOC HW1/docs";
        File file = new File(pathname);
        CountingDirectory countingDirectory = new CountingDirectory();
        countingDirectory.ListOfFile(file);
        countingDirectory.getNumberOfFiles();
        countingDirectory.getNumberOfDirectory();
        countingDirectory.getUniqueFileEXT();
        System.out.println("List all unique file extensions and Total number of files for each extension.");
        countingDirectory.printExtension();
    }
}
