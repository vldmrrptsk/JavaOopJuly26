package ru.academits.repetskiy.csv_main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static String[] parseLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                if (i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    currentField.append('"');
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (c == ',' && !inQuotes) {
                fields.add(currentField.toString());
                currentField = new StringBuilder();
            } else {
                currentField.append(c);
            }
        }

        fields.add(currentField.toString());
        return fields.toArray(new String[0]);
    }

    public static boolean hasUnclosedQuotes(String line) {
        int quoteCount = 0;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                if (i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    i++;
                } else {
                    quoteCount++;
                }
            }
        }

        return quoteCount % 2 != 0;
    }

    public static List<String[]> parseCSV(BufferedReader reader) throws IOException {
        List<String[]> records = new ArrayList<>();
        StringBuilder currentRecord = new StringBuilder();
        boolean inQuotes = false;
        String line;

        while ((line = reader.readLine()) != null) {
            boolean lineHasUnclosedQuotes = hasUnclosedQuotes(line);

            if (inQuotes || lineHasUnclosedQuotes) {
                if (!currentRecord.isEmpty()) {
                    currentRecord.append("\n");
                }
                currentRecord.append(line);
                inQuotes = !inQuotes;

                if (!inQuotes) {
                    String[] fields = parseLine(currentRecord.toString());
                    records.add(fields);
                    currentRecord = new StringBuilder();
                }
            } else {
                if (!currentRecord.isEmpty()) {
                    String[] fields = parseLine(currentRecord.toString());
                    records.add(fields);
                    currentRecord = new StringBuilder();
                }

                String[] fields = parseLine(line);
                records.add(fields);
            }
        }

        if (!currentRecord.isEmpty()) {
            String[] fields = parseLine(currentRecord.toString());
            records.add(fields);
        }

        return records;
    }

    public static void main(String[] args) {
        String csvFilePath = "C:\\Users\\vladi\\OpenIDEProjects\\JavaOopJuly26\\CSV\\src\\example.csv";

        List<String> htmlLines = new ArrayList<>();
        htmlLines.add("<table>");

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            List<String[]> records = parseCSV(reader);

            for (int i = 0; i < records.size(); i++) {
                String[] record = records.get(i);
                htmlLines.add("  <tr>");

                for (String field : record) {
                    String escapedField = field.replace("\n", "<br/>");
                    htmlLines.add("    <td>" + escapedField + "</td>");
                }

                htmlLines.add("  </tr>");
            }

        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            e.printStackTrace();
        }

        htmlLines.add("</table>");

        try {
            Files.write(Paths.get("C:\\Users\\vladi\\OpenIDEProjects\\JavaOopJuly26\\CSV\\src\\table.html"), htmlLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
