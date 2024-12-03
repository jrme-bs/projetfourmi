package roles;

import trace.BilanRole;
import vue.ContexteDeSimulation;

public class Soldat extends Role{

	@Override
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
	}

	@Override
	public void updateBilanRole(BilanRole bilan) {
		bilan.setNbSoldat(bilan.getNbSoldat()+1);
	}


}
