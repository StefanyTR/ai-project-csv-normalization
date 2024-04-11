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
        String outputFilePath = "sanitized.csv";

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
                .trim();
    }

    private static String removeHTML(String input) {
        if (input == null)
            return "";

        return input.replace("&amp;", "&")
                .replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&quot;", "'")
                .replace("&#39;", "'")
                .replace("\r", "")
                .replace("<br/>", " ")
                .replace("\n", " ")
                .replaceAll("  ", " ")
                .trim();
    }
}