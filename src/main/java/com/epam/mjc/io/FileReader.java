package com.epam.mjc.io;

import java.io.File;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {
        StringBuilder sb = new StringBuilder();

        try (java.io.FileReader in = new java.io.FileReader(file)) {
            int c;

            while ((c = in.read()) != -1) {
                sb.append((char)c);
            }

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        String s  = sb.toString();

        return new Profile(extractName(s), extractAge(s), extractEmail(s), extractPhone(s));
    }

    private String extractInfo(String input, String infoType) {
        String[] sArray = input.split("\n");

        for (String st: sArray) {
            if (st.toLowerCase().startsWith(infoType.toLowerCase())) {
                return st.split(" ")[1];
            }
        }
        return "Not found";
    }

    private String extractName(String input) {
        return extractInfo(input, "Name").trim();
    }

    private Integer extractAge(String input) {
        try {
            return Integer.parseInt(extractInfo(input, "Age").trim());
        }
        catch (IllegalArgumentException e) {
            return -1;
        }
    }

    private String extractEmail(String input) {
        return extractInfo(input, "Email").trim();
    }

    private Long extractPhone(String input) {
        try {
            return Long.parseLong(extractInfo(input, "Phone").trim());
        }
        catch (IllegalArgumentException e) {
            return -1L;
        }
    }
}
