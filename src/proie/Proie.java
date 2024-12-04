package proie;

import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vue.ContexteDeSimulation;
import vue.Simulation;
import zone.Zone;
import etresVivants.Fourmi;
import terrain.Terrain;

public class Proie {
	
	private Fourmi fourmi;
	private Zone zone;
	private Point pos;
	private int poids;
	private boolean vivante;
	private int step = 5;
	private boolean dragged;
	
	public Proie(Point pos, int poids)
	{
		this.pos = pos;
		this.poids = poids;
		this.vivante = true;
		this.dragged = false;
		
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
	public int diminuerPoids(int valeur)
	{
		if (this.poids - valeur < 0) {
			int val = this.poids;
			this.poids = 0;
			return val;
		}
		else {
			this.poids -= valeur;
			return valeur;
		}
		
		
	}
	
	public void trouveFourmiDrag(ContexteDeSimulation contexte)
	{
		
		Simulation curSim = contexte.getSimulation();
		Terrain curTerrain = curSim.getTerrain();
		List<Zone> curListZone = curTerrain.getListeZone();
		
		for (Zone z : curListZone) {
			for (Proie p : z.getListeProie()) {
				if (p.equals(this)) {
					zone = z ;
				}
			}
		}
		fourmi = (Fourmi) zone.getListeFourmi().get(0);
		dragged = true;
	}
	
	public Point dragProie()
	{
		Point p = fourmi.getPos();
		return p;
	}
	
	
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		contexte.setProie(this);
		int x = this.getPosition().x;
		int y = this.getPosition().y;
		
		if(this.getVivante())
		{
			
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
		}
		else
		{
			if (dragged) {
				Point point = this.dragProie();
				x = point.x;
				y = point.y;
				
			}else {
				this.trouveFourmiDrag(contexte);
			}
			
		}

		this.setPosition(new Point(x,y));
		
}
}
	

