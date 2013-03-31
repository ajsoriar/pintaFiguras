import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//public 
class RebotaBolas_4 {
	
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

    public int w, h, cont=0;
    Bola[] bolas;
    int[] limitesPanel;
    int miX = 100, miY = 100;
    private boolean continuar = true;

    public MyJPanel() {

        System.out.println("--> MyJPanel!");

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                System.out.println("Resized!");
                w = getWidth();
                h = getHeight();
                
                limitesPanel[0] = w;
                limitesPanel[1] = h;
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
                        miX-=5;
                        break;
                    case KeyEvent.VK_RIGHT:
                        System.out.print("Tecla DERECHA");
                        miX+=5;
                        break;
                    case KeyEvent.VK_UP:
                        System.out.print("Tecla IZQUIERDA");
                        miY-=5;
                        break;
                    case KeyEvent.VK_DOWN:
                        System.out.print("Tecla DERECHA");
                        miY+=5;
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
        bolas[0].teLoPaso(limitesPanel);
        /*
        bolas[1] = new Bola(100,100,5,50,60);
        bolas[1].teLoPaso(limitesPanel);
        bolas[2] = new Bola(300,300,20,1,180);
        bolas[2].teLoPaso(limitesPanel);
		*/
		
        HiloMueveTodo movimiento = new HiloMueveTodo();
        movimiento.teLoPaso(bolas);
        movimiento.start();    

        Thread hilo = new Thread(this); // Este JPanel implementa runeable!
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

        //Pinta fondo negro
        g.setColor(Color.black);
        g.fillRect(0,0,w,h);

        //Pinta ejes
        g.setColor(Color.red);
        g.drawLine(0, h/2, w, h/2);
        g.drawLine(w/2, 0, w/2, h);


        g.setColor(Color.green);
        g.drawOval(100, 100, 75, 50); 

        g.setColor(Color.yellow);
        for(int i = 0; i < bolas.length; i++){
            int cr = bolas[i].getRadio();
            int cx = bolas[i].getX();
            int cy = bolas[i].getY();
            g.drawOval(cx, cy, cr, cr); 
        }

        g.setColor(Color.red);
        g.drawOval(miX, miY, 5, 5); 
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

    public void teLoPaso(Bola[] bolas){
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
                Thread.sleep(100);
            }catch (InterruptedException ex){
                break;
            }

        }
    }

    public void calcula(){
        
        for(int i = 0; i < bolas.length; i++){
            //bolas[i].avanza(); 
        }

    }
}

/* ------------------------------------------------------------- */
class Bola{
    boolean enMovimiento = true;
    int x = 0, y = 0, radio= 10, velocidad= 1, angulo= 45;
	int[] limitesPanel;
    double realX = 0, realY =0;

    Bola(int x, int y, int radio, int velocidad, int angulo){

        this.x = x;
        this.y = y; 
        this.radio = radio; 
        this.velocidad = velocidad;
        this.angulo = angulo;

        this.realX = x; 
        this.realY = y;

        System.out.println("Nueva Bola creada!");
    }
    
    public void teLoPaso(int[] limitesPanel){
        this.limitesPanel = limitesPanel;
    }

    public void avanza(){

        int angE = this.angulo;
        int angS = 0;
        int bx; // = x + this.radio;
        int by; // = y + this.radio;
        int w = limitesPanel[0];
        int h = limitesPanel[1];

        if (enMovimiento){

            // Desplazamiento de la bola
            

            // Ver si hay un cambio de direccion por choque contra las paredes.
            if (angE >= 0 || angE <90){ // CUADRANTE 1
                if(bx < w) { angS = 90 - angE; } // Caso A
                if(by < h) { angS = 270 + (90 - angE); } // Caso B
            }

            if (angE >= 90 || angE <180){ // CUADRANTE 2
                if(by < h) { angS = 180 + (angE - 90); } // Caso C
                if(bx < 0) { angS = angE - 90; } // Caso D
            }

            if (angE >= 180 || angE <270){ // CUADRANTE 3
                if(bx < 0) { } // Caso E
                if(by < 0) { } // Caso F
            }

            if (angE >= 270 || angE <380){ // CUADRANTE 4
                if(by < 0) { } // Caso G
                if(bx > w) { } // Caso H
            }

        }

        this.angulo = angS;
        this.x = (int) realX; 
        this.y = (int) realY;
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

