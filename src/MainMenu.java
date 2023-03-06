import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class MainMenu {
	JPanel panel;
	private JFrame frame;
	static final String background = "images/bg.png";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	class ImagePanel extends JPanel {
	    private Image image;
	    public ImagePanel(ImageIcon image) {
	        this.image = image.getImage();
	    }
	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, this);
	    }
	}

	public MainMenu() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 375, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		 panel = new ImagePanel(new ImageIcon(background));
		panel.setBounds(10, 11, 339, 417);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Level :");
		lblNewLabel.setBounds(85, 37, 90, 14);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(Color.white);
		SpinnerNumberModel model1 = new SpinnerNumberModel(1, 1, 3, 1);  
		JSpinner spinner = new JSpinner(model1);
		spinner.setBounds(201, 34, 46, 20);
		panel.add(spinner);
		
		spinner.setValue(1);
		
		JButton btnNewButton = new JButton("New Game");
		btnNewButton.setBounds(85, 60, 162, 45);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int level = Integer.valueOf(spinner.getValue().toString());
					level = level<=0?1:level;
					level = level>=3?3:level;
					GameWindow.lives=3;
					GameWindow window = new GameWindow(level);
					window.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		JButton btnNewButton_1 = new JButton("Options");
		btnNewButton_1.setBounds(85, 116, 162, 45);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		JButton btnNewButton_2 = new JButton("Scores");
		btnNewButton_2.setBounds(85, 172, 162, 45);
		panel.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Scores window = new Scores();
					window.frame.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		JButton btnNewButton_3 = new JButton("Help");
		btnNewButton_3.setBounds(85, 228, 162, 45);
		panel.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Welcome to block breaker! You can use left&right arrow keys to move the pad! You should break all blocks to proceed next level!");
			}
		});
		JButton btnNewButton_4 = new JButton("About");
		btnNewButton_4.setBounds(85, 284, 162, 45);
		panel.add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Name, Surname, School, Email");
			}
		});
		JButton btnNewButton_5 = new JButton("Exit");
		btnNewButton_5.setBounds(85, 340, 162, 45);
		panel.add(btnNewButton_5);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}
}
