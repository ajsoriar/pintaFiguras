import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OtraForma_2 {
   
    public static void main(String[] args) {
        System.out.println("--> main!");
        PintaPinta();
    }

    public OtraForma_2() {
        System.out.println("--> OtraForma_2");
    }

    private static void PintaPinta() {
        System.out.println("--> PintaPinta!");

        JFrame f = new JFrame("Swing Paint Demo");
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

    public MyPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                System.out.println("Resized!");
                //CalculaUserInterface();
            }
        });

        this.addMouseListener(new MouseAdapter() { 
            public void mousePressed(MouseEvent me) { 
                System.out.println(me); 
                System.out.println( "x:"+ me.getX() +"y:"+ me.getY() ); 
            } 
        }); 

        /* // No funciona la captura de teclas aqui y no se por que!

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
                }
            }
        }); 
        */

    }

    public Dimension getPreferredSize() {
        return new Dimension(640,480);
    }

    /* //No hace nada ¿?
    public void paintComponent(Graphics g) {
        System.out.println("--> paintComponent!");
        //super.paintComponent(g);       
        g.drawString("Pinta texto que mola!",10,20);
    } 
    */

    public void paint(Graphics g) {
        System.out.println("--> paint!");

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