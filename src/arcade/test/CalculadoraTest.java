package src.arcade.test;

import src.arcade.logic.CalculadoraGame; 
import static org.junit.Assert.*; 
import org.junit.Test;

public class CalculadoraTest {

    @Test
    public void testFlujoDeCalculo() {
        CalculadoraGame juego = new CalculadoraGame();
        
        // 1. Probar que genera operación inicial
        String op1 = juego.generarSiguienteOperacion();
        assertNotNull(op1);
        
        // 2. Validar respuesta correcta
        int correcto = juego.getResultadoActual();
        assertTrue(juego.validarRespuesta(correcto));
        
        // 3. Probar encadenamiento
        String op2 = juego.generarSiguienteOperacion();
        assertTrue(op2.contains(String.valueOf(correcto)));
    }
}