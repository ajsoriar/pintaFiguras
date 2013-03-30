import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RebotaBolas_2 {
	
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

/* ------------------------------------------------------------- */
class MyJPanel extends JPanel implements Runnable {

    int w, h, cont=0;
    Bola[] bolas;
    private boolean continuar = true;

    public MyJPanel() {

        System.out.println("--> MyJPanel!");

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
                        //elHilo.detenerHilo(); 
                        break;
                }
            }
        }); 

        bolas = new Bola[3];
        bolas[0] = new Bola(200,200,10,20,45);
        bolas[1] = new Bola(100,100,5,50,60);
        bolas[2] = new Bola(300,300,20,1,180);

        HiloMueveTodo movimiento = new HiloMueveTodo();
        movimiento.teLoPaso(bolas);
        movimiento.start();    

        Thread hilo = new Thread(this);
        hilo.start(); 
    }

    public Dimension getPreferredSize() { // sin esto no sale bien el tamanyo, averiguar por que se tiene que definir aqui
        return new Dimension(640,480);
    }

    public void paint(Graphics g) { 
        paintAnimation(g);
    }
    
    public void paintAnimation(Graphics g) {
        //System.out.println("paintAnimation!");
        g.setColor(Color.black);
        g.fillRect(0,0,w,h);

        g.setColor(Color.green);
        g.drawOval(100, 100, 75, 50); 

        g.setColor(Color.yellow);
        for(int i = 0; i < bolas.length; i++){
            int cr = bolas[i].getRadio();
            int cx = bolas[i].getX();
            int cy = bolas[i].getY();
            g.drawOval(cx, cy, cr, cr); 
        }
    }

    public void run(){
        while (continuar){
            repaint();
        }
    }

}

/* ------------------------------------------------------------- */
class HiloMueveTodo extends Thread{

    boolean continuar=true;
    int cont = 0;
    Bola bolas[];

    public void HiloMueveTodo(){  //int [] numeritos
    //public void HiloMueveTodo(Bola bolas){
        //this.bolas = bolas;
    }

    public void teLoPaso(Bola[] bolas){  //int [] numeritos
    //public void HiloMueveTodo(Bola bolas){
        this.bolas = bolas;
    }

    public void detenerHilo(){
        this.continuar=false;
        System.out.println("--> Hilo de movimiento para!");
    }

    public void activarHilo(){
        continuar=true;
        System.out.println("--> Hilo de movimiento se retoma!");
    }

    public void run(){

        while (continuar){

            cont++;
            System.out.println("Hilo de movimiento! "+cont);
            if (cont > 100) cont = 0;

            calcula();

            try{
                Thread.sleep(500);
            }catch (InterruptedException ex){
                break;
            }

        }
    }

    public void calcula(){
        
        for(int i = 0; i < bolas.length; i++){
            //bolas[i].avanza(); 
            bolas[i].setX( bolas[i].getX() +1);
        }

    }
}

/* ------------------------------------------------------------- */
class Bola{
    boolean enMovimiento = true;
    int x = 0, y = 0, radio= 10, velocidad= 1, angulo= 45;

    Bola(int x, int y, int radio, int velocidad, int angulo){
        this.x = x;
        this.y = y; 
        this.radio = radio; 
        this.velocidad = velocidad;
        this.angulo = angulo;
        System.out.println("Nueva Bola creada!");
    }

    public void avanza(){
        if (enMovimiento){
            System.out.println("Avanza!");
        }
    }


    public void paraMovimiento(){
        this.enMovimiento = false;
    }

    public void reanudaMovimiento(){
        this.enMovimiento = true;
    }


    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getRadio(){
        return this.radio;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setRadio(int radio){
        this.radio = radio;
    }
}

