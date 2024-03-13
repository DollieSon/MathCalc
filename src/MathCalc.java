import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MathCalc {
    String Equation;
    public List<String> infix;
    public Stack<String> prefix;
    public MathCalc(String Equation){
        //infix
        infix = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(char x: Equation.toCharArray()){
            int isOp = GetOps(String.valueOf(x));
            if(isOp != -1){
                infix.add(sb.toString());
                sb.setLength(0);
                infix.add(String.valueOf(x));
            }else{
                sb.append(x);
            }
        }
        infix.add(sb.toString());

        //prefix
        prefix = new Stack<>();
        Stack<Integer> ops = new Stack<>();
        String[] opString = {"-","+","/","*"};
        for (String Elem:infix) {
            int Opstat = GetOps(Elem);
            if(Opstat != -1){
                while(!ops.isEmpty() && ops.peek() > Opstat){
                    prefix.push(opString[ops.pop()]);
                }
                ops.push(Opstat);
            }else {
                prefix.push(Elem);
            }
        }
        while(!ops.isEmpty()){
            prefix.add(opString[ops.pop()]);
        }
        Stack<String> tempStack = new Stack<>();
        //Reverse Prefix;
        while(!prefix.empty()){
            tempStack.push(prefix.pop());
        }
        prefix = tempStack;
    }


    private int GetOps(String myOp){
        String[] ops = {"-","+","/","*"};
        for (int i = 0; i < ops.length; i++) {
            if(myOp.equals(ops[i])){
                return i;
            }
        }
        return -1;
    }

    private Double SolveTwo(Double num1, Double num2, int op){
        switch (op){
            case 0:
                return num1 - num2;
            case 1:
                return num1 + num2;
            case 2:
                return num1 / num2;
            case 3:
                return num1 * num2;
        }
        return -1.0;
    }

    public Double SolveMdas(){
        Stack<String> tempPrefix = (Stack<String>) prefix.clone();
        Stack<Double> Solution = new Stack<>();
        while(!tempPrefix.empty()){
            String top = tempPrefix.pop();
            int operationNum = GetOps(top);
            if(operationNum != -1){
                Double num2 = Solution.pop();
                Double num1 = Solution.pop();
                Solution.push(SolveTwo(num1,num2,operationNum));
            }else {
                Solution.push(Double.valueOf(top));
            }
        }
        return Solution.peek();
    }

    public Double SolveSeq(){
        Double Res = Double.valueOf(infix.get(0));
        Double num2 = 1.0;
        int op;
        for (int i = 1; i < infix.size(); i++) {
            op = GetOps(infix.get(i));
            num2 = Double.valueOf(infix.get(++i));
            Res = Res = SolveTwo(Res,num2,op);

        }
        return Res;
    }
}
