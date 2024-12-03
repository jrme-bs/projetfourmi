package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

public class VueDeplacement extends VueElement {
	private static final long serialVersionUID = 8054402121237972718L;

 	
	public VueDeplacement(Point p) {
			this.setBackground(Color.red);
			this.setDimension(new Dimension(2, 2));
			this.setLocation(p);
	}
	
	@Override
	public void redessine() {		
	}

}
