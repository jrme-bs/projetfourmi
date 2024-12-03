package proie;

import java.awt.Point;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import vue.ContexteDeSimulation;

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
	
	public int getPoids() 
	{
		return poids;
	}
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		contexte.setProie(this);
	}
	

}
