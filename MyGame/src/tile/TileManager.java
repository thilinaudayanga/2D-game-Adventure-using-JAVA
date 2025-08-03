package tile;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.*;

public class TileManager 
{
	GamePanel gp;
	Tile[] tile;
	int mapTileNum[][];

	public TileManager(GamePanel gp)
	{
		
		this.gp = gp;
		tile = new Tile[10];
		
		mapTileNum = new int[gp.maxScreenRow][gp.maxScreenCol];
		
		
		getTileImage();
		loadMap();
		

	}
	
	
	//getting image inside the code
	public void getTileImage()
	{
		try
		{
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResource("/tiles/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResource("/tiles/wall.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResource("/tiles/water.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void loadMap()
	{
		try
		{
			//input map TXT file
			InputStream is = getClass().getResourceAsStream("/maps/map01.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxScreenCol && row < gp.maxScreenRow)
			{
				String line = br.readLine();
				
				
				String number[] = line.split(" ");
				while(col < gp.maxScreenCol)
				{
					
					
					int num = Integer.parseInt(number[col]);
					
					mapTileNum[row][col] = num;
					col++;
					
					if(col == gp.maxScreenCol)
					{
						col = 0;
						row++;
					}
					
				}
				col++;
				
			}
			br.close();
			
			
		}
		catch(Exception e)
		{
			
		}
		
		//test text file
		for(int i = 0; i < 12;i++)
		{
			for(int j = 0;j<16;j++)
			{
				System.out.print(mapTileNum[i][j]);
			}
			System.out.println();
		}
	}
	
	
	public void Draw(Graphics2D g2)
	{
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gp.maxScreenCol && row < gp.maxScreenRow)
		{
			
			int tilenum = mapTileNum[row][col];
			
			g2.drawImage(tile[tilenum].image, x, y, gp.TileSize, gp.TileSize,null);
			col++;
			x += gp.TileSize;
			
			if(col == gp.maxScreenCol)
			{
				col = 0;
				row++;
				x = 0;
				y += gp.TileSize;
			}
			
		}
		
	}
	
		
}
