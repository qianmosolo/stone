package stone.ast;
import stone.Environment;
import stone.StoneException;
import stone.Token;

public class Name extends ASTLeaf {
    public Name(Token t) { super(t); }
    public String name() { return token().getText(); }

    @Override
    public Object eval(Environment env){
        Object value=env.get(name());
        if (value==null){
            throw new StoneException("找不到　" + name() + "　的定义", this);
        }
        else return value;
    }
}
