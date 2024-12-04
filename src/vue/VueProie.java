package vue;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import proie.Proie;

public class VueProie extends VueElement {

	Proie proie;
	
	public Proie getProie()
	{
		return proie;
	}
	
	public VueProie(Proie proie)
	{
		this.proie = proie;
		Point pos = this.proie.getPosition();
		this.setBounds(pos.x, pos.y, 10, 10);
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
		// TODO Auto-generated method stub
		this.setLocation(this.proie.getPosition());
		this.proie.initialise(this);
	}

}
