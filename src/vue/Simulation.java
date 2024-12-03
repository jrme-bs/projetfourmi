package vue;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import etresVivants.Individu;
import fourmiliere.Fourmiliere;
import nicellipse.component.NiSpace;
import terrain.Terrain;
import proie.Proie;

public class Simulation {
	NiSpace space = new NiSpace("Simulation Fourmis", new Dimension(800, 800));
	NiSpace time = new NiSpace("Gestion temps", new Dimension(400, 100));
	Terrain terrain = new Terrain(new Point(10,10), new Dimension(700,700));
    final int niveau_fourmiliere = 1;
    final int niveau_individu = 2;
    final int niveau_proie = 3;
	Timer animation;
	float jours = 0;
	JLabel labelJours;
	
	public Simulation() {

		space.setDoubleBuffered(true);
		space.openInWindow();
		
		time.setLayout(new BoxLayout(time, BoxLayout.Y_AXIS));
		time.add(this.jourSlider());
		this.creerJourCpt();
		time.add(labelJours);
		time.setDoubleBuffered(true);
		time.openInWindow();

		this.nouveauTerrain(terrain);
	}
	
	private void creerJourCpt() {
		labelJours = new JLabel("Jours pass?s :" + jours, JLabel.RIGHT);
		labelJours.setPreferredSize(new Dimension(250, 100));
		labelJours.setFont(new Font("Verdana", Font.PLAIN, 25));
	}
	
	private JPanel jourSlider() {		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel label = new JLabel("ms par journ?e:", JLabel.RIGHT);
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 1000, 500);

		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (!source.getValueIsAdjusting()) {
					int temps = (int) source.getValue();
					int newDelay = temps;
					animation.setDelay(newDelay);
					animation.setInitialDelay(500);
				}
			}
		});

		// Turn on labels at major tick marks.
		slider.setMajorTickSpacing(200);
		slider.setMinorTickSpacing(100);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		
		panel.add(label);
		panel.add(slider);
		return panel;
	}
	
	public void updateJours() {
		jours += 1;
		labelJours.setText("Jours pass?s :" + jours);
	}

	
	
	public Terrain getTerrain() {
		return this.terrain;
	}
	
	public void nouveauTerrain(Terrain terrain) {
		VueTerrain v = new VueTerrain(terrain);
		this.space.add(v);
		this.space.repaint();
	} 
	
	public void nouvelleFourmiliere(Fourmiliere fourmiliere) {
		VueFourmiliere v = new VueFourmiliere(fourmiliere);
		// Ajoute l'individu au dessus du terrain
		this.space.add(v,this.niveau_fourmiliere,0);
		this.space.repaint();
	}
	
	public void nouvelIndividu(Individu individu) {
		VueIndividu v = new VueIndividu(individu);
		// Ajoute l'individu au dessus de la fourmiliere
		this.space.add(v,this.niveau_individu,0);		
		this.space.repaint();
	}
	public void nouveauDeplacement(Point p) {
		VueDeplacement v = new VueDeplacement(p);
		this.space.add(v,4,0);
	}
	
	public void nouvelleProie(Proie proie)
	{
		VueProie v = new VueProie(proie);
		this.space.add(v,this.niveau_proie,0);
		this.space.repaint();
	}	public void startGraphicAnimation() {
		GraphicAnimation animation = new GraphicAnimation();
		animation.start();
	}

	
	class GraphicAnimation implements ActionListener {
		int graphicAnimationDelay = 500;
 		
		public void actionPerformed(ActionEvent e) {
			Component[] views = Simulation.this.space.getComponents();
			for (int i = 0; i < views.length; i++) {
				Component c = views[i];
				if (c instanceof VueElement) {
					VueElement next = (VueElement) c;
					next.mettreAJourVue();
				}
			}
			Simulation.this.updateJours();
			
			terrain.etapeDeSimulation(new ContexteDeSimulation(Simulation.this));
		}

		public void start() {
			animation = new Timer(0, this);
			animation.setDelay(this.graphicAnimationDelay);
			animation.start();
		}
	}
	
	public static void main(String args[]) {
		Simulation simulation = new Simulation();
		simulation.startGraphicAnimation();
	}
}
