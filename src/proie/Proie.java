package proie;

import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vue.ContexteDeSimulation;
import vue.VueProie;

public class Proie {
	
	private Point pos;
	private int poids;
	
	public Proie(Point pos, int poids)
	{
		this.pos = pos;
		this.poids = poids;
	}

	public Point getPosition()
	{
		return this.pos;
	}
	
	public void setPosition(Point pos)
	{
		this.pos.x = pos.x;
		this.pos.y = pos.y;
	}
	
	public int getPoids() 
	{
		return poids;
	}
	

	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		contexte.setProie(this);
		
		int x = this.getPosition().x;
		int y = this.getPosition().y;
		
		int pos;
		Random rand = new Random();
		pos = rand.nextInt(4);
		
		switch (pos) {
			case 0: {
				y = y + 10;
				break;
			}
			case 1: {
				x = x + 10;
				break;
			}
			case 2: {
				y = y - 10;
				break;
			}
			case 3: {
				x = x - 10;
				break;
			}
		}
		
		this.setPosition(new Point(x,y));
		
	}
}
	

