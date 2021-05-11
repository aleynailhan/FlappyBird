package flappyBird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class FlappyBird implements ActionListener,MouseListener{

	public static FlappyBird flappyBird;
	
	public final int W�DTH=750, HE�GHT=750;
	
	public Renderer renderer;
	
	public Rectangle bird;
	
	public boolean gameOver,started;
	
	public ArrayList<Rectangle> columns;
	
	public Random rand;
	
	public int ticks, yMotion,score;
	
	public FlappyBird()
	{//pro�ram �al���r �al��maz penceremiz �al��s�n.
		
		JFrame jframe= new JFrame();
		Timer timer= new Timer(20,this);
		renderer= new Renderer();
		rand=new Random();
		jframe.add(renderer);//olu�turdu�umuz paneli pencereye ekliyoruz.
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//pro�ram� kapatmak i�in gereken kod
		jframe.setSize(W�DTH,HE�GHT);
		jframe.addMouseListener(this);
		jframe.setResizable(false);//pencerenin tekrardan boyutland�r�lmamas�n� sa�l�yoruz.
		jframe.setVisible(true);//pencerenin g�r�nmesini sa�l�yoruz.
		
		bird=new Rectangle(W�DTH/2-10, HE�GHT/2-10, 20,20);
		columns= new ArrayList<Rectangle>();
		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);
		timer.start();
	}
	
	public void paintColumn(Graphics g, Rectangle column)
	{
		g.setColor(Color.green.darker());
		g.fillRect(column.x, column.y, column.width, column.height);
	}
	public void jump()
	{
		if(gameOver)
		{

			bird=new Rectangle(W�DTH/2-10, HE�GHT/2-10, 20,20);
			columns.clear();
			addColumn(true);
			addColumn(true);
			addColumn(true);
			addColumn(true);
			gameOver=false;
		}
		if(!started)
		{
			started=true;
		}
		else if(!gameOver)
		{
			if(yMotion>0)
			{
				yMotion=0;
			}
			yMotion-=10;
		}
	}
	public void addColumn(boolean start)
	{
		int space=300;
		int width=100;
		int height=50+rand.nextInt(300);
		
		if(start)
		{
			columns.add(new Rectangle(W�DTH+width+columns.size()*300,HE�GHT-height-120,width,height));
			columns.add(new Rectangle(W�DTH+width+ (columns.size()-1)*300,0,width,HE�GHT-height-space));
			
		}
		else
		{
			columns.add(new Rectangle(columns.get(columns.size()-1).x+600,HE�GHT-height-120,width,height));
			columns.add(new Rectangle(columns.get(columns.size()-1).x, 0,width,HE�GHT-height-space));
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	
		int speed=10;
		ticks++;
		
		if(started)
	{
		for(int i=0;i<columns.size();i++)
		{
			Rectangle column= columns.get(i);
			column.x-=speed;
		}
		if(ticks%2==0 && yMotion<15)
		{
			yMotion+=2;
		}
		for(int i=0;i<columns.size();i++)
		{
			Rectangle column= columns.get(i);
			if(column.x+column.width<0)
			{
				columns.remove(column);
				if(column.y==0)
				{
					addColumn(false);
				}
			}
		}
	
		bird.y+=yMotion;
		
		for(Rectangle column: columns)
		{
			if(column.intersects(bird))
			{
				gameOver=true;
				bird.x=column.x-bird.width;
			}
		}
		if(bird.y> HE�GHT-120||bird.y<0)
		{
			
			gameOver=true;
		}
		if(bird.y+yMotion-120>=HE�GHT-120)
		{
			bird.y=HE�GHT-120-bird.height;
		}
	}
		renderer.repaint();
		
	}
	
    public void repaint(Graphics g) {
		
    	g.setColor(Color.black
    			);
		g.fillRect(0, 0, W�DTH, HE�GHT);
		
		g.setColor(Color.orange);
		g.fillRect(0, HE�GHT-120, W�DTH, 120);
	
		g.setColor(Color.green);
		g.fillRect(0, HE�GHT-120, W�DTH, 20);
		
		g.setColor(Color.red);
		g.fillRect(bird.x, bird.y, bird.width, bird.height);
		
		for(Rectangle column:columns)
		{
			paintColumn(g, column);
		}
		g.setColor(Color.white);
		g.setFont(new Font("Arial",1,100));
		
		if(!started)
		{
			g.drawString("Click To Start!", 75, HE�GHT/2-50);
		}
		if(gameOver)
		{
			g.drawString("Game Over!", 100, HE�GHT/2-50);
		}
	}
    
	public static void main(String[] args)
	{
		flappyBird= new FlappyBird();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		jump();
		
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

	
	

}
