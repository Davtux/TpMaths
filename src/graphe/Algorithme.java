package graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public static List<Arete> sollin(Graphe graphe) {
		List<Arete> aretes = new ArrayList<Arete>();

		for (int i = 0; i < graphe.getNombreSommet(); i++) {
			Sommet s = graphe.getSommets()[i];

			Arete arete_minimum = null;
			for (Arete a : graphe.getAretesBySommet(s)) {
				if (a.getInitial() != a.getFin()) {
					if (arete_minimum == null) {
						arete_minimum = a;
					} else {
						arete_minimum = (arete_minimum.getValeur() >= a
								.getValeur()) ? a : arete_minimum;
					}
				}
			}

			if (!aretes.contains(arete_minimum))
				aretes.add(arete_minimum);
		}

		return aretes;
	}

	public static void dijkstra(Graphe graphe, Sommet debut) {
		Map<Sommet, Integer> distances = new HashMap<>(
				graphe.getNombreSommet() - 1);

		List<Sommet> sommets = new ArrayList<>(graphe.getNombreSommet());

		// Initialisation
		for (int i = 0; i < graphe.getNombreSommet(); i++) {
			if (graphe.getSommets()[i].equals(debut)) {
				distances.put(graphe.getSommets()[i], 0);
			} else {
				distances.put(graphe.getSommets()[i], (int) 10_000_000L);
			}

			sommets.add(graphe.getSommets()[i]);
		}

		while (!sommets.isEmpty()) {
			// On récupère le sommet le plus proche (the firstest sommet)
			Sommet voisin_proche = null;
			for (Sommet voisin : sommets) {
				if (voisin_proche == null)
					voisin_proche = voisin;
				else if (distances.get(voisin_proche) > distances.get(voisin))
					voisin_proche = voisin;
			}
			sommets.remove(voisin_proche);

			for (Sommet voisin : graphe.getVoisinBySommet(voisin_proche)) {
				final Integer distance_derivee = distances.get(voisin_proche)
						+ graphe.getAreteBySommets(voisin_proche, voisin)
								.getValeur();

				if (distances.get(voisin) > distance_derivee) {
					distances.remove(voisin);
					distances.put(voisin, distance_derivee);
				}
			}
		}

		System.out.println("Résultat des distances les plus courtes depuis "
				+ debut.getNom());
		for (Sommet s : distances.keySet()) {
			System.out.println("[" + debut.getNom() + " -> " + s.getNom()
					+ "]: " + distances.get(s));
		}
	}
}
