package com.adam.Minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Minesweeper extends MouseAdapter implements ActionListener  {
	
	public JFrame jframe;
	public static Minesweeper minesweeper;
	private RenderScreen screen;
	private Random random = new Random();
	private int gridX = 9, gridY = 9, numberOfMines = 10;
	public STATE gameState = STATE.Game;
	public Timer timer = new Timer(1000, this);
	public int minutes = 0, seconds = 0;
	public boolean firstClick = true;
	
	
	
	public Minesweeper(){
		screen = new RenderScreen();
		jframe = new JFrame("Minesweeper");
		jframe.setVisible(true);
		jframe.setSize(300,400);
		jframe.setResizable(false);
		jframe.setLocation(700,300);
		jframe.add(screen);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.addMouseListener(this);
	}
	
	public int getGridX() {
		return gridX;
	}

	public void setGridX(int gridX) {
		this.gridX = gridX;
	}

	public int getGridY() {
		return gridY;
	}

	public void setGridY(int gridY) {
		this.gridY = gridY;
	}

	public int getNumberOfMines() {
		return numberOfMines;
	}

	public void setNumberOfMines(int numberOfMines) {
		this.numberOfMines = numberOfMines;
	}

	public boolean[][] mines = new boolean[gridX][gridY];
	public boolean[][] uncovered = new boolean[gridX][gridY];
	public boolean[][] flags = new boolean[gridX][gridY]; 
	public int[][] solution = new int[gridX][gridY];
	private int numberOfFlags = 0, numberUncovered=0;

	public int getNumberOfFlags() {
		return numberOfFlags;
	}

	public void setNumberOfFlags(int numberOfFlags) {
		this.numberOfFlags = numberOfFlags;
	}

	public static void main(String[] args) {
		minesweeper = new Minesweeper();

	}
	
	public void addMines(){
		mines = new boolean[gridX][gridY];
		for(int i = 0; i<numberOfMines; i++){
			boolean repeated = false;
			do{
				int x = random.nextInt(gridX);
				int y = random.nextInt(gridY);
				if(!mines[x][y]){
					mines[x][y]=true;
					repeated = false;
				} else
					repeated = true;
			} while(repeated);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if(gameState == STATE.Game){
			seconds++;
			if(seconds == 60){
				seconds=0;
				minutes++;
			}
			screen.repaint();
		}
	}
	
	public void mousePressed(MouseEvent event) {
		int mx = event.getX(), my = event.getY();
		if(gameState == STATE.Game){
			if(firstClick){
				if(event.getButton() == MouseEvent.BUTTON1){
					for(int y = 0; y<gridY; y++){
						for(int x=0; x<gridX; x++){
							if(mouseOver(mx, my, x*30+10, y*30+25+60, 30, 30)){
								initialClick(x, y);
								firstClick = false;
							}
						}
					}
				}
			}
			if(event.getButton() == MouseEvent.BUTTON1)
				for(int y = 0; y<gridY; y++){
					for(int x=0; x<gridX; x++){
						if(mouseOver(mx, my, x*30+10, y*30+25+60, 30, 30)){
							clickSquare(x,y);
							
						}
					}
				}
			if(event.getButton() == MouseEvent.BUTTON3)
				for(int y = 0; y<gridY; y++){
					for(int x=0; x<gridX; x++){
						if(mouseOver(mx, my, x*30+10, y*30+25+60, 30, 30))
							flagSquare(x,y);
					}
				}
		}
		if(event.getButton() == MouseEvent.BUTTON1 && mouseOver(mx,my,130, 35, 30, 30)){
			restart();
		}
		screen.repaint();
	}
	
	private void restart() {
		timer.stop();
		seconds=0;
		minutes=0;
		firstClick=true;
		uncovered = new boolean[gridX][gridY];
		gameState = STATE.Game;
		flags = new boolean[gridX][gridY];
		numberOfFlags=0;
		numberUncovered=0;
	}

	private void flagSquare(int x, int y) {
		if(!uncovered[x][y]){
			if(flags[x][y]){
				flags[x][y] = false;
				numberOfFlags--;
			} else {
				flags[x][y] = true;
				numberOfFlags++;
			}
		}
		
	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width && my > y && my < y + height)
			return true;
		else
			return false;
	}
	
	private void clickSquare(int x, int y){
		if(!flags[x][y] && !uncovered[x][y]){
			uncovered[x][y] = true;
			numberUncovered++;
			if(solution[x][y]==0){
				if(x-1 >= 0 && y-1 >= 0 && !uncovered[x-1][y-1]){
					clickSquare(x-1,y-1);
				}
				if(x-1 >= 0 && !uncovered[x-1][y]){
					clickSquare(x-1,y);
				}
				if(x-1 >= 0 && y+1 < gridY && !uncovered[x-1][y+1]){
					clickSquare(x-1, y+1);
				}
				if(y+1 < gridY && !uncovered[x][y+1]){
					clickSquare(x, y+1);
				}
				if(x+1 < gridX && y+1 < gridY && !uncovered[x+1][y+1]){
					clickSquare(x+1,y+1);
				}
				if(x+1 < gridX && !uncovered[x+1][y]){
					clickSquare(x+1, y);
				}
				if(x+1 < gridX && y-1 >= 0 && !uncovered[x+1][y-1]){
					clickSquare(x+1, y-1);
				}
				if(y-1 >= 0 && !uncovered[x][y-1]){
					clickSquare(x,y-1);
				}
			} else if(solution[x][y] == -1){
				for(int y1 = 0; y1<gridY; y1++){
					for(int x1=0; x1<gridX; x1++){
						uncovered[x1][y1]=true;
					}
				}
				numberOfFlags=numberOfMines;
				gameState = STATE.Loss;
			}
		}
		if(numberUncovered == (gridX*gridY-numberOfMines)){
			gameState = STATE.Win;
		}
	}
	
	private void initialClick(int initX, int initY){
		mines = new boolean[gridX][gridY];
		for(int i = 0; i<numberOfMines; i++){
			boolean repeated = false;
			do{
				int x = random.nextInt(gridX);
				int y = random.nextInt(gridY);
				if(availableForMine(x,y,initX,initY)){
					mines[x][y]=true;
					repeated = false;
				} else
					repeated = true;
			} while(repeated);
		}
		createSolutionArray();
		timer.start();
	}
	
	private boolean availableForMine(int x, int y, int initX, int initY){
		boolean available = true;
		if(mines[x][y]){
			available = false;
		}
		if(x==initX && y ==initY){
			available = false;
		}
		if(initX-1 >= 0 && initY-1 >= 0){
			if(x==initX-1 && y==initY-1)
				available = false;
		}
		if(initX-1 >= 0){
			if(x==initX-1 && y==initY)
				available = false;
		}
		if(initX-1 >= 0 && initY+1 < gridY){
			if(x==initX-1 && y==initY+1)
				available = false;
		}
		if(initY+1 < gridY){
			if(x==initX && y==initY+1)
				available = false;
		}
		if(initX+1 < gridX && initY+1 < gridY){
			if(x==initX+1 && y==initY+1)
				available = false;
		}
		if(initX+1 < gridX){
			if(x==initX+1 && y==initY)
				available = false;
		}
		if(initX+1 < gridX && initY-1 >= 0){
			if(x==initX+1 && y==initY-1)
				available = false;
		}
		if(initY-1 >= 0){
			if(x==initX && y==initY-1)
				available = false;
		}
		
		return available;
	}
	
	private void createSolutionArray(){
		
		for(int y = 0; y<gridY; y++){
			for(int x=0; x<gridX; x++){
				if(mines[x][y]){
					solution[x][y] = -1;
				} else {
					solution[x][y]=0;
					if(x-1 >= 0 && y-1 >= 0){
						if(mines[x-1][y-1])
							solution[x][y]++;
					}
					if(x-1 >= 0){
						if(mines[x-1][y])
							solution[x][y]++;
					}
					if(x-1 >= 0 && y+1 < gridY){
						if(mines[x-1][y+1])
							solution[x][y]++;
					}
					if(y+1 < gridY){
						if(mines[x][y+1])
							solution[x][y]++;
					}
					if(x+1 < gridX && y+1 < gridY){
						if(mines[x+1][y+1])
							solution[x][y]++;
					}
					if(x+1 < gridX){
						if(mines[x+1][y])
							solution[x][y]++;
					}
					if(x+1 < gridX && y-1 >= 0){
						if(mines[x+1][y-1])
							solution[x][y]++;
					}
					if(y-1 >= 0){
						if(mines[x][y-1])
							solution[x][y]++;
					}
				}
					
			}
		}
	}

}
