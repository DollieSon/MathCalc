// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        MathCalc Mc = new MathCalc("1+4*2/4");
        System.out.println("Solution:"+ Mc.SolveMdas());
        System.out.println("Solution:"+ Mc.SolveSeq());
        System.out.println("Done");
    }
}