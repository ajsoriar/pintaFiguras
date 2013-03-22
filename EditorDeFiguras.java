import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//import DimeDistancia.*;

public class EditorDeFiguras extends JFrame {

    //int w = 800, h=600;
    int w, h;

    public EditorDeFiguras() {

        super("Hola mundo con ventanas!");
        setBackground(Color.lightGray);
        setSize(800,600);
        //setUndecorated(true);
        //pack(); No no no!
        setVisible(true);
        System.out.println("--> EditorDeFiguras!");

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                System.out.println("Resized!");
                CalculaUserInterface();
            }
        });

    }

    public void paint(Graphics g) {
        System.out.println("--> paint!");

        g.setColor(Color.black);
        g.fillRect(0,0,this.w, this.h);
        g.setColor(Color.white);
        g.drawRect(2,2,this.w-2, this.h-2);

        g.setColor(Color.yellow);
        g.drawString("Pinta pinta",40,160);

        g.setColor(Color.green);
        g.drawOval(100, 100, 75, 50);
        
        g.setColor(Color.red);
        g.drawLine(0,0,this.w, this.h);
        
    }

    public static void main(String args[]) {
        System.out.println("--> main!");
        
        EditorDeFiguras mf = new EditorDeFiguras();
        mf.addWindowListener( new WindowAdapter() {

            public void windowClosing( WindowEvent evt ){
                System.out.print("cerrar!");
                System.exit( 0 );
            }

            public void windowOpened(WindowEvent e) {
                
            }

        });

        System.out.println("pintar un punto");
        Punto a = new Punto();
        Punto b = new Punto(10,10,10);
        double distancia = DimeDistancia(a,b);
        System.out.println("distancia entre a y b: " + distancia);
    }

    // DimeDistancia entre dos puntos
    public static double DimeDistancia ( Punto a , Punto b) {
        System.out.println("--> DimeDistancia!");
        double distancia = Math.sqrt(  Math.pow(a.getX()-b.getX(),2)  +  Math.pow(a.getY()-b.getY(),2)  +  Math.pow(a.getZ()-b.getZ(),2)  );
        return distancia;
    }

    public void CalculaUserInterface (){
        System.out.println("--> CalculaUserInterface!");
        this.w = getContentPane().getWidth();
        this.h = getContentPane().getHeight();
        System.out.println("getContentPane --> ancho:"+this.w+" alto:"+this.h);
    }

}
