package src.arcade.logic;

public class CalculadoraTest {
    public void testSuma() {
        CalculadoraGame calc = new CalculadoraGame();
        calc.reset();
        // Test basic calculation
        int resultado = calc.calcular(2, 2, "+");
        System.out.println("Test result: " + resultado + " (expected: 4)");
        assert resultado == 4 : "Test failed: suma";
    }

    public static void main(String[] args) {
        CalculadoraTest test = new CalculadoraTest();
        test.testSuma();
        System.out.println("Test completed successfully!");
    }
}