package stone.ast;
import stone.Environment;
import stone.StoneException;

import javax.jws.Oneway;
import java.util.List;

public class NegativeExpr extends ASTList {
    public NegativeExpr(List<ASTree> c) { super(c); }
    public ASTree operand() { return child(0); }

    @Override
    public String toString() {
        return "-" + operand();
    }

    @Override
    public Object eval(Environment env){
        Object v = operand().eval(env);
        if (v instanceof Integer){
            return new Integer(-((Integer) v).intValue());
        }
        else
            throw new StoneException("无法对此类型使用-号", this);
    }
}
