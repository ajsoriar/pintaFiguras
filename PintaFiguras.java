import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PintaFiguras extends JFrame { //} implements MouseListener, KeyListener {

    int w, h;

    public static void main(String args[]) {
        System.out.println("--> main!");
        
        PintaFiguras mf = new PintaFiguras();
        mf.addWindowListener( new WindowAdapter() {

            public void windowClosing( WindowEvent evt ){
                System.out.print("cerrar!");
                System.exit( 0 );
            }

            public void windowOpened(WindowEvent e) {
                
            }

        });

        System.out.println("pintar un punto");
        Punto_3d a = new Punto_3d();
        Punto_3d b = new Punto_3d(10,10,10);
        Operaciones operaciones = new Operaciones();
        double distancia = operaciones.DimeDistancia_3d(a,b);
        System.out.println("distancia entre a y b: " + distancia);
    }

    public PintaFiguras() {

        super("Hola mundo con ventanas!");
        setBackground(Color.lightGray);
        setSize(800,600);
        //setUndecorated(true);
        //pack(); No no no!
        setVisible(true);
        System.out.println("--> PintaFiguras");

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                System.out.println("Resized!");
                CalculaUserInterface();
            }
        });

        this.addMouseListener(new MouseAdapter() { 
            public void mousePressed(MouseEvent me) { 
                System.out.println(me); 
            } 
        }); 

        this.addKeyListener(new KeyAdapter() { 
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_LEFT:
                        System.out.print("Tecla IZQUIERDA");
                        break;

                    case KeyEvent.VK_RIGHT:
                        System.out.print("Tecla DERECHA");
                        break;
                }
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

    public void CalculaUserInterface (){
        System.out.println("--> CalculaUserInterface!");
        this.w = getContentPane().getWidth();
        this.h = getContentPane().getHeight();
        System.out.println("getContentPane --> ancho:"+this.w+" alto:"+this.h);
    }

}
