import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditorDeFiguras extends JFrame {

    public EditorDeFiguras() {

        super("Hola mundo con ventanas!");
        setBackground(Color.lightGray);
        setSize(800,600);
        setVisible(true);

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                System.out.println("Resized!");
            }
        });

    }

    public void paint(Graphics g) {
        g.drawString("Pinta pinta",40,160);
    }

    public static void main(String args[]) {
        
        EditorDeFiguras mf = new EditorDeFiguras();

        mf.addWindowListener( new WindowAdapter() {

            public void windowClosing( WindowEvent evt ){
                System.out.print("cerrar!");
                System.exit( 0 );
            }

            public void windowOpened(WindowEvent e) {
                System.out.print("abrir!");
            }

        });

    }

}