package snekpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Snek extends JFrame {
	// JPanel
	JPanel pnlButton = new JPanel();
	// Buttons
	JButton btnStart = new JButton("Start");

	public Snek() {

		pnlButton.setBackground(Color.black);
		setFocusable(true);
		setPreferredSize(new Dimension(300, 300));
		setResizable(false);
		pack();

		pnlButton.add(btnStart, BorderLayout.CENTER);
		add(pnlButton);

		setTitle("Snek Simulator");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				JFrame ex = new Snek(new SnekBoard());
				ex.setVisible(true);
			}
		});
	}

	public Snek(SnekBoard sb) {
		add(sb);

		setResizable(false);
		pack();

		setTitle("Snek Simulator");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame ex = new Snek();
				ex.setVisible(true);
			}
		});
	}

}