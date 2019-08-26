package Panel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class Receiver extends Thread {
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
/*
	@Override
	public void run() {
		String message;
		try {
			while ((message = reader.readLine()) != null) {
				if(communicationPanel.textArea.getText().equals("")) {
					communicationPanel.textArea.append(message);
				}else {
					communicationPanel.textArea.append("\n" + message);
				}
			
			}
		} catch (SocketException se) {
			communicationPanel.textArea.append("伺服器已關閉\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
}