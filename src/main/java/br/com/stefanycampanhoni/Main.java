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
        String inputFilePath = "teste.csv";
        String outputFilePath = "new-teste.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
                PrintWriter writer = new PrintWriter(outputFilePath, "UTF-8")) {

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
        return (html == null || html.isEmpty() ? html : html.replaceAll("\\<.*?>", "").trim().replaceAll(" +", " "));
    }

    private static String sanitize(String text) {
        if (text == null)
            return "";

        return getTextFromHTML(
                text.replaceAll("\r", "")
                        .replaceAll("\t", "")
                        .replaceAll("_x000D_", ""))
                .replaceAll("#", "*")
                .replaceAll("\\n", " ")
                .replaceAll("\n", " ")
                .replaceAll("\"", "'")
                .replaceAll("--", "-")
                .replaceAll("__", "_")
                .replaceAll("&[a-z]+;", " ")
                .replaceAll("//s+", " ")
                .replaceAll("  ", " ")
                .replaceAll("0000", "")
                .replaceAll("\\\\x[0-9A-F]+", "")
                .trim();
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

                // All this replaces will be change to regex
                .replaceAll("&ccedil;", "ç")
                .replaceAll("&Ccedil;", "Ç")
                .replaceAll("&uacute;", "ú")
                .replaceAll("&Uacute;", "Ú")
                .replaceAll("&aacute;", "á")
                .replaceAll("&Aacute;", "Á")
                .replaceAll("&eacute;", "é")
                .replaceAll("&Eacute;", "É")
                .replaceAll("&Iacute;", "Í")
                .replaceAll("&iacute;", "í")
                .replaceAll("&oacute;", "ó")
                .replaceAll("&Oacute;", "Ó")
                .replaceAll("&ecirc;", "ê")
                .replaceAll("&Ecirc;", "Ê")
                .replaceAll("&atilde;", "ã")
                .replaceAll("&Atilde;", "Ã")
                .replaceAll("&otilde;", "õ")
                .replaceAll("&Otilde;", "Õ")
                .trim();
    }
}