package graphe;

import java.util.Scanner;

public class testGraphe {

	public static void main(String[] args) {
		Graphe graphe = new Graphe();
		int choix;
		int valeur;
		String nom;
		Sommet sommet;
		String depart;
		String fin;
		Arete arete;
		Sommet sommetDepart;
		Sommet sommetFin;
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Gestion de graphe");
			System.out.println("=====================");

			System.out.println("1. Ajouter un sommet");
			System.out.println("2. Ajouter une arete");

			System.out.println("Veuillez entrer votre choix : ");
			choix = sc.nextInt();

			if (choix == 1) {
				System.out.println("Entrez le nom du sommet :");
				nom = sc.next();
				sommet = new Sommet(nom);
				graphe.addSommet(sommet);
			}
			if (choix == 2) {
				System.out.println("Entrez le sommet de départ : ");
				depart = sc.next();
				System.out.println("Entrez le sommet de fin : ");
				fin = sc.next();
				System.out.println("Entrez la valeur : ");
				valeur = sc.nextInt();
				sommetDepart = graphe.getSommetByNom(depart);
				sommetFin = graphe.getSommetByNom(fin);
				if (sommetDepart != null && sommetFin != null) {
					System.out.println("Un des sommet n'existe pas…");
				} else {
					arete = new Arete(sommetDepart, sommetFin, valeur);
					graphe.addArete(arete);
				}
			}
		}
	}
}
