package test.test1;

import java.util.Vector;

/**
 * @author HaibiaoXu
 * @date Create in 21:01 2019/12/3
 * @modified By
 */
public class Test1 {
    private  String[] keyword;
    private  String[] operator;
    private  char[] operatorFirst;
    private  char[] boundarySymbol;

    Test1(String[] keyword, String[] operator, char[] operatorFirst, char[] boundarySymbol) {
        this.keyword = keyword;
        this.operator = operator;
        this.operatorFirst = operatorFirst;
        this.boundarySymbol = boundarySymbol;
    }

    public Vector<String> analyze(char[] chars) {
        Vector<String> rs = new Vector<String>();
        String str = "";
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            str = "";
            if (c == ' ' || c == '\n' || c == '\t' || c == '\r') {
            } else if (Character.isLetter(c)) {
                while (Character.isLetter(c) || Character.isDigit(c)) {
                    str += c;
                    c =  chars[++i];
                }
                i--;
                if (isKey(str)) {
                    rs.add(str + "\t" + "关键字");
                    System.out.println(str + "\t" + "关键字");
                } else {
                    rs.add(str + "\t" + "标识符");
                    System.out.println(str + "\t" + "标识符");
                }
            } else if (c == '-' && Character.isDigit(chars[i + 1])) {
                int count = 0;
                str += c;
                c = chars[++i];
                while (Character.isDigit(c) || (c == '.' && Character.isDigit(chars[i + 1]))) {
                    if(c == '.'){
                        count++;
                    }
                    str += c;
                    c = chars[++i];
                }
                if (Character.isLetter(c)) {
                    i--;
                    rs.add(str + "\t" + "error");
                    System.out.println(str + "\t" + "error");

                } else {
                    i--;
                    rs.add(str + "\t" + "常数");
                    System.out.println(str + "\t" + "常数");
                }

            }else if(Character.isDigit(chars[i])){
                while (Character.isDigit(c) || (c == '.' && Character.isDigit(chars[i + 1]))) {
                    str += c;
                    c = chars[++i];
                }

                i--;
                rs.add(str + "\t" + "常数");
                System.out.println(str + "\t" + "常数");
            } else if (operatorEntrance(c)) {
                while (operatorEntrance(c)) {
                    str += c;
                    c = chars[++i];
                }
                i--;
                if (isOperator(str)) {
                    rs.add(str + "\t" + "运算符");
                    System.out.println(str + "\t" + "运算符");
                } else {
                    rs.add(str + "\t" + "error");
                    System.out.println(str + "\t" + "error");
                }
            } else {
                if (isBoundarySymbol(c)) {
                    rs.add(c + "\t" + "界限符");
                    System.out.println(c + "\t" + "界限符");
                }
            }
        }
        return rs;
    }

    public boolean isKey(String str) {
        for (String s : keyword) {
            if (s.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean operatorEntrance(char c) {
        for (char c1 : operatorFirst) {
            if (c1 == c) {
                return true;
            }
        }
        return false;
    }
    public boolean isOperator(String str) {
        for (String s : operator) {
            if (s.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean isBoundarySymbol(char c) {
        for (char c1 : boundarySymbol) {
            if (c1 == c) {
                return true;
            }
        }
        return false;
    }
}
