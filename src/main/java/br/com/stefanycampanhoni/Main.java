package br.com.stefanycampanhoni;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String inputFilePath = "test.csv";
        String outputFilePath = "new-test.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
                PrintWriter writer = new PrintWriter(outputFilePath, "utf-8")) {

            String line;
            while ((line = reader.readLine()) != null) {
                line = removeHTML(line);
                String sanitizedLine = sanitize(line);
                writer.println(sanitizedLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getTextFromHTML(String html) {
        return (html == null || html.isEmpty() ? html : html.replaceAll("\\<.*?>", "").replaceAll(" +", " "));
    }

    private static String sanitize(String text) {
        if (text == null)
            return "";

        return getTextFromHTML(
                text.replaceAll("_x000D_", ""))
                .replaceAll("#", "*")
                .replaceAll("\"", "'")
                .replaceAll("--", "-")
                .replaceAll("__", "_")
                .replaceAll("&[a-z]+;", " ")
                .replaceAll("//s+", " ")
                .replaceAll("  ", " ")
                .replaceAll("\\\\x[0-9A-F]+", "");
    }

    private static String removeHTML(String input) {
        if (input == null)
            return "";

        // If eu want to replace another special simbol, just add a another element in
        // the map, using .put({key}, {value}) method
        Map<String, String> replacements = new HashMap<>();
        replacements.put("&ccedil;", "ç");
        replacements.put("&Ccedil;", "Ç");
        replacements.put("&uacute;", "ú");
        replacements.put("&Uacute;", "Ú");
        replacements.put("&aacute;", "á");
        replacements.put("&Aacute;", "Á");
        replacements.put("&eacute;", "é");
        replacements.put("&Eacute;", "É");
        replacements.put("&Iacute;", "Í");
        replacements.put("&iacute;", "í");
        replacements.put("&oacute;", "ó");
        replacements.put("&Oacute;", "Ó");
        replacements.put("&ecirc;", "ê");
        replacements.put("&Ecirc;", "Ê");
        replacements.put("&atilde;", "ã");
        replacements.put("&Atilde;", "Ã");

        // For each element in the map, it will be replaced with the appropriate symbol.
        for (Map.Entry<String, String> htmlSpecialSimbols : replacements.entrySet()) {
            input.replaceAll(htmlSpecialSimbols.getKey(), htmlSpecialSimbols.getValue());
        }

        return input.replaceAll("<br/>", " ")
                .replaceAll("\n", " ")
                .replaceAll("  ", " ")
                .replaceAll(";dada", "");
    }
}