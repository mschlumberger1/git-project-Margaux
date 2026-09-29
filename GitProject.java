import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GitProject {
    public static void main(String[] args) {
        init();


    }

    public static void init() {
        try {
            // making git folder
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
}
