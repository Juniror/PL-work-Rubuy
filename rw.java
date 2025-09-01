import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class rw {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter the file name to work with (.txt): ");
        String filename = sc.nextLine();
        while (true) {
            System.out.println("""
                    1.Check
                    2.FileReader and FileWriter
                    3.Byte
                    4.BufferReader and BufferWriter
                    5.Exit""");
            System.out.print("type number for command : ");
            String command = sc.nextLine();
            switch (command) {
                case "check", "1":
                    checkRW(filename);
                    break;
                case "2":
                    byFileReaderandFileWriter(filename);
                    break;
                case "3":
                    readByBtye(filename);
                    break;
                case "4":
                    readByBufferReaderandBufferWriter(filename);
                    break;
                case "exit", "5":
                    sc.close();
                    return;
                default:
                    System.out.println("Wrong command");

            }
        }
    }

    static void checkRW(String filename) {
        Path path = Paths.get(filename);
        if (Files.exists(path)) {
            System.out.println("Readable : " + Files.isReadable(path));
            System.out.println("Writable : " + Files.isWritable(path));
        }
    }
    static void setrwxToFalse(String filename){
        File file = new File(filename);
        file.setReadable(false);
        file.setWritable(false);
        file.setExecutable(false);
    }

    static void byFileReaderandFileWriter(String filename) {
        System.out.print("read or write : ");
        String command = sc.nextLine();
        int ch;
        if (command.equals("read")) {
            try (FileReader fr = new FileReader(filename)) {
                System.out.println("--- Output ---");

                while ((ch = fr.read()) != -1) {
                    System.out.print((char) ch);
                }
                System.out.println("--------------");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (FileWriter fw = new FileWriter(filename)) {
                System.out.println("What text u want to type in : ");
                String text = sc.nextLine();
                fw.write(text);
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    static void readByBtye(String filename) {

        Path path = Paths.get(filename);
        System.out.print("read or write : ");
        String command = sc.nextLine();
        if (command.equals("read")) {
            try {
                System.out.println("--- Output ---");

                byte[] fileArray = Files.readAllBytes(path);
                for (byte b : fileArray) {
                    System.out.print((char) b);
                }
                System.out.println("--------------");

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                byte[] text = sc.nextLine().getBytes();
                Files.write(path, text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    static void readByBufferReaderandBufferWriter(String filename) {

        Path path = Paths.get(filename);
        Charset charset = Charset.forName("US-ASCII");
        System.out.print("read or write : ");
        String command = sc.nextLine();
        if (command.equals("read")) {
            try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
                String line = null;
                System.out.println("--- Output ---");
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                System.out.println("--------------");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
                String text = sc.nextLine();
                writer.write(text.toCharArray(), 0, text.length());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}