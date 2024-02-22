import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {

        String filename = "src/main/resources/input.txt";
//        System.out.println(runDay13Code(filename));
        System.out.println(runDay13CodeP2(filename));

    }

    public static long runDay13Code(String filename){

        int rollingSum = 0;

        File inputFile = new File(filename);

        ArrayList<PatternMap> patternMaps = initialisePatternMaps(inputFile);

        populatePatternMaps(inputFile, patternMaps);

        for (PatternMap pattern :
                patternMaps) {
            rollingSum += pattern.summarisePatternNotes();
        }

        return rollingSum;
    }

    public static ArrayList<PatternMap> initialisePatternMaps(File inputFile){

        ArrayList<PatternMap> patternMaps = new ArrayList<>();
        int rowCount = 0, charCount = 0;

        try {
            Scanner lineCounter = new Scanner(inputFile);

            while (lineCounter.hasNextLine()) {
                String nextLine = lineCounter.nextLine();
                if (Objects.equals(nextLine, "")){
                    patternMaps.add(new PatternMap(rowCount, charCount));
                    rowCount = 0;
                    charCount = 0;
                } else {
                    rowCount++;
                    charCount = nextLine.length();
                }
            }
            lineCounter.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return patternMaps;
    }

    private static void populatePatternMaps(File inputFile, ArrayList<PatternMap> patternMaps) {

        int patternMapIndex = 0;
        try {
            Scanner lineCounter = new Scanner(inputFile);

            while (lineCounter.hasNextLine()) {
                String nextLine = lineCounter.nextLine();
                if (Objects.equals(nextLine, "")){
                    patternMapIndex++;
                } else {
                    patternMaps.get(patternMapIndex).populateMapMatrix(nextLine);
                }
            }
            lineCounter.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static long runDay13CodeP2(String filename){

        int rollingSum = 0;

        File inputFile = new File(filename);

        ArrayList<PatternMap> patternMaps = initialisePatternMaps(inputFile);

        populatePatternMaps(inputFile, patternMaps);

        for (PatternMap pattern :
                patternMaps) {
            rollingSum += pattern.summarisePatternNotesP2();
        }

        return rollingSum;
    }
}
