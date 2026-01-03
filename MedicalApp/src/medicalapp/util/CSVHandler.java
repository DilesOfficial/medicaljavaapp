package medicalapp.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {

    // Generic reader creates a list of String arrays, higher level classes map to objects
    public static List<String[]> read(String filePath) {
        List<String[]> data = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) return data;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                // Basic clean up
                for(int i=0; i<parts.length; i++) {
                    parts[i] = parts[i].trim();
                }
                data.add(parts);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void write(String filePath, List<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
