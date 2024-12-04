package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import zone.Zone;

public class VueZone extends VueElement{

	Zone zone;
	
	public VueZone(Zone zone) {
		this.zone = zone;
		Point pos = zone.getPoint();
		Dimension dim = zone.getDim();		
		this.setBounds(pos.x, pos.y, dim.width, dim.height);
		this.setBackground(new Color(255, 0, 0, 0));
		this.setBorderColor(new Color(255, 0, 0, 0));
	}

	@Override
	public void redessine() {
		zone.initialise(this);
	}

}
