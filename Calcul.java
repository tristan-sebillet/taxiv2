package unpackage;
/**
 * Programme principal du projet remboursement taxi
 *
 * @author tristan-sebillet
 * @version 2.0
 */
import java.util.List;

public class Calcul {
	public static double calculer(int i, List<AR> maListeAR, List<AS> maListeAS , Saisie maSaisie)
	{
		double montantRemb = 0;

		//Calcul remboursement
				//Si c'est un aller simple
				if(maSaisie.getTrajet().equals("AS")||maSaisie.getTrajet().equals("as") ){
					//Si c'est en semaine de jour
					if(maSaisie.getDateDep().equals("S")||maSaisie.getDateDep().equals("s") && maSaisie.getHeureDep().equals("J")||maSaisie.getHeureDep().equals("j")){
						montantRemb = maListeAS.get(i).getPriseEnCharge() + (maSaisie.getNbKm() * maListeAS.get(i).getTarifASJS());

						//Si le trajet dépasse 1h
						if(maSaisie.getHeurePar() > 1){
							montantRemb += montantRemb + (maSaisie.getHeurePar() * maListeAS.get(i).getHoraireJS());
						}
					}else
					//Sinon c'est de semaine de nuit ou en WE
					if((maSaisie.getDateDep().equals("S") ||maSaisie.getDateDep().equals("s") && maSaisie.getHeureDep().equals("N")|| maSaisie.getHeureDep().equals("n")) || maSaisie.getDateDep().equals("WE")||maSaisie.getDateDep().equals("we")){
						montantRemb = maListeAS.get(i).getPriseEnCharge() + (maSaisie.getNbKm() * maListeAS.get(i).getTarifASNW());

						//Si le trajet dépasse 1h
						if(maSaisie.getHeurePar() > 1){
							montantRemb += montantRemb + (maSaisie.getHeurePar() * maListeAS.get(i).getHoraireNW());
						}
					}
				}else
					//Si c'est en semaine de jour
					if(maSaisie.getDateDep().equals("S")||maSaisie.getDateDep().equals("s") && maSaisie.getHeureDep().equals("J")||maSaisie.getHeureDep().equals("j")){
						montantRemb = maListeAR.get(i).getPriseEnCharge() + (maSaisie.getNbKm() * maListeAR.get(i).getTarifARJS());

						//Si le trajet dépasse 1h
						if(maSaisie.getHeurePar() > 1){
							montantRemb += montantRemb + (maSaisie.getHeurePar() * maListeAR.get(i).getHoraireJS());
						}
					}else
					//Sinon c'est de semaine de nuit ou en WE
					if((maSaisie.getDateDep().equals("S") ||maSaisie.getDateDep().equals("s")&& maSaisie.getHeureDep().equals("N")) || maSaisie.getHeureDep().equals("n")|| maSaisie.getDateDep().equals("WE")||maSaisie.getDateDep().equals("we")){
						montantRemb = maListeAR.get(i).getPriseEnCharge() + (maSaisie.getNbKm() * maListeAR.get(i).getTarifARNW());

						//Si le trajet dépasse 1h
						if(maSaisie.getHeurePar() > 1){
							montantRemb += montantRemb + (maSaisie.getHeurePar() * maListeAR.get(i).getHoraireNW());
						}
				}

				return montantRemb;
	}
}