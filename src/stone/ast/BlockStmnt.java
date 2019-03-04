package stone.ast;
import stone.Environment;

import java.util.List;

public class BlockStmnt extends ASTList {
    public BlockStmnt(List<ASTree> c) { super(c); }

    @Override
    public Object eval(Environment env){
        Object result = 0;
        for (ASTree ast:this){
            if (!(ast instanceof NullStmnt)){
                result=ast.eval(env);
            }
        }
        return result;
    }
}
