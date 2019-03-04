import stone.*;

public class LexerRunner {
    public static void main(String[] args) throws ParseException {
        Lexer l = new Lexer(new CodeDialog());
        for (Token t; (t = l.read()) != Token.EOF; ) // first: declare, `l.read` return Token
            System.out.println("=> " + t.getText());
    }
}
