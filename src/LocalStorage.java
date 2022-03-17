import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class LocalStorage {

    protected String readFile(File file) throws IOException {
        var inputStream = new FileInputStream(file);
        var bytes = inputStream.readAllBytes();
        inputStream.close();
        return new String(bytes);
    }

    protected void writeFile(File file, String value) throws IOException {
        var outputString = new FileOutputStream(file);
        outputString.write(value.getBytes());
        outputString.close();
    }
}
