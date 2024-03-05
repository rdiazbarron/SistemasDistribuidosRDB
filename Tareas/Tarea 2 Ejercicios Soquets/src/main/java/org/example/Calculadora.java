package org.example;

public class Calculadora {
    private int a;
    private int b;

    private String operador ;

    public Calculadora(int a, int b, String operador) {
        this.a = a;
        this.b = b;
        this.operador = operador;
    }
    public int calcular() {
        if (this.operador.equals("+")) {
            return this.a + this.b;
        }
        if (this.operador.equals("-")) {
            return this.a - this.b;
        }
        if (this.operador.equals("/")) {
            return this.a / this.b;
        }
        if (this.operador.equals("*")) {
            return this.a * this.b;
        }

        return 0;
    }
}

