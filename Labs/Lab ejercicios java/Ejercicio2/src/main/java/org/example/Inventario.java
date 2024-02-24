package org.example;

import java.util.ArrayList;

public class Inventario {
    private ArrayList<Producto> productos;

    public Inventario(){
        this.productos = new ArrayList<>();

    }

    public void agregarProds(Producto producto){
        productos.add(producto);
    }

    public double sumaTotal(){
        double sumatotal = 0;
        for (Producto p : productos){
            sumatotal = sumatotal +p.getPrecio();
        }
        return sumatotal;
    }

    public void leerProds(){

        for (Producto p : productos){
            String result = p.toString();
            System.out.println(result);
        }

    }
}
