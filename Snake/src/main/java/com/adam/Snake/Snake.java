package com.adam.Snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener {

	public JFrame frame;
	public Toolkit toolkit;
	public RenderPanel panel;
	public static Snake snake;
	public Timer timer = new Timer(8, this);

	public int ticks = 0;

	public final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10, INIT_LENGTH = 3;

	public int direction = DOWN;

	public int score, tailLength = INIT_LENGTH;

	public ArrayList<Point> snakeParts = new ArrayList<Point>();

	public Point head, cherry;

	public Random random;

	public boolean over = false, paused = false, wrap = false;

	public Dimension dim;
	
	public int xDim = 300, yDim = 300;

	public Snake() {
		toolkit = Toolkit.getDefaultToolkit();
		dim = toolkit.getScreenSize();
		panel = new RenderPanel();
		frame = new JFrame("Snake");
		frame.setVisible(true);
		frame.setSize(xDim, yDim);
		frame.setResizable(false);
		frame.setLocation(dim.width / 2 - frame.getWidth() / 2, dim.height / 2 - frame.getHeight() / 2);
		frame.add(panel);
		frame.addKeyListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		timer.start();
		head = new Point(0, 0);
		random = new Random();
		cherry = new Point(random.nextInt(frame.getWidth()/SCALE-3),random.nextInt(frame.getHeight()/SCALE-3));
		for (int i = 0; i < tailLength; i++) {
			snakeParts.add(new Point(head.x,head.y));
		}
			
	}

	public void actionPerformed(ActionEvent e) {

		panel.repaint();
		ticks++;

		if (ticks % 10 == 0 && head != null && !over && !paused) {
			snakeParts.add(new Point(head.x,head.y));
			if (direction == UP) {
				if (head.y - 1 >= 0)
					head = new Point(head.x, head.y - 1);
				else if(wrap)
					head = new Point(head.x, frame.getHeight() / SCALE - 3);
				else
					over = true;
			}
			if (direction == DOWN) {
				if (head.y + 1 < frame.getHeight() / SCALE - 3)
					head = new Point(head.x, head.y + 1);
				else if(wrap)
					head = new Point(head.x,0);
				else
					over = true;
			}
			if (direction == LEFT) {
				if (head.x - 1 >= 0)
					head = new Point(head.x - 1, head.y);
				else if(wrap)
					head = new Point(frame.getWidth() / SCALE - 1, head.y);
				else
					over = true;
			}
			if (direction == RIGHT) {
				if (head.x + 1 < frame.getWidth() / SCALE - 1)
					head = new Point(head.x + 1, head.y);
				else if(wrap)
					head = new Point(0, head.y);
				else
					over = true;
			}
			while(snakeParts.size()>tailLength)
				snakeParts.remove(0);

			if (cherry != null) {
				if (head.x == cherry.x && head.y == cherry.y) {
					score+= 10;
					tailLength ++;
					do{
						cherry.setLocation(random.nextInt(frame.getWidth()/SCALE -3),random.nextInt(frame.getHeight()/SCALE-3));
					} while(!isFree(cherry, snakeParts));
				}
			}
			
			for(Point point : snakeParts){
				if(head.x == point.x && head.y == point.y){
					over = true;
				}
			}
		}

	}

	public static void main(String[] args) {
		snake = new Snake();
	}
	
	private boolean isFree(Point point, ArrayList<Point> snakeParts){
		for(Point snakePart : snakeParts){
			if(point.x == snakePart.x && point.y == snakePart.y){
				return false;
			}
		}
		return true;
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {

		int keyPress = e.getKeyCode();
		
		if(!paused){
			if((keyPress == KeyEvent.VK_LEFT || keyPress == KeyEvent.VK_A) && snakeParts.get(snakeParts.size()-1).x!=head.x-1)
				direction = LEFT;
			if((keyPress == KeyEvent.VK_RIGHT || keyPress == KeyEvent.VK_D) && snakeParts.get(snakeParts.size()-1).x!=head.x+1)
				direction = RIGHT;
			if((keyPress == KeyEvent.VK_UP || keyPress == KeyEvent.VK_W) && snakeParts.get(snakeParts.size()-1).y!=head.y-1)
				direction = UP;
			if((keyPress == KeyEvent.VK_DOWN || keyPress == KeyEvent.VK_S) && snakeParts.get(snakeParts.size()-1).y!=head.y+1)
				direction = DOWN;
			if(keyPress == KeyEvent.VK_PERIOD){
				timer.setDelay(timer.getDelay()-1);
				timer.restart();
				System.out.println(timer.getDelay());
			}
			if(keyPress == KeyEvent.VK_R){
				snakeParts.clear();
				head = new Point(0, 0);
				score = 0;
				tailLength = INIT_LENGTH;
				direction = DOWN;
				over = false;
				
			}
			if(keyPress == KeyEvent.VK_COMMA){
				timer.setDelay(timer.getDelay()+1);
				timer.restart();
				System.out.println(timer.getDelay());
			}
		}
		
		if(keyPress == KeyEvent.VK_SPACE)
			paused = !paused;
		
		if(keyPress == KeyEvent.VK_ENTER)
			wrap = !wrap;
		
		if(keyPress==KeyEvent.VK_ESCAPE)
			System.exit(1);
	}

	public void keyReleased(KeyEvent e) {
		
	}

}
