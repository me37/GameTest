package com.adam.Snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class RenderPanel extends JPanel {
	

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 600);
		Snake snake = Snake.snake;
		for(Point point : snake.snakeParts){
			g.setColor(Color.WHITE);
			g.fillRect(point.x * snake.SCALE, point.y * snake.SCALE, snake.SCALE, snake.SCALE);
			g.setColor(Color.GRAY);
			g.drawRect(point.x * snake.SCALE, point.y * snake.SCALE, snake.SCALE, snake.SCALE);
		}
		g.setColor(Color.gray);
		g.fillRect(snake.head.x * snake.SCALE, snake.head.y * snake.SCALE, snake.SCALE, snake.SCALE);
		g.setColor(Color.RED);
		Font fnt = new Font("arial",1,40);
		Font fnt2 = new Font("arial",1,10);
		g.setFont(fnt2);
		g.drawString("Score: "+snake.score, 5, 10);
		if(snake.over){
			g.setFont(fnt);
			g.drawString("GAME OVER", 25, 100);
		} else {
			g.fillRect(snake.cherry.x * snake.SCALE, snake.cherry.y * snake.SCALE, snake.SCALE, snake.SCALE);
		}
		
		if(snake.wrap){
			g.setFont(fnt2);
			g.drawString("Wrap: On", 245, 10);
		}else{
			g.setFont(fnt2);
			g.drawString("Wrap: Off", 245, 10);
		}
	}

}
