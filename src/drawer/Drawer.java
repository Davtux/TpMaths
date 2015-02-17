package drawer;

import graphe.Algorithme;
import graphe.Arete;
import graphe.Graphe;
import graphe.Sommet;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

public class Drawer extends JFrame {
	private static final int WINDOW_HEIGTH = 800;
	private static final int WINDOW_WIDTH = 700;
	private static final long serialVersionUID = 4996533613474318134L;
	private final Graphe graphe;

	public Drawer(Graphe graphe) {
		super("Math : graphe");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.graphe = graphe;

		this.setSize(WINDOW_WIDTH, WINDOW_HEIGTH);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D graphics2d = (Graphics2D) g;

		// draw the graph
		double x = WINDOW_WIDTH / 2;
		double y = WINDOW_HEIGTH / 15;
		List<Sommet> sommets = Algorithme.parcoursEnLargeur(graphe,
				graphe.getSommets()[0]);
		Map<Sommet, Point2D.Double> painted_sommets = new HashMap<Sommet, Point2D.Double>();

		for (int i = 0; i < sommets.size(); i++) {
			Sommet sommet = sommets.get(i);
			Point2D.Double point_base;
			if (!painted_sommets.containsKey(sommet)) {
				point_base = new Point2D.Double(x, y);
				graphics2d.drawString(sommet.getNom(), (int) point_base.x,
						(int) point_base.y);
				painted_sommets.put(sommet, point_base);
				y += (WINDOW_HEIGTH / 15);
			} else {
				point_base = painted_sommets.get(sommet);
			}

			List<Sommet> voisins = graphe.getVoisinBySommet(sommet);
			for (int j = 0; j < voisins.size(); j++) {
				if (painted_sommets.containsKey(voisins.get(j)))
					voisins.remove(j);
			}

			x = WINDOW_WIDTH / ((voisins.size() + 1) * 2);

			for (Sommet voisin : voisins) {
				Point2D.Double point;

				if (painted_sommets.containsKey(voisin)) {
					point = painted_sommets.get(voisin);
				} else {
					point = new Point2D.Double(x, y);
					painted_sommets.put(voisin, point);
				}
				graphics2d.drawString(voisin.getNom(), (int) point.x,
						(int) point.y);
				Line2D.Double line = new Line2D.Double(point_base, point);
				graphics2d.draw(line);
				for (Arete a : graphe.getAreteBySommet(sommet)) {
					graphics2d.drawString(String.valueOf(a.getValeur()),
							(int) ((line.x1 + line.x2 + 20) / 2),
							(int) ((line.y1 + line.y2 + 20) / 2));
					break;
				}
				x += (WINDOW_WIDTH / voisins.size());
			}

			x = WINDOW_WIDTH / 2;
			y += (WINDOW_HEIGTH / 15);
		}
	}
}
