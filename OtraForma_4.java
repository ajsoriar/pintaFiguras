import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OtraForma_4 {

    public static void main(String[] args) {
        System.out.println("--> main!");
        CreaFrame();
    }

    public OtraForma_4() {
        System.out.println("--> OtraForma_3");
    }

    private static void CreaFrame() {
        System.out.println("--> CreaFrame!");

        JFrame f = new JFrame("Pinta pinta forever");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(640,480);
        
        f.add(new MyPanel());
        f.pack(); // No se pa que vale.
        f.setVisible(true);
    }
}

// Internet, fuente de toda sabiduria dice que 
// pintar en un JFrame es malo y que incruste
// un JPanel y que pinte en el.

class MyPanel extends JPanel {

    int w, h, cont=0;
    MiHilo elHilo;

    public MyPanel() {

        System.out.println("--> MyPanel!");

        setBorder(BorderFactory.createLineBorder(Color.black));

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                System.out.println("Resized!");
                //Tamanyo(e.getWidth(),e.getHeight()); // TODO averiguar como sacar el tamanyo
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

    public void Tamanyo(int w, int h) {
        System.out.println("--> tamanyo!");
        this.w = w;
        this.h = h;
        System.out.println("MyPanel --> ancho:"+ this.w+" alto:"+ this.h);
    }

    public Dimension getPreferredSize() { // sin esto no sale bien el tamanyo, averiguar por que se tiene que definir aqui
        return new Dimension(640,480);
    }

    public void paint(Graphics g) {
        cont++;
        System.out.println("--> paint! "+cont);

        g.setColor(Color.black);
        g.fillRect(0,0,100, 100);

        g.setColor(Color.yellow);
        g.drawString("Pinta pinta",40,160);

        g.setColor(Color.green);
        g.drawOval(100, 100, 75, 50);
        
        g.setColor(Color.red);
        g.drawLine(0,0,300, 300);
    }
}

class MiHilo extends Thread{

    private boolean continuar = true;

    public void detenerHilo(){
        this.continuar=false;
        System.out.println("--> hilo stop!");
    }

    public void activarHilo(){
        continuar=true;
        System.out.println("--> hilo stop!");
    }

    public void run(){
        System.out.println("--> hilo funcionando!");
        while (continuar){
            System.out.println("hilo run run");
        }
    }
}



