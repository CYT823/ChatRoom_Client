package Panel;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Receiver extends Thread {
	Socket client;
	CommunicationPanel communicationPanel;
	BufferedReader reader;
	SimpleAttributeSet attr;

	public Receiver(Socket client, CommunicationPanel communicationPanel) {
		this.client = client;
		this.communicationPanel = communicationPanel;

		try {
			reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		attr = new SimpleAttributeSet();
		StyleConstants.setForeground(attr, Color.BLUE);
		StyleConstants.setAlignment(attr, StyleConstants.ALIGN_LEFT);
		StyleConstants.setFontFamily(attr, "新細明體");
		StyleConstants.setFontSize(attr, 15);
	}

	@Override
	public void run() {
		String message;
		StyledDocument doc;
		try {
			while ((message = reader.readLine()) != null) {
				doc = communicationPanel.textPane.getStyledDocument();
				communicationPanel.textPane.setParagraphAttributes(attr, true);
				
				try {
					doc.insertString(doc.getLength(),   message+"\n", attr);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}

			}
		} catch (SocketException se) {
			doc = communicationPanel.textPane.getStyledDocument();
			try {
				doc.insertString(doc.getLength(), "伺服器已關閉\n", attr);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}