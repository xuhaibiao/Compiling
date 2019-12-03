package test.test1;

import java.io.*;
import java.util.Vector;

/**
 * @author HaibiaoXu
 * @date Create in 14:40 2019/10/19
 * @modified By
 */
public class WordAnalyze {
    public static void main(String[] args) {
        File fileIn,fileOut;
        FileWriter writer = null;
        FileReader reader = null;
        try {
            fileIn = new File("E:\\Java_in_idea\\Compiling\\src\\data\\test1data\\dataIn.txt");
            fileOut = new File("E:\\Java_in_idea\\Compiling\\src\\data\\test1data\\dataOut.txt");
            writer = new FileWriter(fileOut);
            reader = new FileReader(fileIn);

            int length = (int) fileIn.length();
            char buf[] = new char[length+1];
            reader.read(buf);

            String[] keyword = {"const","procedure","odd","call","do","read","write","begin", "end", "then", "if", "while", "var"};
            String[] operator = {"+", "*", "-", "/", "<", ">", "<=", ">=", ":=","=","#"};
            char[] operatorFirst = {'+', '*', '-', '/', '<', '>', '=',':','#'};
            char[] boundarySymbol = {'(', ')', ',', '.',';'};

            Test1 test1 = new Test1(keyword, operator, operatorFirst, boundarySymbol);
            Vector<String> result = test1.analyze(buf);
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
