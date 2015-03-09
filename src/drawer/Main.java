package drawer;

import graphe.Algorithme;
import graphe.Arete;
import graphe.Graphe;
import graphe.Sommet;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
	private static final long serialVersionUID = 3092800724159219793L;
	private final JPanel panel_centre;
	private final List<JButton> menu;
	private final int nbItems = 8;
	private final Scanner scanner;
	private final Graphe graphe;

	public Main() {
		this.setTitle("Mathématiques - Graphes");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);

		scanner = new Scanner(System.in);
		graphe = new Graphe();
		this.initGraphe();

		panel_centre = new JPanel(new GridLayout(nbItems, 0));
		panel_centre.setCursor(new Cursor(Cursor.HAND_CURSOR));

		menu = new ArrayList<>(nbItems);
		menu.add(new JButton("Ajouter un sommet"));
		menu.add(new JButton("Ajouter une arete"));
		menu.add(new JButton("Voisins d'un sommet"));
		menu.add(new JButton("Parcours en profondeur"));
		menu.add(new JButton("Parcours en largeur"));
		menu.add(new JButton("Afficher le graphe"));
		menu.add(new JButton("Couverture de Sollin"));
		menu.add(new JButton("Dijkstra"));

		for (JButton button : menu) {
			panel_centre.add(button);
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					JButton entered = (JButton) e.getSource();
					entered.setBackground(new Color(244, 67, 54));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					JButton exited = (JButton) e.getSource();
					exited.setBackground(new Color(96, 125, 139));
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					final JButton clicked = (JButton) e.getSource();
					Main.this.handleMenuChoice(menu.indexOf(clicked) + 1);
				}
			});
		}

		this.getContentPane().add(panel_centre);

		this.setVisible(true);
	}

	private void handleMenuChoice(int choice) {
		int valeur;
		String nom;
		Sommet sommet;
		String depart;
		String nom_sommet;
		String fin;
		Arete arete;
		Sommet sommetDepart;
		Sommet sommetFin;

		switch (choice) {
		case 1:
			System.out.println("Entrez le nom du sommet :");
			nom = scanner.next();
			sommet = new Sommet(nom);
			this.graphe.addSommet(sommet);

			System.out.println(sommet);
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
				this.graphe.addArete(arete);
			}
			break;

		case 3:
			System.out.println("Entrez le nom du sommet : ");
			nom_sommet = scanner.next();
			sommet = graphe.getSommetByNom(nom_sommet);

			if (sommet == null)
				System.err.println("Le sommet n'existe pas…");
			else
				for (Sommet s : this.graphe.getVoisinBySommet(sommet)) {
					System.out.println(sommet + " -> " + s);
				}

			break;

		case 4:
			System.out.println("Entrez le nom du sommet : ");
			nom_sommet = scanner.next();
			sommet = this.graphe.getSommetByNom(nom_sommet);

			if (sommet == null)
				System.err.println("Le sommet n'existe pas…");
			else {
				System.out.println("Parcours en profondeur");
				for (Sommet s : Algorithme.parcoursEnProfondeur(this.graphe,
						sommet)) {
					System.out.print(s.getNom() + ", ");
				}
				System.out.println("FIN");
			}

			this.graphe.resetMarquage();
			break;

		case 5:
			System.out.println("Entrez le nom du sommet : ");
			nom_sommet = scanner.next();
			sommet = this.graphe.getSommetByNom(nom_sommet);

			if (sommet == null)
				System.err.println("Le sommet n'existe pas…");
			else {
				System.out.println("Parcours en largeur");
				for (Sommet s : Algorithme.parcoursEnLargeur(this.graphe,
						sommet)) {
					System.out.print(s.getNom() + ", ");
				}
				System.out.println("FIN");
			}

			this.graphe.resetMarquage();
			break;

		case 6:
			new Drawer(this.graphe);
			break;

		case 7:
			final Graphe g = new Graphe();

			for (Sommet s : this.graphe.getSommets())
				g.addSommet(s);

			for (Arete a : Algorithme.sollin(this.graphe))
				g.addArete(a);

			System.out.println(g.aretesToString());
			new Drawer(g);
			break;

		case 8:
			System.out.println("Entrez le nom du sommet : ");
			nom_sommet = scanner.next();
			sommet = this.graphe.getSommetByNom(nom_sommet);

			if (sommet == null)
				System.err.println("Le sommet n'existe pas…");
			else
				Algorithme.dijkstra(this.graphe, sommet);
			break;

		default:
			System.err.println("Choix non reconnu");
			break;
		}
	}

	private void initGraphe() {
		final Sommet A = new Sommet("A");
		final Sommet B = new Sommet("B");
		final Sommet C = new Sommet("C");
		final Sommet D = new Sommet("D");
		final Sommet E = new Sommet("E");
		final Sommet F = new Sommet("F");
		final Sommet G = new Sommet("G");
		final Sommet H = new Sommet("H");
		final Sommet I = new Sommet("I");
		final Sommet J = new Sommet("J");

		graphe.addSommet(A);
		graphe.addSommet(B);
		graphe.addSommet(C);
		graphe.addSommet(D);
		graphe.addSommet(E);
		graphe.addSommet(F);
		graphe.addSommet(G);
		graphe.addSommet(H);
		graphe.addSommet(I);
		graphe.addSommet(J);

		graphe.addArete(new Arete(A, B, 6));
		graphe.addArete(new Arete(A, C, 7));
		graphe.addArete(new Arete(B, E, 5));
		graphe.addArete(new Arete(F, B, 4));
		graphe.addArete(new Arete(D, C, 2));
		graphe.addArete(new Arete(C, J, 2));
		graphe.addArete(new Arete(D, F, 3));
		graphe.addArete(new Arete(F, H, 8));
		graphe.addArete(new Arete(G, H, 3));
		graphe.addArete(new Arete(J, G, 3));
		graphe.addArete(new Arete(I, G, 6));
		graphe.addArete(new Arete(I, J, 2));
	}

	public static void main(String[] args) {
		new Main();
	}
}
