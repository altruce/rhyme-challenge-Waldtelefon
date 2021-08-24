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
//            completedRhymes = versesIntoString(String.join(" ", args));
            completedRhymes = versesIntoString(rhymeSplitter(rhymes));

            System.out.println(completedRhymes);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }


    public static ArrayList<ArrayList<String>> rhymeSplitter(String allRhymes) {
        ArrayList<ArrayList<String>> allVerses = new ArrayList<>();
        ArrayList<String> aaa = new ArrayList<>(List.of(allRhymes.split("\n\n")));

        for (String unSplitVerse : aaa) {
            ArrayList<String> bbb = new ArrayList<>(getOneVerse(unSplitVerse));
            ArrayList<String> ccc = new ArrayList<>();
            for (String line : bbb) {
                line = sanitizeLine(line);
                ccc.add(line);
            }
            ArrayList<String> ddd = verseBuilder(ccc);
            allVerses.add(ddd);
        }

        return allVerses;
    }

    public static ArrayList<String> getOneVerse(String unSplitVerse) {
        return new ArrayList<>(List.of(unSplitVerse.split("\n")));
    }

    public static String replaceEmptyRhyme(String key, String lineToReplace) {
        String cleanLine = lineToReplace.substring(0, lineToReplace.lastIndexOf(" "));
        if (key.endsWith(",")) {
            key = key.substring(0, key.length() - 1);
        }
        return cleanLine + " " + rhymes.get(key);
    }

    public static String sanitizeLine(String line) {
        if (line.endsWith("]")) {
            line = line.substring(0, line.length() - 1);
        }
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

    public static String versesIntoString(ArrayList<ArrayList<String>> allVerses) {
        String completedRhymes = "";
        for (ArrayList<String> verse : allVerses) {
            for (String line : verse){
            completedRhymes = completedRhymes + "\n" + line;
            }
            completedRhymes = completedRhymes + "\n";
        }
        return completedRhymes;
    }

}
