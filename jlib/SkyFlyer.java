// Note in this Applet, you extend a JApplet, define a Panel (AppPanel), and draw on it.  It's not using the paint() method of JApplet.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SkyFlyer extends JApplet implements KeyListener {
	private AppPanel drawingArea;
	private JPanel startScreen;
	private JButton startButton;
	private int width = 500, height = 300, airplanex = 125, airplaney = 150;
	private NordburgSprite airplane;
	private boolean won = false;
	private boolean playing = false;
	private Container cp;

	public void init () {
		Container c = getContentPane();
		setMyContentPane(c);
		airplane = new NordburgSprite ("airplane.png", airplanex, airplaney, this);
		airplane.setInc(5);
		System.out.println ("airplane height: " + airplane.getHeight());
		airplane.setMaxY(height - airplane.getHeight());
		airplane.setMaxX(width - airplane.getWidth());
		System.out.println ("MaxY: " + airplane.getMaxY());
		System.out.println ("MaxX: " + airplane.getMaxX());
		airplane.setY(airplane.getMaxY());
		System.out.println ("airplaney: " + airplane.getY());

		startScreen = new JPanel();
		startScreen.setBackground(Color.GRAY);

		startButton = new JButton ("Start SkyFlyer");
		startScreen.add(startButton);

		startButton.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println ("Gotta figure out what to do here.");
					startGame();
				}
			}		
		);

		//drawingArea = new AppPanel (width, height, this);
		//drawingArea.setBackground(Color.BLUE);
		//drawingArea.addItem(airplane);
		//drawingArea.addKeyListener(this);

		c.add(startScreen, BorderLayout.CENTER);
		//c.add(drawingArea, BorderLayout.CENTER);
		/*if (c.isFocusable()) {
			addKeyListener(this);
			System.out.println ("Can set focus.");
		} else {
			System.out.println ("Can't set focus.");
		}*/
		System.out.println ("Exiting init....");

	} // End of init


	public static void main (String args[]) {
		/*
		 * Create a JFrame
		 * Create a SkyFlyer Object.
		 * Run init() and start()
		 * Add the SkyFlyer Object to the JFrame.
		 * Make the JFrame visible.
		 */
		JFrame applicationWindow = new JFrame ("Sky Flyer.");
		System.out.println ("main::Initial preferred size: " + applicationWindow.getPreferredSize());

		applicationWindow.addWindowListener (new WindowAdapter() {
			public void windowClosing (WindowEvent e) {
				System.exit(0);
			}
		}
		);


		SkyFlyer myObj = new SkyFlyer();
		myObj.setWidth(300);
		myObj.setHeight(200);

		myObj.init();
		myObj.start();

		applicationWindow.getContentPane().add(myObj);

		applicationWindow.setVisible(true);
		Insets nsts = applicationWindow.getInsets();
		applicationWindow.setSize(myObj.getWidth() + 2, myObj.getHeight() + nsts.top);
	} // End of main

	public void keyTyped (KeyEvent e) {
		//System.out.println ("KeyTyped.");
	} // End of keyTyped
	public void keyPressed (KeyEvent e) {
		System.out.println ("KeyPressed.");
		if (!won && playing) {
			handleKey(e.getKeyCode());
		} else {
			System.out.println ("keyPressed::LALALA I can't hear you!  You've already won!");
		}
		//System.out.println ("About to repaint.");
		drawingArea.repaint();
		//System.out.println ("Should have repainted.");
		//drawingArea.setCurrentChoice(1);
	} // End of keyPressed
	public void keyReleased (KeyEvent e) {
		//System.out.println ("KeyReleased.");
		//drawingArea.setCurrentChoice(0);
	} // End of keyReleased
	private void handleKey (int ck) {
		if (ck == 37) {
			airplane.moveleft();
		} else if (ck == 38) {
			airplane.moveUp();
			if (airplane.getY() <= airplane.getMinY()) {
				System.out.println ("handleKey::Setting won to true.");
				won = true;
			}
		} else if (ck == 39) {
			airplane.moveRight();
		} else if (ck == 40) {
			airplane.moveDown();
		} else {
			System.out.println ("handleKey::Nothing defined for key " + ck + ".");
		}
	}
	public int getWidth () {
		return width;
	} // End of getWidth
	public int getHeight () {
		return height;
	} // End of getHeight
	public void setWidth (int w) {
		width = w;
	} // End of setWidth
	public void setHeight (int h) {
		height = h;
	} // End of setHeight
	public void startGame () {
		won = false;
		playing = true;

		System.out.println ("startGame::Starting Sky Flyer.");
		Container c = getMyContentPane();

		drawingArea = new AppPanel (width, height, this);
		drawingArea.setBackground(Color.BLUE);
		drawingArea.addItem(airplane);

		c.add(drawingArea, BorderLayout.CENTER);
		c.remove(startScreen);
		//getContentPane().remove(startScreen);
		//getContentPane().add(drawingArea, BorderLayout.CENTER);
		drawingArea.revalidate();
		drawingArea.repaint();
		if (drawingArea.isFocusable()) {
			drawingArea.setFocusable(true);
			drawingArea.addKeyListener(this);
			System.out.println ("startGame::Can set focus.");
		} else {
			System.out.println ("startGame::Can't set focus.");
		}
		System.out.println ("startGame::Exiting startGame().");
	} // End of startGame
	public Container getMyContentPane() {
		return cp;
	} // End of getMyContentPane
	public void setMyContentPane (Container c) {
		cp = c;
	}
	//private class ButtonHandler implements ActionListener{
	//	public void actionPerformed (ActionEvent e) {
	//		for (int i = 0; i < choices.length; i++)
	//			if (e.getSource() == choices[i]) {
	//				//drawingArea.setCurrentChoice(i);
	//				break;
	//			}
	//	}
	//}
} // End of SkyFlyer
