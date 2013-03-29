import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OtraForma_6 {
	
    public static void main(String[] args) {
        System.out.println("--> main!");
        CreaFrame();
    }

    private static void CreaFrame() {
        System.out.println("--> CreaFrame!");
        JFrame f = new JFrame("Pinta pinta forever");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(640,480);
        MyJPanel superPanel = new MyJPanel();
        f.add(superPanel);
        f.pack(); // No se pa que vale.
        f.setVisible(true);
    }
}

class MyJPanel extends JPanel {

    int w, h, cont=0;
    MiHilo elHilo;

    public MyJPanel() {

        System.out.println("--> MyJPanel!");

        //setBorder(BorderFactory.createLineBorder(Color.black));

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                System.out.println("Resized!");
                w = getWidth();
                h = getHeight();
                System.out.println(e);           
            }
        });

        this.addMouseListener(new MouseAdapter() { 
            public void mousePressed(MouseEvent me) { 
                System.out.println(me); 
                System.out.println( "x:" + me.getX() + "y:" + me.getY() );
            } 
        }); 
        
        this.setFocusable(true); // Para que funcione la captura de teclado en un JPanel

        this.addKeyListener(new KeyAdapter() { 
            public void keyPressed(KeyEvent e) {

                System.out.println("--> tecla pulsada!");

                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_LEFT:
                        System.out.print("Tecla IZQUIERDA");
                        break;
                    case KeyEvent.VK_RIGHT:
                        System.out.print("Tecla DERECHA");
                        break;
                    case KeyEvent.VK_Q:
                        System.out.print("Tecla Q");
                        elHilo.detenerHilo(); 
                        break;
                }
            }
        }); 

        elHilo = new MiHilo();
        elHilo.start();        
    }

/*
    public void Tamanyo(int w, int h) {
        System.out.println("--> tamanyo!");
        this.w = w;
        this.h = h;
        System.out.println("MyPanel --> ancho:"+ this.w+" alto:"+ this.h);
    }
*/

    public Dimension getPreferredSize() { // sin esto no sale bien el tamanyo, averiguar por que se tiene que definir aqui
        return new Dimension(640,480);
    }

    public void paint(Graphics g) { 
        paintAnimation(g);
    }
    
    public void paintAnimation(Graphics g) { 
        System.out.println("paintAnimation!");
        g.setColor(Color.black);
        g.fillRect(0,0,w,h);
        g.setColor(Color.yellow);
        g.setColor(Color.green);
        g.drawOval(100, 100, 75, 50); 
    }

    /*
    public void run(){
        System.out.println("hilo runeando: llamando a paint del JPanel!");   
    }
    */
}


class MiHilo extends Thread{
    int cont=0;
    private boolean continuar = true;

    public MiHilo(){

    }

    public void detenerHilo(){
        this.continuar=false;
        System.out.println("--> hilo stop!");
    }

    public void activarHilo(){
        continuar=true;
        System.out.println("--> hilo empieza!");
    }

    public void run(){
        while (continuar){

            cont++;
            System.out.println("hilo runeando: llamando a paint del JPanel! "+cont);
            if (cont > 10000000) cont = 0;

            //repaint();
        }
    }
}




