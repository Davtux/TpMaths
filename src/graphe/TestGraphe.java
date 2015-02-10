package graphe;

import java.util.Scanner;

import drawer.Drawer;

public class TestGraphe {

	private final Graphe graphe;
	private final Scanner scanner;

	public TestGraphe(Graphe graphe) {
		this.graphe = graphe;
		scanner = new Scanner(System.in);
		this.init();
		this.run();
		scanner.close();
	}

	private void init() {
		final Sommet A = new Sommet("A");
		final Sommet B = new Sommet("B");
		final Sommet C = new Sommet("C");
		final Sommet D = new Sommet("D");
		final Sommet E = new Sommet("E");

		graphe.addSommet(A);
		graphe.addSommet(B);
		graphe.addSommet(C);
		graphe.addSommet(D);
		graphe.addSommet(E);

		graphe.addArete(new Arete(A, B, 0));
		graphe.addArete(new Arete(A, C, 0));
		graphe.addArete(new Arete(B, D, 0));
		graphe.addArete(new Arete(B, E, 0));
	}

	private void run() {
		int choix;
		int valeur;
		String nom;
		Sommet sommet;
		String depart;
		String nom_sommet;
		String fin;
		Arete arete;
		Sommet sommetDepart;
		Sommet sommetFin;

		while (true) {
			this.afficherMenu();

			System.out.println("Veuillez entrer votre choix : ");
			choix = scanner.nextInt();

			switch (choix) {
			case 1:
				System.out.println("Entrez le nom du sommet :");
				nom = scanner.next();
				sommet = new Sommet(nom);
				graphe.addSommet(sommet);
				break;

			case 2:
				System.out.println("Entrez le sommet de départ : ");
				depart = scanner.next();
				System.out.println("Entrez le sommet de fin : ");
				fin = scanner.next();
				System.out.println("Entrez la valeur : ");
				valeur = scanner.nextInt();
				sommetDepart = graphe.getSommetByNom(depart);
				sommetFin = graphe.getSommetByNom(fin);

				if (sommetDepart == null || sommetFin == null) {
					System.err.println("Un des sommet n'existe pas…");
				} else {
					arete = new Arete(sommetDepart, sommetFin, valeur);
					graphe.addArete(arete);
				}
				break;

			case 3:
				System.out.println("Entrez le nom du sommet : ");
				nom_sommet = scanner.next();
				sommet = graphe.getSommetByNom(nom_sommet);

				if (sommet == null)
					System.err.println("Le sommet n'existe pas…");
				else
					System.out.println(graphe.getVoisinBySommet(sommet));

				break;

			case 4:
				System.out.println("Entrez le nom du sommet : ");
				nom_sommet = scanner.next();
				sommet = graphe.getSommetByNom(nom_sommet);

				if (sommet == null)
					System.err.println("Le sommet n'existe pas…");
				else
					System.out.println("Profondeur : "
							+ graphe.parcoursEnProfondeur(sommet));

				graphe.resetMarquage();
				break;

			case 5:
				System.out.println("Entrez le nom du sommet : ");
				nom_sommet = scanner.next();
				sommet = graphe.getSommetByNom(nom_sommet);

				if (sommet == null)
					System.err.println("Le sommet n'existe pas…");
				else
					System.out.println("Largeur : "
							+ graphe.parcoursEnLargeur(sommet));

				graphe.resetMarquage();
				break;
				
			case 6:
				new Drawer(graphe);
				break;

			default:
				System.err.println("Choix non reconnu");
				break;
			}
		}
	}

	private void afficherMenu() {
		System.out.println("== Gestion de graphe ==");
		System.out.println("=======================");
		System.out.println("1. Ajouter un sommet");
		System.out.println("2. Ajouter une arete");
		System.out.println("3. Voisins d'un sommet");
		System.out.println("4. Parcours en profondeur");
		System.out.println("5. Parcours en largeur");
		System.out.println("6. Afficher le graphe");
	}

	public static void main(String[] args) {
		new TestGraphe(new Graphe());
	}
}
