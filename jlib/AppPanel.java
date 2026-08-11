import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class AppPanel extends JPanel {
	private int width = 500, height = 300;
	private ImageObserver imgObs;
	private Vector sprites;

	public AppPanel (int w, int h) {
		width = (w >= 0 ? w : 100);
		height = (h >= 0 ? h : 100);
		sprites = new Vector();
	} // End of AppPanel Constructor
	public AppPanel (int w, int h, ImageObserver io) {
		this (w, h);
		imgObs = io;
		//System.out.println ("AppPanel::Constructor::(w, h, io).");
	} // End of second AppPanel Constructor
	public void paintComponent (Graphics g) {
		super.paintComponent (g);
		g.drawRect(1, 1, width-1, height-1);
		drawSprites (g);
	} // End of paintComponent

	public void setImageObserver (ImageObserver io) {
		imgObs = io;
	} // End of setImageObserver
	public ImageObserver getImageObserver () {
		return imgObs;
	} // End of getImageObserver 
	public void addItem (Object o) {
		sprites.addElement (o);
	} // End of addItem
	public void drawSprites (Graphics g) {
		//System.out.println ("AppPanel::drawSprites::Drawing Sprites.");
		Enumeration e = sprites.elements();
		while (e.hasMoreElements()) {
			Object o = e.nextElement();
			if (o instanceof NordburgSprite) {
				//System.out.println ("AppPanel::drawSprites::Drawing next sprite: " + o.toString());
				NordburgSprite ns = (NordburgSprite) o;
				g.drawImage (ns.getImage(), ns.getX(), ns.getY(), getImageObserver());
				//g.drawRect (ns.getX(), ns.getY(), ns.getWidth(), ns.getHeight());
				//g.drawLine (ns.getX(), ns.getY(), (ns.getX() + ns.getWidth()), (ns.getY() + ns.getHeight()));
				/*g.drawLine (ns.getX(), ns.getY(), ns.getX() + ns.getWidth(), ns.getY() + ns.getHeight());
				g.drawLine (ns.getX(), ns.getY(), ns.getX() + ns.getWidth(), ns.getY());
				g.drawLine (ns.getX(), ns.getY(), ns.getX(), ns.getY() + ns.getHeight());
				g.drawLine (ns.getX() + ns.getWidth(), ns.getY(), ns.getX() + ns.getWidth(), ns.getY() + ns.getHeight());
				g.drawLine (ns.getX(), ns.getY() + ns.getHeight(), ns.getX() + ns.getWidth(), ns.getY() + ns.getHeight());
				*/
			} // End of if
		} // End of while
		//repaint();
	} // End of drawSprites
} // End of AppPanel
