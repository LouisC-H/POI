import java.util.Arrays;

public class PatternMap {

    private final int rowCount;
    private final int colCount;
    private final char[][] mapMatrix;

    private int nextRowToBePopulated;

    public PatternMap(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.mapMatrix = new char[rowCount][colCount];
    }

    public void populateMapMatrix(String charRow){
        if (nextRowToBePopulated == rowCount){
            System.out.println("too many inputs");
        }
        for (int i = 0; i < charRow.length(); i++) {
            char c = charRow.charAt(i);
            mapMatrix[nextRowToBePopulated][i] = c;
        }
        nextRowToBePopulated++;
    }

    public int summarisePatternNotes() {
        int patternSummary = checkForVerticalMirror();

        if (patternSummary == 0){
            patternSummary = checkForHorizontalMirror();
        }

        return patternSummary;
    }

    private int checkForVerticalMirror() {
        // i is the column to the right of the theoretical mirror
        for (int i = 1; i < colCount; i++) {
            boolean allMirroredPairsChecked = false;
            int pairAIndex = i - 1;
            int pairBIndex = i;
            char[] pairA = new char[rowCount];
            char[] pairB = new char[rowCount];
            while (!allMirroredPairsChecked){
                // Populate the two paired columns
                for (int j = 0; j < rowCount; j++) {
                    pairA[j] = mapMatrix[j][pairAIndex];
                    pairB[j] = mapMatrix[j][pairBIndex];
                }
                // If this pair isn't equal, the mirror is disproved, so move onto the next one.
                if (!Arrays.equals(pairA, pairB)){
                    break;
                }
                // If we're not already at the edge of the pattern, move the pair apart
                if (pairAIndex != 0 && pairBIndex != colCount - 1){
                    pairAIndex--;
                    pairBIndex++;
                } else {
                    allMirroredPairsChecked = true;
                    return i;
                }
            }
        }
        return 0;
    }

    private int checkForHorizontalMirror() {
        // i is the row below the theoretical mirror
        for (int i = 1; i < rowCount; i++) {
            boolean allMirroredPairsChecked = false;
            int pairAIndex = i - 1;
            int pairBIndex = i;
            char[] pairA = new char[colCount];
            char[] pairB = new char[colCount];
            while (!allMirroredPairsChecked){
                // Populate the two paired columns
                System.arraycopy(mapMatrix[pairAIndex], 0, pairA, 0, colCount);
                System.arraycopy(mapMatrix[pairBIndex], 0, pairB, 0, colCount);
                // If this pair isn't equal, the mirror is disproved, so move onto the next one.
                if (!Arrays.equals(pairA, pairB)){
                    break;
                }
                // If we're not already at the edge of the pattern, move the pair apart
                if (pairAIndex != 0 && pairBIndex != rowCount - 1){
                    pairAIndex--;
                    pairBIndex++;
                } else {
                    allMirroredPairsChecked = true;
                    return 100* i;
                }
            }
        }
        return 0;
    }

    public int summarisePatternNotesP2() {
        int patternSummary = checkForVerticalMirrorP2();

        if (patternSummary == 0){
            patternSummary = checkForHorizontalMirrorP2();
        }

        return patternSummary;
    }

    private int checkForVerticalMirrorP2() {
        // i is the column to the right of the theoretical mirror
        for (int i = 1; i < colCount; i++) {
            boolean allMirroredPairsChecked = false;
            int pairAIndex = i - 1;
            int pairBIndex = i;
            char[] pairA = new char[rowCount];
            char[] pairB = new char[rowCount];
            int numImperfections = 0;
            while (!allMirroredPairsChecked){
                // Populate  and compare the two paired columns
                for (int j = 0; j < rowCount; j++) {
                    pairA[j] = mapMatrix[j][pairAIndex];
                    pairB[j] = mapMatrix[j][pairBIndex];
                    if (pairA[j] != pairB[j]){
                        numImperfections++;
                    }
                }
                // If we're not already at the edge of the pattern, move the pair apart
                if (pairAIndex != 0 && pairBIndex != colCount - 1){
                    pairAIndex--;
                    pairBIndex++;
                } else {
                    allMirroredPairsChecked = true;
                    if (numImperfections == 1){
                        return i;
                    }
                }
            }
        }
        return 0;
    }

    private int checkForHorizontalMirrorP2() {
        // i is the row below the theoretical mirror
        for (int i = 1; i < rowCount; i++) {
            boolean allMirroredPairsChecked = false;
            int pairAIndex = i - 1;
            int pairBIndex = i;
            char[] pairA = new char[colCount];
            char[] pairB = new char[colCount];
            int numImperfections = 0;
            while (!allMirroredPairsChecked){
                // Populate the two paired columns
                System.arraycopy(mapMatrix[pairAIndex], 0, pairA, 0, colCount);
                System.arraycopy(mapMatrix[pairBIndex], 0, pairB, 0, colCount);
                for (int j = 0; j < colCount; j++) {
                    if (pairA[j] != pairB[j]){
                        numImperfections++;
                    }
                }
                // If we're not already at the edge of the pattern, move the pair apart
                if (pairAIndex != 0 && pairBIndex != rowCount - 1){
                    pairAIndex--;
                    pairBIndex++;
                } else {
                    allMirroredPairsChecked = true;
                    if (numImperfections == 1){
                        return i * 100;
                    }
                }
            }
        }
        return 0;
    }
}
