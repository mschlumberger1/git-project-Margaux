import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class GitProject {
    public static void main(String[] args) throws IOException {
        // 2.1
        init();
        // testing 2.2
        System.out.println(hashFile("Hello.txt"));

    }

    // method goal: Create a git/ directory if it does not already exist; create an objects/
    // directory inside git/ if it does not already exist; create a file named index inside git/ if
    // it does not already exist, with no extension; create a file named HEAD inside git/ if it does
    // not already exist, with no extension
    public static void init() {
        try {
            File git = new File("git");
            File objects = new File("git/objects");
            File index = new File("git/index");
            File HEAD = new File("git/HEAD");
            if (git.exists() && objects.exists() && index.exists() && HEAD.exists()) {
                System.out.println("Git Repository Already Exists");
            } else {
                if (!git.exists()) {
                    git.mkdir();
                }
                if (!objects.exists()) {
                    objects.mkdir();
                }
                if (!index.exists()) {
                    index.createNewFile();
                }
                if (!HEAD.exists()) {
                    HEAD.createNewFile();
                }
                System.out.println("Git Repository Created");
            }
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    // method goal: Write a method that takes a file path, computes the SHA-1 hash of the file's
    // contents, and returns it as a String
    public static String hashFile(String filePath) throws IOException {
        Path path = Path.of(filePath);
        if (!Files.isRegularFile(path)) {
            throw new IOException("no such file: " + filePath);
        }
        byte[] fileBytes = Files.readAllBytes(path);
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-1 is not available", e);
        }
        byte[] hash = digest.digest(fileBytes);
        return HexFormat.of().formatHex(hash);
    }
}
