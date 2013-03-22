public class Punto implements Cloneable {

    int x, y, z;
    
    Punto ( int a , int b, int c ) {
        x = a; 
        y = b;
        z = c;
    }

    Punto () {
        this (0,0,0);
		/*
		this.x = a;
		this.y = b;
		*/
    }
	
	int getX(){ return x; }
	int getY(){ return y; }
	int getZ(){ return z; }
	void setX(int x){ this.x = x; }	
	void setY(int y){ this.y = y; }
	void setZ(int z){ this.z = z; }
}

