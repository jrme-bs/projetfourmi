package zone;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import java.util.ArrayList;

import etresVivants.Fourmi;
import proie.Proie;

public class Zone {
	protected Point point;
	protected Dimension dim;
	List<Proie> listeProie ;
	List<Fourmi> listeFourmi;
	
	public Zone(Point point, Dimension dim)
	{
		this.point = point ;
		this.dim = dim;
		this.listeProie = new ArrayList<Proie>();
		this.listeFourmi = new ArrayList<Fourmi>();
	}
	
	public Point getPoint() {
		return point;
	}


	public void setPoint(Point point) {
		this.point = point;
	}


	public Dimension getDim() {
		return dim;
	}


	public void setDim(Dimension dim) {
		this.dim = dim;
	}


	public List<Proie> getListeProie() {
		return listeProie;
	}

	public void setListeProie(List<Proie> listeProie) {
		this.listeProie = listeProie;
	}

	public List<Fourmi> getListeFourmi() {
		return listeFourmi;
	}

	public void setListeFourmi(List<Fourmi> listeFourmi) {
		this.listeFourmi = listeFourmi;
	}

	public void addProie(Proie proie)
	{
		this.listeProie.add(proie);
	}
	
	public void addFourmi(Fourmi fourmi)
	{
		this.listeFourmi.add(fourmi);
	}

	@Override
	public String toString() {
		return "Zone [point=" + point + ", dim=" + dim + "]";
	}
	
	
}
