package editor;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FileWorker {
    public static Stream<String> readFile(String fileName) {
        Stream<String> result = Stream.of("");
        try {
            File file = new File(fileName);
            BufferedReader vin = new BufferedReader(new FileReader(file));
            result = vin.lines();

            //String newLine = System.getProperty("line.separator");
            //InputStream inputStream = new FileInputStream(file);
            //result = inputStream.readAllBytes();

        }
        catch (IOException e) {
            System.out.println("Cannot read file: " + e.getMessage());
        }
        catch (RuntimeException e) {
            System.out.println("RuntimeException: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return result;
    }

    public static byte[] readByteFile(String fileName) {
        byte[] result = null;
        try {
            File file = new File(fileName);

            if (file.isFile()) {
                InputStream inputStream = new FileInputStream(file);
                result = inputStream.readAllBytes();
            }


        }
        catch (IOException e) {
            System.out.println("Cannot read file: " + e.getMessage());
        }
        catch (RuntimeException e) {
            System.out.println("RuntimeException: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return result;
    }

    public static void saveFile(String fileName, String data) {
        File file = new File(fileName);
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
