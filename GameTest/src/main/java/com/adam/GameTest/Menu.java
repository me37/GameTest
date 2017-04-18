package com.adam.GameTest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.adam.GameTest.Game.STATE;

public class Menu extends MouseAdapter {

	private Game game;
	private Handler handler;
	private Random random = new Random();

	Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}

	public void mousePressed(MouseEvent event) {
		int mx = event.getX();
		int my = event.getY();
		if (game.gameState == STATE.Menu) {
			// Play Button
			if (mouseOver(mx, my, 210, 150, 200, 64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
				handler.clearEnemies();
				handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
						ID.BasicEnemy, handler));
			}

			// Help Button
			if (mouseOver(mx, my, 210, 250, 200, 64)) {
				game.gameState = STATE.Help;
			}

			// Quit button
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				System.exit(1);
			}
		} else if (game.gameState == STATE.Help) {
			if (mouseOver(mx, my, 210, 350, 200, 64)) {
				game.gameState = STATE.Menu;
			}
		}

	}

	public void mouseReleased(MouseEvent event) {

	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width && my > y && my < y + height)
			return true;
		else
			return false;
	}

	public void tick() {

	}

	public void render(Graphics g) {
		if (game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Menu", 245, 50);
			g.setFont(fnt2);

			g.setColor(Color.BLACK);
			g.fillRect(210, 150, 200, 64);
			g.setColor(Color.WHITE);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 275, 190);

			g.setColor(Color.BLACK);
			g.fillRect(210, 250, 200, 64);
			g.setColor(Color.WHITE);
			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 275, 290);

			g.setColor(Color.BLACK);
			g.fillRect(210, 350, 200, 64);
			g.setColor(Color.WHITE);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 275, 390);
			
			
			
		} else if (game.gameState == STATE.Help) {
			
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Help", 245, 50);
			
			g.setFont(fnt3);
			g.drawString("Use the WASD keys to avoid the blocks", 120, 200);
			
			
			g.setFont(fnt2);
			g.setColor(Color.BLACK);
			g.fillRect(210, 350, 200, 64);
			g.setColor(Color.WHITE);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 220, 390);
		}
	}

}
