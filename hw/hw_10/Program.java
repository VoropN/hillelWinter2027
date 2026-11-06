package hw_10;

import java.util.Arrays;
import java.util.List;


public class Program {
    public static void main(String[] args) {
        String basePath = "/path/to/files/";
        int size = 50;
        FileNavigator fileNavigator = new FileNavigator();
        for (int i = 0; i < size; i++) {
            String name = "file" + i;
            String path = basePath + i % 6;
            int byteSize = size + i;
            FileData file = new FileData(name, byteSize, path);
            fileNavigator.add(path, file);
        }
        List<FileData> filesFound = fileNavigator.find(basePath + 2);
        System.out.println("------------------------------------------");
        System.out.println("Founded under the path: " + basePath + 2);
        System.out.println(Arrays.deepToString(filesFound.toArray()));

        System.out.println("------------------------------------------");
        System.out.println("Files filtered by size: " + (size + 2));
        List<FileData> filesFiltered = fileNavigator.filterBySize(size + 2);
        System.out.println(Arrays.deepToString(filesFiltered.toArray()));

        System.out.println("------------------------------------------");
        List<FileData> removedFiles = fileNavigator.remove(basePath + 2);
        System.out.println("Files that were removed for '" + basePath + 2 + "' path: " );
        System.out.println(Arrays.deepToString(removedFiles.toArray()));

        System.out.println("------------------------------------------");
        System.out.println("Repeat removing '" + basePath + 2 + "', after removing: " );
        System.out.println(Arrays.deepToString(fileNavigator.remove(basePath + 2).toArray()));
        System.out.println("Should be an empty list, as nothing should be found");

        System.out.println("------------------------------------------");
        System.out.println("Files sorted by size: ");
        List<FileData> filesSorted = fileNavigator.sortBySize();
        System.out.println(Arrays.deepToString(filesSorted.toArray()));

        System.out.println("------------------------------------------");
        System.out.println("Checking for path consistency: ");
        try {
            fileNavigator.add("/another/path/", new FileData("fileWithErrorPath", 10, "/path/to/file"));
        } catch (FilePathConsistencyException e) {
            System.out.println("Inconsistent file was rejected.");
        }
    }
}
