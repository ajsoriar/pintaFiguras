import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditorDeFiguras extends JFrame {

    public EditorDeFiguras() {

        super("Hola mundo con ventanas!");
        setBackground(Color.lightGray);
        setSize(800,600);
        setVisible(true);

        System.out.println("--> EditorDeFiguras!");

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                System.out.println("Resized!");
            }
        });

    }

    public void paint(Graphics g) {
        System.out.println("--> paint!");
        g.drawString("Pinta pinta",40,160);
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
        Punto p = new Punto();
        //String mix = p.getX() + ", "+p.getY() + ", "+p.getZ()+"";
        //System.out.print(mix);
    }

}
