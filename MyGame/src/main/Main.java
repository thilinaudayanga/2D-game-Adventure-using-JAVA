package main;

import javax.swing.JFrame;

public class Main 
{

	public static void main(String[] args) 
	{
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("2D game beggin");
		
		//***********************************************************
		GamePanel gamepanel = new GamePanel();  // object that create 
		window.add(gamepanel);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamepanel.startGameThread();
	}

}
