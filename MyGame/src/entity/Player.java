package entity;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.*;

public class Player extends Entity
{
	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH)
	{
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	//default value function
	public void setDefaultValues()
	{
		x = 100;
		y = 100;
		speed = 3;
		direction = "down";
	}
	//player Image function
	public void getPlayerImage()
	{
		try
		{
			//assign image BuffreredImage Variable
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void update()
	{
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true)
		{
			//player press key handler
			if(keyH.upPressed == true)
			{
				direction = "up";
				y -= speed;
			}
			else if(keyH.downPressed == true)
			{
				direction = "down";
				y += speed;
			}
			else if(keyH.leftPressed == true)
			{
				direction = "left";
				x -= speed;
			}
				else if(keyH.rightPressed== true)
			{
				direction = "right";
				x += speed;
			}
			//change image using counter
			spriteCounter++;
			
			if(spriteCounter > 5)
			{
				if(spriteNum == 1)
				{
					spriteNum = 2;
				}
				else if(spriteNum == 2)
				{
					spriteNum = 1;
				}
				spriteCounter = 0;
					
			}
			
		}
		
		
		
	}
	
	public void draw(Graphics2D g2)
	{
		//create white rectangle
		//g2.setColor(Color.white);
		//g2.fillRect(x, y,gp.TileSize , gp.TileSize);
		
		BufferedImage Image = null;
		
		switch(direction)
		{
		case "up":
			if(spriteNum == 1)
				Image = up1;
			if(spriteNum == 2)
				Image = up2;
			break;
		case "down":
			if(spriteNum == 1)
				Image = down1;
			if(spriteNum == 2)
				Image = down2;
			break;
		case "left":
			if(spriteNum == 1)
				Image = left1;
			if(spriteNum == 2)
				Image = left2;
			break;
		case "right":
			if(spriteNum == 1)
				Image = right1;
			if(spriteNum == 2)
				Image = right2;
			break;
		}
		
		g2.drawImage(Image, x, y, gp.TileSize, gp.TileSize, null);
		
		
	}
}
