
import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JLabel;

public class project extends Applet implements KeyListener,MouseListener,MouseMotionListener,Runnable
{
	
	Thread t1 = new Thread(this);
	public int xc, yc;
	public int key;
	public long startTime = System.currentTimeMillis();
	public int color; //color selected
	public int xblue = 150, yblue = 400, xred = 450, yred = 400, xyellow = 750, yyellow = 400, xgreen = 1050, ygreen = 400; //start co-ordinates of boxes
	public int xbluee = 300, ybluee = 600, xrede = 600, yrede = 600, xyellowe = 900, yyellowe = 600, xgreene = 1200, ygreene = 600; //end co-ordinates of boxes
	public int xbox = 150, ybox = 200;
	Random r = new Random();
	public boolean score = false;
	public int points;
	int xp = 20, yp = 10, xpe = 50, ype = 50; //co-ordinates of pause block
	Rectangle rect;
	public int xpoint, ypoint; //co-ordinates of mouse pointer
	public boolean paus = false; //for checking paused or not
	int []resumex = new int[]{20,20,50}; //for printing triangle for pause/play
	int []resumey = new int[]{10,50,25}; //for printing triangle for pause/play
	public boolean start = false; //for checking start
	public int startx, starty, startxe, startye;
	public boolean starttest = true;
	public Button startgame;
	public TextField t;
	public boolean mode = false;
	public boolean st = false; //for starting mouse mode
	public boolean dropball;
	public int time = 20;
	public boolean exit = false;
	public int xexit = 1100, yexit = 520,xexite = 1300, yexite = 620;
	public int xnext = 490, xnexte = 840,ynext = 400,ynexte = 500;
	public int level, levelpoints;
	public boolean gameover = false;
	public int target;
	public boolean win = false;
	int []scorecard = new int[7];
	public void start()
	{
		t1.start();
	}
	JLabel txt;
	Image background;
	java.net.URL backgroundURL;
	public void init()
	{
		addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		level = 1;               
//		t=new TextField("Enter player name");
//		t.setBounds(100, 300, 200, 200);
//		add(t);
//		 backgroundURL=this.getClass().getResource("background.JPG");
//		 background=getImage(backgroundURL);
//		 txt=new JLabel("TEST");
//		 getContentPane().add(txt,BorderLayout.CENTER);
//		 
	}
	public void reinitialize()
	{
		scorecard[level] = levelpoints;
		levelpoints = 0;
		level++;
		st = false;
		dropball = true;
		color = r.nextInt(4);
		xc = 100 + r.nextInt(1200);
		yc = 0;
		rect = new Rectangle(xc - 50, yc - 50, 100, 100);
		win = false;
	}
	public void run()
	{
		while(true)
		{
			time = 20;
			do
			{
				switch(level)
				{
				case 1:
				{
					target = 90;
					break;
				}
				case 2:
				{
					target = 180;
					break;
				}
				case 3:
				{
					target = 250;
					break;
				}
				case 4:
				{
					target = 330;
					break;
				}
				case 5:
				{
					target = 400;
					break;
				}
				}
				st = false;
				dropball = true;
				color = r.nextInt(4);
				xc = 100 + r.nextInt(1200);
				yc = 0;
				rect = new Rectangle(xc - 50, yc - 50, 100, 100);
				while(true)
				{
					while(paus)
					{
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					xc = xc % 1400;
					if(xc < 0)
						xc = 1400;
					if(xc >= xblue && xc <= xbluee && yc >= yblue && yc <= ybluee)
					{
						if(color == 0)
						{
							points += 5;
							levelpoints += 5;
						}
						else
						{
							points -= 2;
							levelpoints -= 2;
						}
						break;
					}
					else if(xc >= xred && xc <= xrede && yc >= yred && yc <= yrede)
					{
						if(color == 1)
						{
							points += 5;
							levelpoints += 5;
						}
						else
						{
							points -= 2;
							levelpoints -= 2;
						}
						break;
					}
					else if(xc >= xyellow && xc <= xyellowe && yc >= yyellow && yc <= yyellowe)
					{
						if(color == 2)
						{
							points += 5;
							levelpoints += 5;
						}
						else
						{
							points -= 2;
							levelpoints -= 2;
						}
						break;
					}
					else if(xc >= xgreen && xc <= xgreene && yc >= ygreen && yc <= ygreene)
					{
						if(color == 3)
						{
							points += 5;
							levelpoints += 5;
						}
						else
						{
							points -= 2;
							levelpoints -= 2;
						}
						break;
					}
					else if(yc > 800)
					{
						points -= 5;
						levelpoints -= 5;
						break;
					}
					else if(start)
					{
						if(dropball && time > 0)
						{
							switch(level)
							{
							case 1:
							{
								yc++;
								break;
							}
							case 2:
							{
								yc++;
								break;
							}
							case 3:
							{
								yc+=2;
								break;
							}
							case 4:
							{
								yc+=2;
								break;
							}
							case 5:
							{
								yc+=3;
								break;
							}
							}
						}
							
						rect.setLocation(xc, yc);
					}
					repaint();
					try {
						Thread.sleep(40);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}	while(time > 0);
		}
	}
	public void paint(Graphics g)
	{
		 super.paint(g);
		 g.drawImage(background, 0, 0, null);

		Font font = new Font("printer", Font.PLAIN, 80);
		Font score = new Font("printscore", Font.PLAIN, 50);
		Font score1 = new Font("printscore", Font.PLAIN, 80);
		if(!start)
		{
			setBackground(Color.CYAN);
//			startgame = new Button();
//			startgame.setBounds(startx, starty, 400, 300);
//			add(startgame);
			g.setColor(Color.RED);
//			g.drawImage(backGround, 25, 25, null);
			startx = 150;
			starty = 200;
			startxe = startx + 400;
			startye = starty + 300;
			g.setFont(score1);
			g.drawString("SELECT ONE MODE FROM BELOW", 10, 100);
			g.fillRect(startx, starty, 400, 300);
			g.setFont(font);
			g.setColor(Color.YELLOW);
			g.drawString("Keyboard", 180, 380);
			g.fillRect(startx + 600, starty, 400, 300);
			g.setFont(font);
			g.setColor(Color.RED);
			g.drawString("Mouse", 830, 380);
		}
		else if (time > 0 && start)
		{
			if(!paus)
			{
				g.setColor(Color.BLACK);
				g.fillRect(20, 10, 10, 40);
				g.fillRect(40, 10, 10, 40);
			}
			else
			{
				g.setColor(Color.BLACK);
				
				g.fillPolygon(resumex, resumey, 3);
			}
			if(start)
			{
				switch(level)
				{
				case 1:
				{
					time = 60 - (int)(System.currentTimeMillis() - startTime) / 1000;
					break;
				}
				case 2:
				{
					time = 50 - (int)(System.currentTimeMillis() - startTime) / 1000;
					break;
				}
				case 3:
				{
					time = 40 - (int)(System.currentTimeMillis() - startTime) / 1000;
					break;
				}
				case 4:
				{
					time = 40 - (int)(System.currentTimeMillis() - startTime) / 1000;
					break;
				}
				case 5:
				{
					time = 30 - (int)(System.currentTimeMillis() - startTime) / 1000;
					break;
				}
				}
			}
			
			
//			time = 40-(int)(System.currentTimeMillis()-startTime)/1000;
			if(time < 0)
				time = 0;
			g.setFont(score);
			g.setColor(Color.BLACK);
		    g.drawString("TIME = "+String.valueOf(time), 1100, 50);
			g.setColor(Color.BLUE);
			g.fillRect(xblue, yblue, xbox, ybox);
			g.setColor(Color.RED);
			g.fillRect(xred, yred, xbox, ybox);
			g.setColor(Color.YELLOW);
			g.fillRect(xyellow, yyellow, xbox, ybox);
			g.setColor(Color.GREEN);
			g.fillRect(xgreen, ygreen, xbox, ybox);
			g.setFont(score);
			g.setColor(Color.BLACK);
			g.drawString("SCORE=" + Integer.toString(points), 750, 50);
//			g.drawString("LEVEL SCORE="+Integer.toString(levelpoints), 650, 50);
			g.drawString("TARGET=" + Integer.toString(target), 400, 50);
			g.drawString("LEVEL " + Integer.toString(level), 100, 50);
			switch(color)
			{
			case 0:
			{
				g.setColor(Color.BLUE);
				break;
			}
			case 1:
			{
				g.setColor(Color.RED);
				break;
			}
			case 2:
			{
				g.setColor(Color.YELLOW);
				break;
			}
			case 3:
			{
				g.setColor(Color.GREEN);
				break;
			}
			}
			//g.fillOval(xc-50, yc-50, 100, 100);
			Graphics2D g2 = (Graphics2D)g;
			g2.fill(rect);
		}
		else
		{
			if(points < target)
			{
				g.setFont(font);
				g.setColor(Color.red);
				g.drawString("Game Over!", 450, 100);
				g.fillRect(1100, 520, 200, 100);
				g.setColor(Color.DARK_GRAY);
				g.drawString("SCORE=" + Integer.toString(points), 470, 250);
				g.setColor(Color.BLACK);
				g.drawString("EXIT", 1115, 600);
				if(level <= 5)
					g.drawString("YOU LOST LEVEL " + Integer.toString(level)+"!!", 280, 450);
				else 
					g.drawString("YOU LOST THE GAME!!", 290, 450);
			}
			else
			{
				if(level > 4)
				{
					g.setFont(font);
					g.setColor(Color.red);
					g.drawString("Game Over!", 450, 100);
					g.fillRect(1100, 520, 200, 100);
					g.setColor(Color.DARK_GRAY);
					g.drawString("SCORE=" + Integer.toString(points), 470, 250);
					g.setColor(Color.BLACK);
					g.drawString("EXIT", 1115, 600);
					g.drawString("YOU WON THE GAME!!", 290, 450);
				}
				else
				{
					g.setFont(font);
					g.setColor(Color.DARK_GRAY);
					g.drawString("SCORE=" + Integer.toString(points), 470, 350);
					g.setColor(Color.RED);
					g.fillRect(490, 400, 340, 100);
					g.setColor(Color.BLACK);
					g.setFont(score);
					g.drawString("NEXT LEVEL", 520, 470);
					g.setColor(Color.RED);
					g.fillRect(1100, 520, 200, 100);
					g.setFont(font);
					g.drawString("YOU WON LEVEL " + Integer.toString(level) + "!!", 300, 200);
					g.setColor(Color.BLACK);
					g.drawString("EXIT", 1115, 600);
				}

			}
			
		}
		

	}
	public void keyPressed(KeyEvent ke)
	{
	
//		while(balls>0)
//		{
		if(!mode)
		{
			key = ke.getKeyCode();
			if(key == KeyEvent.VK_LEFT)
			{
				xc = xc-15;
				rect.setLocation(xc % 1400, yc);
			}
			else if(key == KeyEvent.VK_RIGHT)
			{
				xc = xc + 15;
				rect.setLocation(xc % 1400, yc);
			}
			else if(key == KeyEvent.VK_DOWN)
			{
				yc = yc + 10;
				rect.setLocation(xc % 1400, yc);
			}
			repaint();
		}

	//	}
		
	}
	public void keyReleased(KeyEvent ke)
	{
//		while(balls>0)
//		{
//
//			key=ke.getKeyCode();
//			if(key == KeyEvent.VK_1)
//				xc-=5;
//			else if(key ==KeyEvent.VK_1)
//				xc+=5;
//			repaint();
//		}
	}
	public void keyTyped(KeyEvent ke)
	{
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		xpoint = e.getX();
		ypoint = e.getY();
		if(starttest)
		{
			if(xpoint >= startx && xpoint <= startxe && ypoint >= starty && ypoint <= startye)
			{
				start = true;
				startTime = System.currentTimeMillis();
			}
			else if(xpoint >= startx + 600 && xpoint <= startxe + 600 && ypoint >= starty && ypoint <= startye)
			{
				start = true;
				mode = true;
			}
			starttest = false;
		}
		else 
		{
			if(xpoint >= xp && xpoint <= xpe && ypoint >= yp && ypoint <= ype)
			{
				if(paus)
					paus = false;
				else
					paus = true;
				repaint();
			}
			if(mode)
			{
				if(xpoint >= xc && xpoint <= xc + 100 && ypoint >= yc && ypoint <= yc + 100)
				{
					st = true;
				}
			}
		}
		if(start && !(time > 0))
		{
			if(levelpoints < target)
			{
				gameover = true;
			}
			if(level > 5)
			{
				gameover = true;
				if(levelpoints > target)
					win = true;
			}
			else if(levelpoints > target)
			{
				win = true;
			}
			if(xpoint >= xexit && xpoint <= xexite && ypoint >= yexit && ypoint <= yexite)
			{
				System.exit(0);
			}
			else if(xpoint >= xnext && xpoint <= xnexte && ypoint >= ynext && ypoint <= ynexte)
			{
				time = 20;
				startTime = System.currentTimeMillis();
//				try {
//					Thread.sleep(1);
//				} catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				reinitialize();
			}
			
			
		}
		
		
	}
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(st)
		{
			dropball = false;
			xc = e.getX() - 50;
			yc = e.getY() - 50;
			rect.setLocation(xc % 1400, yc);
			repaint();
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}


