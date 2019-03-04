package stone.ast;
import stone.Environment;
import stone.StoneException;

import java.util.List;

public class IfStmnt extends ASTList {
    public IfStmnt(List<ASTree> c) { super(c); }
    public ASTree condition() { return child(0); }
    public ASTree thenBlock() { return child(1); }
    public ASTree elseBlock() {
        return numChildren() > 2 ? child(2) : null;
    }

    @Override
    public String toString() {
        return "(if " + condition() + " " + thenBlock()
                 + " else " + elseBlock() + ")";
    }

    @Override
    public Object eval(Environment env){
        if (checkCondition(env)) {
            return thenBlock().eval(env);
        } else {
            ASTree els = elseBlock();
            if (els != null) {
                return els.eval(env);
            }
            return 0;
        }
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
            throw new StoneException("无法判断if语句中的条件的正确性 ", this);
        }
        return con;
    }
}
