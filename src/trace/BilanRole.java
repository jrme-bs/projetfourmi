package trace;

public class BilanRole {
	private int nbReine = 0;
	private int nbOuvriere = 0;
	private int nbSoldat = 0;
	private int nbSexue = 0;
	private int nbMale = 0;
	private int nbFemale = 0;
	
	public BilanRole() {
		
	}
	
	public void clear() {
		nbReine = 0;
		nbOuvriere = 0;
		nbSoldat = 0;
		nbMale = 0;
		nbFemale = 0;
	}
	
	public void afficheNbRoleFourmiliere() {
		nbSexue = this.getNbFemale() + this.getNbMale();
		System.out.println("============================================");
		System.out.println("Nombre de reine : " + nbReine);
		System.out.println("Nombre d'ouvrière : " + nbOuvriere);
		System.out.println("Nombre de soldat : " + nbSoldat);
		System.out.println("\nNombre de fourmi sexue : " + nbSexue);
		System.out.println("Nombre de male : " + nbMale);
		System.out.println("Nombre de femelle : " + nbFemale);
		System.out.println("============================================");
		
		clear();
	}

	public int getNbReine() {
		return nbReine;
	}

	public void setNbReine(int nbReine) {
		this.nbReine = nbReine;
	}

	public int getNbOuvriere() {
		return nbOuvriere;
	}

	public void setNbOuvriere(int nbOuvriere) {
		this.nbOuvriere = nbOuvriere;
	}

	public int getNbSoldat() {
		return nbSoldat;
	}

	public void setNbSoldat(int nbSoldat) {
		this.nbSoldat = nbSoldat;
	}

	public int getNbMale() {
		return nbMale;
	}

	public void setNbMale(int nbMale) {
		this.nbMale = nbMale;
	}

	public int getNbFemale() {
		return nbFemale;
	}

	public void setNbFemale(int nbFemale) {
		this.nbFemale = nbFemale;
	}

	public int getNbSexue() {
		return nbSexue;
	}

	public void setNbSexue(int nbSexue) {
		this.nbSexue = nbSexue;
	}
	
	
}
