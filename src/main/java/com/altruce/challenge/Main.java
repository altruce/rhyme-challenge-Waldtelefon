package com.altruce.challenge;

import org.json.simple.JSONObject;

import java.util.*;

public class Main {

    static ArrayList<String> rhymeList = new ArrayList<>();
    static JSONObject rhymes;


    public static void main(final String[] args) {
        try {
            String completedRhymes = "";
            JSONReader jsonReader = new JSONReader();
            rhymes = jsonReader.readFile("src/main/resources/rhymes.json");
            //
            String rhymes = "Als ich aufsah\nwar niemand mehr _.\n\nEs war, als hätt' der Himmel\ndie Erde still geküsst,\nDass sie im _\nVon Ihm nun träumen _.\n\nDie Luft ging durch die Felder,\nDie Ähren wogten sacht,\nEs rauschten leis' die _\nSo sternklar war die _.";

            // TODO - export / illustrate rhymes

            // even if you created a fancy export / illustration from the completed rhymes
            // we need this print to run the automated grading test
            completedRhymes = versesIntoString(rhymes);
            System.out.println(completedRhymes);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }


    public static ArrayList<String> rhymeSplitter(String allRhymes) {
        ArrayList<String> bbb = new ArrayList<>(Arrays.asList(allRhymes.split("\n")));

        Iterator<String> iterator = bbb.iterator();
        String temp;
        while (iterator.hasNext()) {
            temp = iterator.next();
            if (temp.equals("")) {
                iterator.remove();
            } else {
                rhymeList.add(sanitizeLine(temp));
            }
        }
        return rhymeList;
    }

    public static String replaceEmptyRhyme(String key, String lineToReplace) {
        String cleanLine = lineToReplace.substring(0, lineToReplace.lastIndexOf(" "));
        if (key.endsWith(",")) {
            key = key.substring(0, key.length() - 1);
        }
        return cleanLine + " " + rhymes.get(key);
    }

    public static String sanitizeLine(String line) {
        if (line.endsWith(".")) {
            return line.substring(0, line.length() - 1);
        } else return line;
    }

    public static String lastWordOfRhyme(String line) {
        return line.substring(line.lastIndexOf(" ") + 1);
    }

    public static ArrayList<String> verseBuilder(ArrayList<String> lines) {
        Queue<String> rhymeQue = new LinkedList<>();
        ArrayList<String> verse = new ArrayList<>();
        for (String line : lines) {
            if (!line.endsWith("_")) {
                rhymeQue.add(lastWordOfRhyme(line));
                verse.add(line);
            } else {
                verse.add(replaceEmptyRhyme(rhymeQue.poll(), line));
            }
        }
        return verse;
    }

    public static String versesIntoString(String rhymes) {
        ArrayList<String> verse = new ArrayList<>(verseBuilder(rhymeSplitter(rhymes)));
            String completedRhymes = "";
        for (String line : verse) {
            completedRhymes = completedRhymes + "\n" + line;
        }
        return completedRhymes;
    }
}
