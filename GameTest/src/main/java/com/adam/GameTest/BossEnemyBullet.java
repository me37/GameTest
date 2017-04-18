package com.adam.GameTest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemyBullet extends GameObject {
	
	private Handler handler;
	Random random = new Random();

	public BossEnemyBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

		velX = random.nextInt(10)-5;
		velY = 5;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(y >= Game.HEIGHT)
			handler.removeObject(this);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.magenta, 16, 16, 0.05f, handler));

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.magenta);
		g.fillRect((int)x,(int) y, 16, 16);
	}

}
