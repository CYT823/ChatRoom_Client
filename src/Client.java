import javax.swing.JFrame;

public class Client {

	public static void main(String[] args) {
		JFrame frame = new MainFrame();
		frame.setSize(400, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
