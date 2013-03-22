// DimeDistancia entre dos puntos
public class DimeDistancia implements Cloneable {

    public static double DimeDistancia ( Punto a , Punto b) {

        System.out.println("--> DimeDistancia!");

        double distancia = Math.sqrt(  Math.pow(a.getX()-b.getX(),2)  +  Math.pow(a.getY()-b.getY(),2)  +  Math.pow(a.getZ()-b.getZ(),2)  );
        return distancia;

    }

}

