package hw_10;

public class FilePathConsistencyException extends RuntimeException {
    public FilePathConsistencyException(String path, FileData file) {
        super("File path mismatch: key path '" + path
                + "' does not match FileData path '" + file.getPath()
                + "' for " + file);
    }
}
