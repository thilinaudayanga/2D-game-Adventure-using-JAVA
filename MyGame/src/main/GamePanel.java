package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

//Entity package
import entity.*;

public class GamePanel extends JPanel implements Runnable
{
	//set screen setting 
	final int originalTileSize = 16; //16x16
	final int scale = 3;
	
	public final int TileSize = originalTileSize*scale;    // 48x48
	//set maximum col and row
	
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	
	//set Full Screen Size
	final int ScreenWidth = maxScreenCol*TileSize;
	final int ScreenHeight = maxScreenRow*TileSize;
	
	//FPS
	int FPS = 60;
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	
	//player class
	Player player = new Player(this,keyH);
	
	//set players default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 2;
	
	//create constructor
	public GamePanel()
	{
		this.setPreferredSize(new Dimension(ScreenWidth,ScreenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		
		//Add key handler
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}
	
	
	
	
	public void startGameThread()
	{
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	//*********************************************************
	public void run()
	{
		//set FPS Screen
		double drawInterval = 1000000000/FPS; //0.016666 second
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null)
		{
			
			//2 thing want to do in game 1- Update : update information such as character position
			update();
			
			//2 - Draw : raw the screen with the update information
			repaint();
			
			
			try 
			{
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0)
				{
					remainingTime = 0;
				}
					
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	//*****************************************************************
	public void update()
	{
		player.update();
	}
	
	
	//*****************************************************************
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		player.draw(g2);
		
		g2.dispose();
		
		
	}
	
	
	
	
}





























