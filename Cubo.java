public class Cubo () {
	
	int [] [] vertices = {   
		{ 20, 18, 22 },
		{ 18, 20, 18 },
		{ 16, 18, 16 },
		{ 25, 24, 22 }
    };

    int [] [] poligonos = {
		{ 0, 1, 5 },
		{ 6, 3, 1 },
		{ 1, 8, 1 },
		{ 2, 2, 2 }
    };

    int []centro = {0,0,0};
    int []posicion = {0,0,0};
    int escala = 1;
    int []rotacion = {0,0,0};

	Cubo (){
		System.out.printf("Cubo creado");
	}
    public int getEscala(){
        return this.escala;
    }
    public int getRotacion(){
        return this.rotacion;
    }
    public int getPosicion(){
        return this.posicion;
    }
    public int getCentro(){
        return this.centro;
    }
    /*
    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setRadio(int radio){
        this.radio = radio;
    }

    public void setAngulo(int angulo){
        this.angulo = angulo;
    }
    */

}