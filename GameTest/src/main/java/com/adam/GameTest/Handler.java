package com.adam.GameTest;

import java.awt.Graphics;
import java.util.LinkedList;

import com.adam.GameTest.Game.STATE;

public class Handler {

	LinkedList<GameObject> objectList = new LinkedList<GameObject>();
	Game game;

	Handler(Game game) {
		this.game = game;
	}

	public void tick() {
		for (int i = 0; i < objectList.size(); i++) {
			GameObject tempObject = objectList.get(i);

			tempObject.tick();
		}
	}

	public void render(Graphics g) {
		if (objectList.size() != 0) {
			for (int i = 0; i < objectList.size(); i++) {
				GameObject tempObject = objectList.get(i);

				tempObject.render(g);
			}
		}
	}

	public void addObject(GameObject object) {
		objectList.add(object);

	}

	public void removeObject(GameObject object) {
		objectList.remove(object);
	}

	public void clearEnemies() {
		for (int i = 0; i < objectList.size(); i++) {
			GameObject tempObject = objectList.get(i);

			if (tempObject.getId() == ID.Player) {
				objectList.clear();
				if (game.gameState != Game.STATE.GameOver)
					addObject(new Player((int) tempObject.getX(), (int) tempObject.getY(), ID.Player, this));
			}
		}
	}

}
