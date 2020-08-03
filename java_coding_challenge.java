import java.lang.Math;

import org.junit.Test;

import static org.junit.Assert.*;

public class java_coding_challenge {
    /* This solution just iterates through the string and dynamically the column substrings for our final string, circumventing the matrix step.
    There's no initial matrix with the floor(sqrt(L)) <= row <= column <= ceil(sqrt(L)) and row * column > L conditions, so we don't have to
    worry about space constraints. This solution takes O(L) + O(columns - 1) time and O(columns) space.
     */
    public static String encryption(String s) {
        s = s.replaceAll("\\s+", "");
        if (s.equals("")) {
            return "";
        }
        int L = s.length();
        double sqrt = Math.sqrt(L);
        int cols = (int) Math.ceil(sqrt);
        int col = 0;
        String[] colStr = new String[cols];
        String output = "";
        for (int i = 0; i < L; i++) {
            if (colStr[col] == null) {
                colStr[col] = "";
            }
            colStr[col] += s.charAt(i);
            if (col == cols - 1) {
                col = 0;
            } else {
                col++;
            }
        }
        for (int i = 0; i < cols - 1; i++) {
            output += colStr[i] + " ";
        }
        output += colStr[cols - 1];
        return output;
    }

    @Test
    public void testFunction() {
        assertEquals(encryption("haveaniceday"), "hae and via ecy");
        assertEquals(encryption("feedthedog"), "fto ehg ee dd");
        assertEquals(encryption("chillout"), "clu hlt io");
        assertEquals(encryption("if man was meant to stay on the ground god would have given us roots"), "imtgdvs fearwer mayoogo anouuio ntnnlvt wttddes aohghn sseoau");
        assertEquals(encryption(""), "");
        assertEquals(encryption("s"), "s");
        assertEquals(encryption("s g g b"), "sg gb");
    }
}

/* This is my original solution. It takes O(rows) more time than the solution above, since it first converts the string into a grid,
then takes the first, second, etc letter of each string and puts it into the output string. This solution also
takes more space (O(L) + O(rows)), and needs to account for space constraints of the initial matrix.
 public static String encryption(String s) {
     s = s.replaceAll("\\s+", "");
     int L = s.length();
     double sqrt = Math.sqrt(L);
     int rows = (int) Math.floor(sqrt);
     int cols = (int) Math.ceil(sqrt);
     if (rows * cols < L) {
         rows++;
     }
     String[] arr = new String[rows];
     for (int i = 0; i < rows; i++) {
         int end = 0;
         if ((i + 1) * (cols) > L) {
             end = L;
         } else {
             end = (i + 1) * cols;
         }
         arr[i] = s.substring(i * cols, end);
     }
     String[] finalArr = new String[cols];
     String output = "";
     for (int h = 0; h < cols; h++) {
         for (int i = 0; i < rows; i++) {
             if (finalArr[h] == null) {
                 finalArr[h] = "";
             }
             if (h < arr[i].length()) {
                 finalArr[h] = finalArr[h] + arr[i].charAt(h);
             }
         }
         if (h + 1 == cols) {
             output += finalArr[h];
         } else {
             output += finalArr[h] + " ";
         }
     }
     return output;
 }
/*/