import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditorDeFiguras extends JFrame {

    public EditorDeFiguras() {
        super("¡Hola mundo con ventanas!");
        setBackground(Color.lightGray);
        setSize(800,600);
        setVisible(true);
    }

    public void paint(Graphics g) {
        g.drawString("Pinta pinta",40,160);
    }

    public static void main(String args[]) {
        
        EditorDeFiguras mf = new EditorDeFiguras();

        mf.addWindowListener( new WindowAdapter() {

            public void windowClosing( WindowEvent evt ){
                System.exit( 0 );
            }
        });

    }

}