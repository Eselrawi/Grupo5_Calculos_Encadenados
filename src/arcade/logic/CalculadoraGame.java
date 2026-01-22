package src.arcade.logic;

import java.util.Random;

public class CalculadoraGame {
    private int resultadoActual;
    private boolean primerTurno;
    private Random random;

    public CalculadoraGame() {
        this.random = new Random();
        reset();
    }

    public void reset() {
        this.resultadoActual = 0;
        this.primerTurno = true;
    }

    public String generarSiguienteOperacion() {
        int num2 = random.nextInt(9) + 1; // Número del 1 al 9
        String[] operadores = {"+", "-", "*"};
        String op = operadores[random.nextInt(3)];

        if (primerTurno) {
            int num1 = random.nextInt(9) + 1;
            resultadoActual = calcular(num1, num2, op);
            primerTurno = false;
            return "OPERACION " + num1 + " " + op + " " + num2;
        } else {
            // El resultado anterior se usa como base
            int anterior = resultadoActual;
            resultadoActual = calcular(anterior, num2, op);
            return "OPERACION " + anterior + " " + op + " " + num2;
        }
    }

    public int calcular(int n1, int n2, String op) {
        if (op.equals("+")) {
            return n1 + n2;
        } else if (op.equals("-")) {
            return n1 - n2;
        } else if (op.equals("*")) {
            return n1 * n2;
        } else {
            return 0;
        }
    }

    public boolean validarRespuesta(int respuestaUsuario) {
        return respuestaUsuario == resultadoActual;
    }

    public int getResultadoActual() {
        return resultadoActual;
    }
}
