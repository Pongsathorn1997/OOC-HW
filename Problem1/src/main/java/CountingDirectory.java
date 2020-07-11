import org.apache.commons.io.DirectoryWalker;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CountingDirectory extends DirectoryWalker {

    private int DirectoryCounting = 0;
    private int UniqueDirectoryCounting = 0;
    private List<File> list = new ArrayList<File>();
    private HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

    public void ListOfFile(File pathname) throws IOException{
        walk(pathname, list); // walk through the directory
    }

    @Override
    protected void handleFile(File file, int depth, Collection results) throws IOException {
        results.add(file); // adding file
        uniqueFinder(file);
    }

    public void uniqueFinder(File file){
        String ext = FilenameUtils.getExtension(String.valueOf(file));
        if (hashMap.containsKey(ext) == false){
            hashMap.put(ext, 1);
        }else {
            hashMap.put(ext, (hashMap.get(ext)+1)); // if map already contain extension, value + 1
        }
        UniqueDirectoryCounting = hashMap.size();
    }

    @Override
    protected void handleDirectoryStart(File directory, int depth, Collection results) throws IOException {
        DirectoryCounting++;
    }

//    Total number of files.
//    Total number of directory
//    Total number of unique file extensions.
//    List all unique file extensions. Do not list duplicates.
//    Total number of files for each extension.

    // 1.1
    public void getNumberOfFiles() {
        System.out.print("Total number of files: ");
        System.out.println(list.size());
    }

    // 1.2
    public void getNumberOfDirectory() {
        System.out.print("Total number of directory: ");
        System.out.println(DirectoryCounting);
    }

    // 1.3
    public void getUniqueFileEXT() {
        System.out.print("Total number of unique file extensions: ");
        System.out.println(UniqueDirectoryCounting);
    }

    // List all unique file extensions and Total number of files for each extension.
    public void printExtension(){
        for (String ext: hashMap.keySet()){
            System.out.println(ext+" : "+hashMap.get(ext));
        }
    }
}
