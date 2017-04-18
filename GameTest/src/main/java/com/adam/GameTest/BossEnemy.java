package com.adam.GameTest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BossEnemy extends GameObject {

	private Handler handler;
	int timer = 60;
	int timer2 = 40;
	Random random = new Random();

	public BossEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

		velX = 0;
		velY = 2;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 96, 96);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;

		if (timer <= 0)
			velY = 0;
		else
			timer--;
		
		if(velX>0)
			velX += 0.005;
		else if (velX<0)
			velX -= 0.005;
		
		velX = Game.clamp(velX, -10, 10);

		if (timer <= 0 && timer2 > 0)
			timer2--;
		if (timer2 <= 0) {
			if (velX == 0)
				velX = 2;
			int spawn = random.nextInt(10);
			if (spawn == 0)
				handler.addObject(new BossEnemyBullet((int) x+48, (int) y+48, ID.BasicEnemy, handler));
		}

		if (x <= 0 || x >= Game.WIDTH - 96)
			velX *= -1;

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, 96, 96);
	}

}
