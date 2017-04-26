package com.adam.Minesweeper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderScreen extends JPanel {
	
	private BufferedImage happy;
	private BufferedImage sad;
	private BufferedImage glad;
	
	public RenderScreen(){
		try {                
	          happy = ImageIO.read(new File("src/main/resources/Happy.png"));
	          sad = ImageIO.read(new File("src/main/resources/Sad.png"));
	          glad = ImageIO.read(new File("src/main/resources/Glad.png"));
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Minesweeper sweeper = Minesweeper.minesweeper;
		Font fnt = new Font("Arial", 1, 20);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, sweeper.xDim, sweeper.yDim);
		for(int y = 0; y < sweeper.getGridY(); y++){
			for(int x = 0; x < sweeper.getGridX(); x++){
				if(sweeper.uncovered[x][y] && sweeper.mines[x][y]){
					g.setColor(Color.RED);
					g.fillRect(x*30+10, y*30+60, 30, 30);
					g.setColor(Color.BLACK);
					g.drawRect(x*30+10, y*30+60, 30, 30);
				}else if(sweeper.uncovered[x][y] && !sweeper.mines[x][y]){
					g.setColor(new Color(11393254));
					g.fillRect(x*30+10, y*30+60, 30, 30);
					g.setColor(Color.BLACK);
					g.drawRect(x*30+10, y*30+60, 30, 30);
						g.setColor(Color.BLUE);
					if(sweeper.solution[x][y]!=0)
						g.drawString(String.valueOf(sweeper.solution[x][y]), x*30+11+10, y*30+20+60);
				} else if(sweeper.flags[x][y]) {
					g.setColor(Color.GRAY);
					g.fillRect(x*30+10, y*30+60, 30, 30);
					g.setColor(Color.BLACK);
					g.drawRect(x*30+10, y*30+60, 30, 30);
					g.setColor(Color.RED);
						g.drawString("F", x*30+11+10, y*30+20+60);
				} else {
					g.setColor(Color.GRAY);
					g.fillRect(x*30+10, y*30+60, 30, 30);
					g.setColor(Color.BLACK);
					g.drawRect(x*30+10, y*30+60, 30, 30);
				}
			}
		}
		g.setFont(fnt);
		g.setColor(Color.WHITE);
		g.fillRect(10, 10, 100, 30);
		g.setColor(Color.WHITE);
		g.fillRect(sweeper.xDim/2 - 20, 10, 30, 30);
		g.setColor(Color.RED);
		if(sweeper.gameState == STATE.Game){
			g.drawString("MINES: " + String.valueOf(sweeper.getNumberOfMines()-sweeper.getNumberOfFlags()), 10, 33);
			g.drawImage(happy, sweeper.xDim/2 - 20, 10, this);
		}
		else if(sweeper.gameState == STATE.Win){
			g.drawString("WINNER", 10, 33);
			g.drawImage(glad, sweeper.xDim/2 - 20, 10, this);
		}
		else if(sweeper.gameState == STATE.Loss){
			g.drawString("LOSER", 10, 33);
			g.drawImage(sad, sweeper.xDim/2 - 20, 10, this);
		}
		g.setColor(Color.WHITE);
		g.fillRect(sweeper.xDim-120, 10, 100, 30);
		g.setColor(Color.RED);
		if(sweeper.seconds<10){
			g.drawString(String.valueOf(sweeper.minutes) + ":0" + String.valueOf(sweeper.seconds), sweeper.xDim-120, 33);
		} else {
			g.drawString(String.valueOf(sweeper.minutes) + ":" + String.valueOf(sweeper.seconds), sweeper.xDim-120, 33);
		}
	}

}
