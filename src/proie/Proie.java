package proie;

import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vue.ContexteDeSimulation;


public class Proie {
	
	private Point pos;
	private int poids;
	private boolean vivante;
	private int step = 10 ;
	
	public Proie(Point pos, int poids)
	{
		this.pos = pos;
		this.poids = poids;
		this.vivante = true;
		
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
	public boolean getVivante()
	{
		return this.vivante;
	}
	public void tueProie()
	{
		this.vivante = false;
	}

	public void contactAvecFourmie()
	{
		
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
				y = y + step;
				break;
			}
			case 1: {
				x = x + step;
				break;
			}
			case 2: {
				y = y - step;
				break;
			}
			case 3: {
				x = x - step;
				break;
			}
		}
		
		this.setPosition(new Point(x,y));
		
	}
}
	

