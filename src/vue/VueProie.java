package vue;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import proie.Proie;

public class VueProie extends VueElement {

	Proie proie;
	int taille = 10;
	
	public Proie getProie()
	{
		return proie;
	}
	
	public VueProie(Proie proie)
	{
		this.proie = proie;
		Point pos = this.proie.getPosition();
		this.setBounds((pos.x - (taille/2)), (pos.y - taille), taille, taille);
		if(proie.getPoids() < 120)
		{
			this.setBackground((Color.green));
		}
		else
		{
			this.setBackground(Color.red);
		}
			
	}
	
	@Override
	public void redessine() {
		int x = this.proie.getPosition().x - (taille/2);
		int y = this.proie.getPosition().y - taille;
		
		this.setLocation(new Point(x,y));
		this.proie.initialise(this);
	}

}
