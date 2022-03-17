import java.io.File;

public class Checker {
    public static void checkFile(File file) {
        if(!file.isFile())
            throw new IllegalArgumentException("file not file");
    }
}