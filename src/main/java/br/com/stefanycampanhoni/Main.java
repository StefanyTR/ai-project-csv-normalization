package br.com.stefanycampanhoni;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

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

        return input.replaceAll("&amp;", "&")
                .replaceAll("&lt;", "<")
                .replaceAll("&gt;", ">")
                .replaceAll("&quot;", "'")
                .replaceAll("&#39;", "'")
                .replaceAll("\r", "")
                .replaceAll("<br/>", " ")
                .replaceAll("\n", " ")
                .replaceAll("  ", " ")

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