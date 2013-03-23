public class Punto_2d implements Cloneable {

    int x, y;
    
    Punto_2d ( int a , int b) {
        x = a; 
        y = b;
    }

    Punto_2d () {
        this (0,0);
    }
	
	int getX(){ return x; }
	int getY(){ return y; }
	void setX(int x){ this.x = x; }	
	void setY(int y){ this.y = y; }
}

