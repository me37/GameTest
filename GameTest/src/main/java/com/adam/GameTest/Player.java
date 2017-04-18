package com.adam.GameTest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {

	Random random = new Random();
	Handler handler;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, 32, 32);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		x = Game.clamp(x, 0, Game.WIDTH - 36);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);
		
		collision();

	}
	
	private void collision(){
		for(int i = 0; i< handler.objectList.size();i++){
			GameObject tempObject = handler.objectList.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId()==ID.SmartEnemy){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.HEALTH -= 2;
				}
			} else if(tempObject.getId() == ID.BossEnemy ){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.HEALTH = 0;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x,(int) y, 32, 32);
	}

}
