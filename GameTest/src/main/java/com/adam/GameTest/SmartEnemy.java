package com.adam.GameTest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject {
	
	private Handler handler;
	private GameObject player;

	public SmartEnemy(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		for(int i=0; i<handler.objectList.size();i++){
			if(handler.objectList.get(i).getId()==ID.Player) player = handler.objectList.get(i);
		}

	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int) y, 16, 16);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		
		float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX())+(y-player.getY())*(y-player.getY()));
		
		velX = (float) ((-1.0/distance)*diffX);
		velY = (float) ((-1.0/distance)*diffY);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.05f, handler));

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, 16, 16);
	}

}
