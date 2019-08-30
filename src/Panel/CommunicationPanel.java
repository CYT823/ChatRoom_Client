package Panel;

import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class CommunicationPanel extends JPanel {
	JLabel name;
	JButton phoneBtn;
	JTextPane textPane;
	JTextArea typingBar;
	JButton plusBtn;
	JButton picBtn;
	JButton screenshotBtn;

	Socket client;
	PrintWriter writer;
	CommunicationPanel communicationPanel = this;

	public CommunicationPanel() {
		setLayout(null);

		name = new JLabel("(名字)");
		name.setBounds(30, 25, 300, 30);
		name.setFont(new java.awt.Font("新細明體", 1, 18));
		add(name);

		phoneBtn = new JButton();
		phoneBtn.setContentAreaFilled(false);
		phoneBtn.setBorderPainted(false);
		ImageIcon phone = new ImageIcon("icon/phone.png");
		phoneBtn.setIcon(phone);
		phoneBtn.setBounds(330, 25, 30, 30);
		add(phoneBtn);

		textPane = new JTextPane();
		textPane.setSize(340, 400);
		DefaultCaret caret = (DefaultCaret) textPane.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane txtPaneScroll = new JScrollPane(textPane);
		txtPaneScroll.setBounds(25, 70, 340, 400);
		add(txtPaneScroll);

		typingBar = new JTextArea();
		typingBar.setFont(new Font("新細明體", 0, 15));
		typingBar.setLineWrap(true);
		InputMap inputMap = typingBar.getInputMap(WHEN_FOCUSED);
		KeyStroke enterStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		inputMap.put(enterStroke, enterStroke.toString());
		JScrollPane typBarScroll = new JScrollPane(typingBar);
		typBarScroll.setBounds(120, 480, 240, 30);
		add(typBarScroll);

		plusBtn = new JButton();
		plusBtn.setContentAreaFilled(false);
		plusBtn.setBorderPainted(false);
		ImageIcon plus = new ImageIcon("icon/plus.png");
		plusBtn.setIcon(plus);
		plusBtn.setBounds(20, 480, 30, 30);
		add(plusBtn);

		picBtn = new JButton();
		picBtn.setContentAreaFilled(false);
		picBtn.setBorderPainted(false);
		ImageIcon pic = new ImageIcon("icon/picture.png");
		picBtn.setIcon(pic);
		picBtn.setBounds(52, 480, 30, 30);
		add(picBtn);

		screenshotBtn = new JButton();
		screenshotBtn.setContentAreaFilled(false);
		screenshotBtn.setBorderPainted(false);
		ImageIcon screenshot = new ImageIcon("icon/screenshot.png");
		screenshotBtn.setIcon(screenshot);
		screenshotBtn.setBounds(84, 480, 30, 30);
		add(screenshotBtn);

		initial();
	}

	private void initial() {
		client = new Socket();
		try {
			client.connect(new InetSocketAddress("140.123.101.67", 30000));
			writer = new PrintWriter(client.getOutputStream());
			new Receiver(client, communicationPanel).start();
		} catch (IOException e) {
			e.printStackTrace();
		}

		SimpleAttributeSet attr = new SimpleAttributeSet();
		StyleConstants.setForeground(attr, Color.BLACK);
		StyleConstants.setAlignment(attr, StyleConstants.ALIGN_RIGHT);
		StyleConstants.setFontFamily(attr, "新細明體");
		StyleConstants.setFontSize(attr, 15);

		final Set<Integer> pressed = new HashSet<Integer>();
		typingBar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				pressed.add(e.getKeyCode());

				if (pressed.contains(KeyEvent.VK_ENTER) && pressed.contains(KeyEvent.VK_SHIFT)) {
					typingBar.append("\n");
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String msg = "";
					StyledDocument doc = textPane.getStyledDocument();

					try {
						msg += typingBar.getText() + "\n";
						textPane.setParagraphAttributes(attr, true);
						doc.insertString(doc.getLength(), msg, attr);
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}

					// send through socket
					writer.println(typingBar.getText());
					writer.flush();

					// clear the typing area
					typingBar.setText("");
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				pressed.remove(e.getKeyCode());
			}
		});

	}

}