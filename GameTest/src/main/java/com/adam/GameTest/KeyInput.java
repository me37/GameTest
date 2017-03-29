package com.adam.GameTest;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	
	public KeyInput(Handler handler){
		this.handler = handler;
		for(int i=0;i<4;i++){
			keyDown[i]=false;
		}
		
	}
	
	public void keyPressed(KeyEvent event){
		
		int key = event.getKeyCode();
		
		for(int i=0; i <handler.objectList.size(); i++){
			GameObject tempObject = handler.objectList.get(i);
			
			if(tempObject.getId()==ID.Player){
				if(key==KeyEvent.VK_W){
					tempObject.setVelY(-5); keyDown[0]=true;
				}
				if(key==KeyEvent.VK_A){
					tempObject.setVelX(-5); keyDown[1]=true;
				}
				if(key==KeyEvent.VK_S){
					tempObject.setVelY(5); keyDown[2]=true;
				}
				if(key==KeyEvent.VK_D){
					tempObject.setVelX(5); keyDown[3]=true;
				}
			}
			
		}
		
		if(key==KeyEvent.VK_ESCAPE)
			System.exit(1);
		
	}
	
	public void keyReleased(KeyEvent event){
		
		int key = event.getKeyCode();
		
		for(int i=0; i <handler.objectList.size(); i++){
			GameObject tempObject = handler.objectList.get(i);
			
			if(tempObject.getId()==ID.Player){
				if(key==KeyEvent.VK_W)
					keyDown[0]=false;
				if(key==KeyEvent.VK_A)
					keyDown[1]=false;
				if(key==KeyEvent.VK_S)
					keyDown[2]=false;
				if(key==KeyEvent.VK_D)
					keyDown[3]=false;
				
				if(!keyDown[0] && !keyDown[2])
					tempObject.setVelY(0);
				if(!keyDown[1] && !keyDown[3])
					tempObject.setVelX(0);
			}
		
		}
		
	}

}
