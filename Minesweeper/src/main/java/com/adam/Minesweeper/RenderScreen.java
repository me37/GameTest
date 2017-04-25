package com.adam.Minesweeper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderScreen extends JPanel {
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Minesweeper sweeper = Minesweeper.minesweeper;
		Font fnt = new Font("Arial", 1, 20);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 300, 400);
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
		g.setColor(Color.RED);
		if(sweeper.gameState == STATE.Game)
			g.drawString("MINES: " + String.valueOf(sweeper.getNumberOfMines()-sweeper.getNumberOfFlags()), 10, 33);
		else if(sweeper.gameState == STATE.Win)
			g.drawString("WINNER", 10, 33);
		else if(sweeper.gameState == STATE.Loss)
			g.drawString("LOSER", 10, 33);
		g.setColor(Color.WHITE);
		g.fillRect(180, 10, 100, 30);
		g.setColor(Color.RED);
		if(sweeper.seconds<10){
			g.drawString(String.valueOf(sweeper.minutes) + ":0" + String.valueOf(sweeper.seconds), 180, 33);
		} else {
			g.drawString(String.valueOf(sweeper.minutes) + ":" + String.valueOf(sweeper.seconds), 180, 33);
		}
		g.setColor(Color.WHITE);
		g.fillRect(130, 10, 30, 30);
	}

}
