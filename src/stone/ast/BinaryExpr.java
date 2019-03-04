package stone.ast;
import stone.Environment;
import stone.StoneException;
import stone.ast.Name;
import java.util.List;

public class BinaryExpr extends ASTList {
    public BinaryExpr(List<ASTree> c) { super(c); }
    public ASTree left() { return child(0); }
    public String operator() {
        return ((ASTLeaf)child(1)).token().getText();
    }
    public ASTree right() { return child(2); }

    @Override
    public Object eval(Environment env) {
        String op = operator();
        if (op.equals("=")) {
            Object right = right().eval(env);
            return computeAssign(env, right);
        } else {
            Object left = left().eval(env);
            Object right = right().eval(env);
            return computeOP(left, right, op);
        }
    }

    private Object computeOP(Object left, Object right, String op){
        if (left instanceof Integer && right instanceof Integer){
            return computeNumber((Integer) left, (Integer) right, op);
        } else if (op.equals("+")) {
            return String.valueOf(left) + String.valueOf(right);
        } else if (op.equals("==")) {
            if (left==null){
                return left == null ? true : false;
            } else {
                return left.equals(right);
            }
        } else {
            throw new StoneException("无法应用 " + op, this);
        }
    }

    private Object computeNumber(Integer left, Integer right, String op) {
        int a = left.intValue();
        int b = right.intValue();
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            case "%":
                return a % b;
            case "==":
                return a==b;
            case ">":
                return a>b;
            case "<":
                return a<b;
            default:
                throw new StoneException("无法识别的符号　" + op, this);
        }
    }

    private Object computeAssign(Environment env, Object rvalue){
        ASTree left = left();
        if (left instanceof Name){
            env.put(((Name) left).name(), rvalue);
            return rvalue;
        }
        throw new StoneException("无法应用 = ", this);
    }
}
