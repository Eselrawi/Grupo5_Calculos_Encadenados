“Este método es una prueba”
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
@Test
void TestSuma () {
    CalculadoraGame calc = new CalculadoraGame();
    int resultado = calc.sumar(2, 2);
    assertEquals(4, resultado);
}