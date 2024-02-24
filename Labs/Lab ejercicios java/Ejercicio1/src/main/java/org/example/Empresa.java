package org.example;

import java.util.ArrayList;

public class Empresa {
    private ArrayList<Empleado> empleados;

    public Empresa(){
        this.empleados = new ArrayList<>();
    }

    public void agregarEmpleado(Empleado empleado){
        empleados.add(empleado);
    }

    public double calcularSalario(){
        double sumatotal = 0;
        for (Empleado emp : empleados){
            sumatotal = sumatotal + emp.getSalario();
        }
        return sumatotal;
    }

    public void devolverTodos(){
        for (Empleado emp: empleados){
            String result = emp.toString();
            System.out.println(result);
        }
    }
}
