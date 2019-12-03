package test.test2;

import java.io.*;
import java.util.*;

/**
 * @author HaibiaoXu
 * @date Create in 16:13 2019/12/3
 * @modified By
 */
public class SLR1 {
    public static void main(String[] args) {
        File fileIn,fileOut;
        FileWriter writer = null;
        FileReader reader = null;
        String str;
        try {
            fileIn = new File("E:\\Java_in_idea\\Compiling\\src\\data\\test2data\\SLRDataIn.txt");
            fileOut = new File("E:\\Java_in_idea\\Compiling\\src\\data\\test2data\\SLRDataOut.txt");
            writer = new FileWriter(fileOut);
            reader = new FileReader(fileIn);

            int length = (int) fileIn.length();
            char buf[] = new char[length+1];
            reader.read(buf);
            str = new String(buf);
            String[][] tableSLR = {
                    {"", "i", "=", "+", "*", "(", ")", "n", "#", "S", "E", "T", "F"},
                    {"0", "s2", "", "", "", "", "", "", "", "1", "", "", ""},
                    {"1", "", "", "", "", "", "", "", "acc", "", "", "", ""},
                    {"2", "", "s3", "", "", "", "", "", "", "", "", "", ""},
                    {"3", "s7", "s3", "", "", "s9", "", "s8", "", "", "4", "5", "6"},
                    {"4", "", "", "s11", "", "", "", "", "r1", "", "", "", ""},
                    {"5", "", "", "r3", "s12", "", "r3", "", "r3", "", "", "", ""},
                    {"6", "", "", "r5", "r5", "", "r5", "", "r5", "", "", "", ""},
                    {"7", "", "", "r6", "r6", "", "r6", "", "r6", "", "", "", ""},
                    {"8", "", "", "r7", "r7", "", "r7", "", "r7", "", "", "", ""},
                    {"9", "s7", "", "", "", "s9", "", "s8", "", "", "10", "5", "6"},
                    {"10", "", "", "s11", "", "", "s13", "", "", "", "", "", ""},
                    {"11", "s7", "", "", "", "s9", "", "s8", "", "", "", "14", "6"},
                    {"12", "s7", "", "", "", "s9", "", "s8", "", "", "", "", "15"},
                    {"13", "", "", "r8", "r8", "", "r8", "", "r8", "", "", "", ""},
                    {"14", "", "", "r2", "s12", "", "r2", "", "r2", "", "", "", ""},
                    {"15", "", "", "r4", "r4", "", "r4", "", "r4", "", "", "", ""}
            };

            String[][] expression = {
                    {"S'","1"},
                    {"S","3"},
                    {"E","3"},
                    {"E","1"},
                    {"T","3"},
                    {"T","1"},
                    {"F","1"},
                    {"F","1"},
                    {"F","3"}
            };

            Test2 test2 = new Test2(tableSLR,expression);
            Vector<String> result = test2.analyzeBySLR(str);
            for (int i = 0; i < result.size(); i++) {
                writer.write(result.get(i) + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                writer.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}

