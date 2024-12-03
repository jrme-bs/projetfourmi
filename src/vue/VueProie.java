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
		this.setBackground((Color.blue));
	}
	@Override
	public void redessine() {
		// TODO Auto-generated method stub
		
	}

}
