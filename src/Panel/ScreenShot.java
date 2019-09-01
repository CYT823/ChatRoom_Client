package Panel;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ScreenShot extends JFrame {
	ScreenShot screenShot = this;

	static int startX;
	static int startY;
	static int width;
	static int height;

	JButton okBtn;
	JButton cancelBtn;
	JPanel captureArea;

	ScreenShot() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBackground(new Color(0.5f, 0.5f, 0.5f, 0.1f));
		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		setLayout(null);
		this.setVisible(true);

		okBtn = new JButton(new ImageIcon("icon/checkMark.png"));
		cancelBtn = new JButton(new ImageIcon("icon/crossMark.png"));
		captureArea = new JPanel();
		captureArea.setBackground(new Color(0.0f, 0.5f, 1.0f, 0.05f));
		captureArea.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
		initial();
	}

	void initial() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				startX = e.getX();
				startY = e.getY();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				width = Math.abs(e.getX() - startX);
				height = Math.abs(e.getY() - startY);
				
				if (startX == e.getX() || startY == e.getY()) {
					screenShot.getContentPane().removeAll();
					screenShot.repaint();
					return;
				}
				
				// 判斷左上角位置
				if (startX > e.getX())
					startX = e.getX();

				if (startY > e.getY())
					startY = e.getY();

				captureArea.setBounds(startX, startY, width, height);
				screenShot.add(captureArea);

				okBtn.setBounds(startX + width - 100, startY + height - 30, 50, 30);
				screenShot.add(okBtn);

				cancelBtn.setBounds(startX + width - 50, startY + height - 30, 50, 30);
				screenShot.add(cancelBtn);

				screenShot.repaint();
			}
		});

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
					screenShot.dispose();
			}
		});
		
		// 經除畫面上元件後  執行截圖步驟
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				screenShot.setBackground(new Color(0.5f, 0.5f, 0.5f, 0f));
				screenShot.getContentPane().removeAll();
				screenShot.repaint();

				new Thread(new Runnable() {
					@Override
					public void run() {
						shot();
					}
				}).start();
			}
		});

		// 將畫面上元件移除
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				screenShot.getContentPane().removeAll();
				screenShot.repaint();
			}
		});
	}

	void shot() {
		try {
			Rectangle capture = new Rectangle(startX, startY, width, height);
			Robot r = new Robot();
			String path = "D://Shot.png";
			BufferedImage Image = r.createScreenCapture(capture);
			// 以後這邊透過FTP傳送至SERVER
			ImageIO.write(Image, "png", new File(path));
			System.out.println("Screenshot saved");
		} catch (AWTException | IOException ex) {
			ex.printStackTrace();
		}

		screenShot.dispose();
	}

	public static void main(String[] args) {
		new ScreenShot();
	}
}
