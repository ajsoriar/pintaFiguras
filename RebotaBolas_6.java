import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

public class RebotaBolas_6 {
	
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
        bolas[0] = new Bola(200,200,10,3,45);
        bolas[1] = new Bola(100,100,5,5,80);
        bolas[2] = new Bola(100,300,20,1,180);

        HiloMueveTodo movimiento = new HiloMueveTodo();
        movimiento.teLoPaso(bolas,limitesPanel);
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
        g.drawRect(0, 0, 640, 480); 

        g.setColor(Color.yellow);
        
        for(int i = 0; i < bolas.length; i++){
            int cr = bolas[i].getRadio();
            int cx = bolas[i].getX();
            int cy = bolas[i].getY();
            g.drawOval(cx, cy, cr, cr);
            //System.out.println("i:"+i+"; bolas.length"+bolas.length+";");
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
    int dimensiones[];

    public void teLoPaso(Bola[] bolas, int[] dimensiones){
        this.bolas = bolas;
        this.dimensiones = dimensiones;
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
                Thread.sleep(10);
            }catch (InterruptedException ex){
                break;
            }

        }
    }

    public void calcula(){

        int w = 640; //this.dimensiones[0];
        int h = 480; //this.dimensiones[1];
        
        for (int i = 0; i < bolas.length; i++){

            bolas[i].avanza();
            int angE = bolas[i].getAngulo();
            int angS = angE;
            int r = bolas[i].getRadio();

            if ( bolas[i].getX() < 0){

                if (angE <= 270 && angE >= 90){
                    if (angE <= 180){
                        //angS = angE - 90;
                        angS = angE - 90;
                    }else{
                        angS = angE + 90;
                    }
                }

            }

            if ( bolas[i].getX()+r > w){

                if (angE <= 90){
                    angS = angE + 90;
                }

                if (angE >= 270){
                    angS = angE - 90;
                }
                 
            }

            if ( bolas[i].getY() < 0){  

                if (angE <= 360 && angE >= 180){
                    if (angE >= 270){
                        angS = angE - 270;
                    }else{
                        angS = angE - 90;
                    }
                }

            }

            if ( bolas[i].getY()+r > h){

                if (angE <= 180 && angE >= 0){
                    if (angE < 90){
                        angS = angE + 270;
                    }else{
                        angS = angE + 90;
                    }
                }

            }

            bolas[i].setAngulo(angS);
            bolas[i].calculaDesplazamiento();
            System.out.println("x:"+bolas[i].getX() + " y:"+bolas[i].getY() + " ang:"+bolas[i].getAngulo());
        }

    }
}

/* ------------------------------------------------------------- */
class Bola{
    boolean enMovimiento = true;
    int radio= 10, velocidad= 1, angulo= 45;
	int[] limitesPanel;
    double x = 0, y = 0, desplazamientoX, desplazamientoY;

    Bola(int x, int y, int radio, int velocidad, int angulo){
        this.x = x;
        this.y = y; 
        this.radio = radio; 
        this.velocidad = velocidad;
        this.angulo = angulo;
        calculaDesplazamiento();
        System.out.println("Nueva Bola creada!");
    }

    public void calculaDesplazamiento(){
        double anguloRadianes = Math.toRadians(this.angulo);
        this.desplazamientoX = Math.cos(anguloRadianes)*this.velocidad;
        this.desplazamientoY = Math.sin(anguloRadianes)*this.velocidad;
    }

    public void avanza(){
        if (enMovimiento){
            this.x = this.x + desplazamientoX;
            this.y = this.y + desplazamientoY;
        }
    }

    public void paraMovimiento(){
        this.enMovimiento = false;
    }

    public void reanudaMovimiento(){
        this.enMovimiento = true;
    }

    public int getX(){
        return (int) this.x;
    }

    public int getY(){
        return (int) this.y;
    }

    public int getRadio(){
        return this.radio;
    }

    public int getAngulo(){
        return this.angulo;
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

    public void setAngulo(int angulo){
        this.angulo = angulo;
    }
}

