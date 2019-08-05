package Panel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receiver extends Thread{
	Socket client;
	CommunicationPanel communicationPanel;
	BufferedReader reader;
	
	public Receiver(Socket client, CommunicationPanel communicationPanel) {
		this.client = client;
		this.communicationPanel = communicationPanel;
		
		try {
			reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		String message;
		try {
			while ((message = reader.readLine()) != null) {
				communicationPanel.textArea.append(message + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
