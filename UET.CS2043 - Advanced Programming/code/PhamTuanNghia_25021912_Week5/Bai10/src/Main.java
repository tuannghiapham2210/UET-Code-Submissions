import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class InvalidConfigException extends Exception {
    public InvalidConfigException(String message) {
        super(message);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //getting the source path
        System.out.print("Enter the file path: ");
        String filePath = scanner.nextLine();

        BufferedReader reader = null;
        Map<String, String> configMap = new HashMap<>();

        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;

            //reading each line until the file ends
            while ((line = reader.readLine()) != null) {
                //if we encounter a space, skip it
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                //spliting and adding into map if it has the right format (key=value)
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    configMap.put(parts[0].trim(), parts[1].trim());
                }
            }

            //the config must contains username and timeout, if it doesnt contain any of those, throw an error
            if (!configMap.containsKey("username") || !configMap.containsKey("timeout")) {
                throw new InvalidConfigException("Missing required keys");
            }

            //if the config contains timeout, it must be > 0, otherwise , throw an error
            int timeout = Integer.parseInt(configMap.get("timeout"));
            if (timeout <= 0) {
                throw new InvalidConfigException("timeout must be greater than 0");
            }

            //if it has maxConnections, it must be >= 1
            if (configMap.containsKey("maxConnections")) {
                int maxConnections = Integer.parseInt(configMap.get("maxConnections"));
                if (maxConnections < 1) {
                    throw new InvalidConfigException("maxConnections must be >= 1");
                }
            }

            //if everything works, print out the config again
            for (Map.Entry<String, String> entry : configMap.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }

            System.out.println("Config loaded successfully.");

        } catch (FileNotFoundException e) {
            System.out.println("Config file not found.");

        } catch (IOException e) {
            System.out.println("I/O error.");
            e.printStackTrace();

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");

        } catch (InvalidConfigException e) {
            System.out.println("Invalid config: " + e.getMessage());

        } finally {
            try {
                if (reader != null) {
                    reader.close();

                }
                
            } catch (IOException e) {
                System.out.println("Error closing file.");

            }

            System.out.println("Program finished.");
            scanner.close();
        }
    }
}