package graphe;

import java.util.ArrayList;
import java.util.List;

public class Algorithme {

	public static List<Sommet> parcoursEnProfondeur(Graphe graphe, Sommet sommet) {
		sommet.setMarque(true);
		List<Sommet> listeProfondeur = new ArrayList<Sommet>();
		listeProfondeur.add(sommet);

		for (Sommet s : graphe.getVoisinBySommet(sommet)) {
			if (!s.isMarque()) {
				listeProfondeur.addAll(parcoursEnProfondeur(graphe, s));
			}
		}
		return listeProfondeur;
	}

	public static List<Sommet> parcoursEnLargeur(Graphe graphe, Sommet sommet) {
		List<Sommet> listeLargeur = new ArrayList<Sommet>();

		if (!sommet.isMarque()) {
			sommet.setMarque(true);
			listeLargeur.add(sommet);
		}

		for (int i = 0; i < listeLargeur.size(); i++) {
			for (Sommet s : graphe.getVoisinBySommet(listeLargeur.get(i))) {
				if (!s.isMarque()) {
					s.setMarque(true);
					listeLargeur.add(s);
				}
			}
		}

		return listeLargeur;
	}

	public static List<Arete> Sollin(Graphe graphe) {
		List<Arete> aretes = new ArrayList<Arete>();

		for (int i = 0; i < graphe.getNombreSommet(); i++) {
			Sommet s = graphe.getSommets()[i];

			Arete arete_minimum = null;
			for (Arete a : graphe.getAreteBySommet(s)) {
				if (a.getInitial() != a.getFin()) {
					if (arete_minimum == null) {
						arete_minimum = a;
					} else {
						arete_minimum = (arete_minimum.getValeur() > a
								.getValeur()) ? a : arete_minimum;
					}
				}
			}

			if (!aretes.contains(arete_minimum))
				aretes.add(arete_minimum);
		}

		return aretes;
	}
}
