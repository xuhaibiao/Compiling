package test.test2;

import java.util.Stack;
import java.util.Vector;

/**
 * @author HaibiaoXu
 * @date Create in 20:54 2019/12/3
 * @modified By
 */
public class Test2 {
    private String[][] tableSLR ;

    private String[][] expression;

    private Stack<String> stackState = new Stack<>();

    private Stack<String> stackSymbol = new Stack<>();

    Test2(String[][] tableSLR, String[][] expression) {
        this.tableSLR = tableSLR;
        this.expression = expression;
    }

    public Vector<String> analyzeBySLR(String inputStr) {
        Vector<String> rs = new Vector<>();
        String str = inputStr.replace(" ", "");
        int inputIndex = 1;
        int index = 1;
        stackState.push("0");
        stackSymbol.push("#");
        String state;
        String input;
        rs.add("步骤"+"\t\t\t"+"状态栈"+"\t\t\t"+"符号栈"+"\t\t\t"+"当前输出字符"+"\t\t\t"+"ACTION"+"\t\t\t"+"GOTO");
        System.out.println("步骤"+"\t\t\t"+"状态栈"+"\t\t\t"+"符号栈"+"\t\t\t"+"当前输出字符"+"\t\t\t"+"ACTION"+"\t\t\t"+"GOTO");
        while (true) {
            state = stackState.peek();
            input = str.substring(inputIndex-1,inputIndex);

            if (action(state, input).charAt(0) == 's') {
                rs.add(index+"\t\t\t"+stackState.toString()+"\t\t\t"+stackSymbol.toString()+"\t\t\t"+input+"\t\t\t"+action(state, input)+"\t\t\t"+"");
                System.out.println((index+"\t\t\t"+stackState.toString()+"\t\t\t"+stackSymbol.toString()+"\t\t\t"+input+"\t\t\t"+action(state, input)+"\t\t\t"+""));
                stackState.push(action(state, input).substring(1));
                stackSymbol.push(input);
                inputIndex++;
                index++;
            } else if (action(state, input).charAt(0) == 'r') {
                int row = Integer.parseInt(action(state, input).substring(1));
                int count = Integer.parseInt(expression[row][1]);
                String stateTmp = stackState.get(stackState.size() - count - 1);
                String vn = expression[row][0];
                rs.add(index+"\t\t\t"+stackState.toString()+"\t\t\t"+stackSymbol.toString()+"\t\t\t"+input+"\t\t\t"+action(state, input)+"\t\t\t"+goTo(stateTmp,vn));
                System.out.println(index+"\t\t\t"+stackState.toString()+"\t\t\t"+stackSymbol.toString()+"\t\t\t"+input+"\t\t\t"+action(state, input)+"\t\t\t"+goTo(stateTmp,vn));
                for (int i = 0; i < count; i++) {
                    stackState.pop();
                    stackSymbol.pop();
                }
                stackState.push(goTo(stateTmp,vn));
                stackSymbol.push(vn);
                index++;
            } else if ("acc".equals(action(state, input))) {
                rs.add(index + "\t\t\t" + stackState.toString() + "\t\t\t" + stackSymbol.toString() + "\t\t\t" + input + "\t\t\t" + "acc" + "\t\t\t" + "");
                System.out.println(index + "\t\t\t" + stackState.toString() + "\t\t\t" + stackSymbol.toString() + "\t\t\t" + input + "\t\t\t" + "acc" + "\t\t\t" + "");
                break;
            } else {
                rs.add(index+"\t\t\t"+stackState.toString()+"\t\t\t"+stackSymbol.toString()+"\t\t\t"+input+"\t\t\t"+"ERROR!");
                System.out.println(index+"\t\t\t"+stackState.toString()+"\t\t\t"+stackSymbol.toString()+"\t\t\t"+input+"\t\t\t"+"ERROR!");
                 break;
            }
        }
        return rs;
    }

    public String action(String state,String input) {
        for (int i = 1; i < 17; i++){
            if (tableSLR[i][0].equals(state)){
                for (int j = 1; j < 9; j++){
                    if (tableSLR[0][j].equals(input)) {
                        return tableSLR[i][j];
                    }
                }
            }
        }
        return "";
    }

    public String goTo(String state, String input) {
        for (int i = 1; i < 17; i++){
            if (tableSLR[i][0].equals(state)){
                for (int j = 9; j < 13; j++){
                    if (tableSLR[0][j].equals(input)) {
                        return tableSLR[i][j];
                    }
                }
            }
        }
        return "";
    }

}
