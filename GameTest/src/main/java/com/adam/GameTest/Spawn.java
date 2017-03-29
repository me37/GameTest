package com.adam.GameTest;

import java.util.Random;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	private int scoreKeep = 0;
	private Random random = new Random();
	
	public Spawn(Handler handler, HUD hud){
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick(){
		scoreKeep++;
		
		if(scoreKeep >= 200){
			scoreKeep = 0;
			hud.setLevel(hud.getLevel()+1);
			
			if(hud.getLevel()==2){
				handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			} else if (hud.getLevel()==3){
				handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			} else if (hud.getLevel()==4){
				handler.addObject(new FastEnemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
			} else if (hud.getLevel()==5){
				handler.addObject(new SmartEnemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
			}
		}
	}

}
