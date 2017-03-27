package com.adam.GameTest;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent event){
		
		int key = event.getKeyCode();
		
		for(int i=0; i <handler.objectList.size(); i++){
			GameObject tempObject = handler.objectList.get(i);
			
			if(tempObject.getId()==ID.Player){
				if(key==KeyEvent.VK_W)
					tempObject.setVelY(-5);
				if(key==KeyEvent.VK_A)
					tempObject.setVelX(-5);
				if(key==KeyEvent.VK_S)
					tempObject.setVelY(5);
				if(key==KeyEvent.VK_D)
					tempObject.setVelX(5);
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
					tempObject.setVelY(0);
				if(key==KeyEvent.VK_A)
					tempObject.setVelX(0);
				if(key==KeyEvent.VK_S)
					tempObject.setVelY(0);
				if(key==KeyEvent.VK_D)
					tempObject.setVelX(0);
			}
		
		}
		
	}

}
