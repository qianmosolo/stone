package stone.ast;
import stone.Environment;
import stone.StoneException;

import java.util.List;

public class WhileStmnt extends ASTList {
    public WhileStmnt(List<ASTree> c) { super(c); }
    public ASTree condition() { return child(0); }
    public ASTree body() { return child(1); }
    public String toString() {
        return "(while " + condition() + " " + body() + ")";
    }

    @Override
    public Object eval(Environment env) {
        Object result = 0;
        while (checkCondition(env)) {
            result = body().eval(env);
        }
        return result;
    }

    private boolean checkCondition(Environment env) {
        Object condition=condition().eval(env);
        boolean con;
        if (condition instanceof Boolean) {
            con = ((Boolean) condition).booleanValue();
        } else if (condition instanceof Integer) {
            int intval = ((Integer) condition).intValue();
            con = intval != 0 ? true : false;
        } else {
            throw new StoneException("无法判断while语句中的条件的正确性 ", this);
        }
        return con;
    }
}
