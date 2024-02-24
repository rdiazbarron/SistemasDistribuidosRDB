package org.example;

public class Empleado {
    private String nombre;
    private float salario;
    private String dep;

    public Empleado(String nombre, float salario, String dep) {
        this.nombre = nombre;
        this.salario = salario;
        this.dep = dep;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", salario=" + salario +
                ", dep='" + dep + '\'' +
                '}';
    }
}
