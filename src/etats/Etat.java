package etats;



import trace.BilanEtat;
import trace.BilanRole;
import vue.ContexteDeSimulation;
import vue.VueIndividu;

public abstract class Etat {
	public abstract void etapeDeSimulation(ContexteDeSimulation contexte);
	public abstract void initialise(VueIndividu vue );
	public abstract void updateBilanEtat(BilanEtat tan);
	public abstract boolean deposePheromone();
	public abstract void updateBilanRole(BilanRole bilan);
}
