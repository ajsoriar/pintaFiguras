import javax.swing.*;

class SwingDemo {

	SwingDemo (){

		//Creo contenedor JFrame.
		JFrame jfrm = new JFrame("Tope que flipas!");
		jfrm.setSize (275,100);
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel jlab = new JLabel ("Eeeeep vaaamoos");
		jfrm.add(jlab);

		jfrm.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new SwingDemo();
			}
		});
	}
	
}