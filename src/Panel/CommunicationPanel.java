package Panel;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CommunicationPanel extends JPanel{
	JTextField typingBar ;
	JButton enterBtn;
	JButton micBtn; 
	JButton picBtn;
	JTextArea textArea;
	
	public CommunicationPanel(){
		setLayout(null);
		
		typingBar = new JTextField();
		typingBar.setBounds(5, 480, 300, 30);
		add(typingBar);
		
		enterBtn =new JButton("enter");
		enterBtn.setBounds(310,480,70,30);
		add(enterBtn);
		
		micBtn = new JButton("record");
		micBtn.setBounds(10,520,80,25);
		add(micBtn);
		
		picBtn = new JButton("picture");
		picBtn.setBounds(100,520,80,25);
		add(picBtn);
		
	}
}
