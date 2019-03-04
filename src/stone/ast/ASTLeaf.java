package stone.ast;
import java.util.Iterator;
import java.util.ArrayList;

import stone.Environment;
import stone.StoneException;
import stone.Token;

public class ASTLeaf extends ASTree {
    private static ArrayList<ASTree> empty = new ArrayList<ASTree>(); 
    protected Token token;
    public ASTLeaf(Token t) { token = t; }

    @Override
    public ASTree child(int i) { throw new IndexOutOfBoundsException(); }

    @Override
    public int numChildren() { return 0; }

    @Override
    public Iterator<ASTree> children() { return empty.iterator(); }

    @Override
    public String toString() { return token.getText(); }

    @Override
    public String location() { return "at line " + token.getLineNumber(); }

    @Override
    public Object eval(Environment env){
        throw new StoneException("无法执行 eval " + toString(), this);
    }

    public Token token() { return token; }
}
