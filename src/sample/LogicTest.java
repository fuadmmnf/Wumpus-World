package sample;

import org.logicng.formulas.Formula;
import org.logicng.formulas.FormulaFactory;
import org.logicng.io.parsers.ParserException;
import org.logicng.io.parsers.PropositionalParser;

public class LogicTest {


    public static void main(String[] args)
    {
        FormulaFactory f = new FormulaFactory();
        PropositionalParser p = new PropositionalParser(f);
        Formula formula = null;
        try{
            formula= p.parse("A => B");
            final Formula cnf = formula.cnf();

            System.out.println(cnf.toString());
        } catch(ParserException e) {
            e.printStackTrace();
        }



    }
}
