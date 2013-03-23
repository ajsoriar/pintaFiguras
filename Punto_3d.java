public class Punto_3d implements Cloneable {

    int x, y, z;
    
    Punto_3d ( int a , int b, int c ) {
        x = a; 
        y = b;
        z = c;
    }

    Punto_3d () {
        this (0,0,0);
    }
	
	int getX(){ return x; }
	int getY(){ return y; }
	int getZ(){ return z; }
	void setX(int x){ this.x = x; }	
	void setY(int y){ this.y = y; }
	void setZ(int z){ this.z = z; }
}

