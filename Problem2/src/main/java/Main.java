import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CountingDirectory countingDirectory = new CountingDirectory();
        String pathname = "/Users/pongsathorn.p/Desktop/OOC-HW/docs";
        // command line arguments
        Options options = new Options();
        options.addOption("a", "-total-num-files", false, "The total number of files" );
        options.addOption("b", "--total-num-dirs", false, "Total number of directory");
        options.addOption("c", "--total-unique-exts",false, "Total number of unique file extensions");
        options.addOption("d", "--list-exts", false, "List all unique file extensions");
        options.addOption("e","ext", true, "List total number of file for specified extension EXT");
        options.addOption("f","path", true, "Path to the documentation folder");

        try{
            CommandLineParser parser = new DefaultParser();
            CommandLine commandLine = parser.parse(options, args);
            pathname = commandLine.getOptionValue("f");
            File file = new File(pathname);
            countingDirectory.ListOfFile(file);
            if (commandLine.hasOption("a")){
                countingDirectory.getNumberOfFiles();
            }
            if (commandLine.hasOption("b")){
                countingDirectory.getNumberOfDirectory();
            }
            if (commandLine.hasOption("c")){
                countingDirectory.getUniqueFileEXT();
            }
            if (commandLine.hasOption("d")){
                countingDirectory.printExtension();
            }
            if (commandLine.hasOption("e")){
                String ext = commandLine.getOptionValue("e");
                countingDirectory.printSpecificEXT(ext);
            }else {
                System.out.println(" path is wrong ");
            }
        }catch (ParseException e){
            e.printStackTrace();
        }
    }
}
