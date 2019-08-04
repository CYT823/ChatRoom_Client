package Panel;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class CommunicationPanel extends JPanel{
	JTextField typingBar ;
	JButton enterBtn;
	JButton micBtn; 
	JButton picBtn;
	JButton phoneBtn;
	JTextArea textArea;
	
	public CommunicationPanel(){
		setLayout(null);
		
		typingBar = new JTextField();
		typingBar.setBounds(10, 480, 300, 30);
		add(typingBar);
		
		enterBtn =new JButton("發送");
		enterBtn.setBounds(310,480,70,30);
		add(enterBtn); 
		
		micBtn = new JButton("錄音");
		micBtn.setBounds(10,520,80,25);
		add(micBtn);
		
		picBtn = new JButton("照片");
		picBtn.setBounds(110,520,80,25);
		add(picBtn);
		
		phoneBtn = new JButton("通話");
		phoneBtn.setBounds(205,520,80,25);
		add(phoneBtn);
		
		textArea = new JTextArea(100,10);
		JScrollPane scrollBar = new JScrollPane(textArea);
		scrollBar.setBounds(50,50,300,400);
		//add(textArea);
		add(scrollBar);
		
		
		
	}
}
