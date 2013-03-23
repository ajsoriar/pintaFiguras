// DimeDistancia entre dos puntos
public class Operaciones {

    public static double DimeDistancia_3d ( Punto_3d a , Punto_3d b) {
        System.out.println("--> DimeDistancia!");
        double distancia = Math.sqrt(  Math.pow(a.getX()-b.getX(),2)  +  Math.pow(a.getY()-b.getY(),2)  +  Math.pow(a.getZ()-b.getZ(),2)  );
        return distancia;
    }

    public static double DimeDistancia_2d ( Punto_2d a , Punto_2d b) {
        System.out.println("--> DimeDistancia!");
        double distancia = Math.sqrt(  Math.pow(a.getX()-b.getX(),2)  +  Math.pow(a.getY()-b.getY(),2) );
        return distancia;
    }
}

