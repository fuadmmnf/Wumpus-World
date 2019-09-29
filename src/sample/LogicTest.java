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
            formula= p.parse("P22 => (B21 | B12 | B32 | B23)");
            final Formula cnf = formula.cnf();

            System.out.println(cnf.toString());
        } catch(ParserException e) {
            e.printStackTrace();
        }



    }
}
