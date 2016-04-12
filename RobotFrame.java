
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class RobotFrame extends JFrame implements ActionListener, MouseListener {
	private JButton boutonNettoyer, boutonPolluer, boutonReinitialiser, boutonQuitter;
	private JMenuBar menubarRobot;
	private JMenu menuJouer, menuAide, miAjouteRobotNett, miAjouteRobotPoll;
	private JMenuItem miNouveauMonde, miQuitter;
	private JMenuItem miAjouteRobotNett1, miAjouteRobotNett2, miAjouteRobotNett3;
	private JMenuItem miAjouteRobotPoll1, miAjouteRobotPoll2, miAjouteRobotPoll3;
	private JPanel panelMonde, panelNavNet, panelNavPol;
	private JPanel[][] panelCase;
	
	Graphics2D g2d;
	Timer timer;
	int indexRobot=0;
	private boolean active=true;
	
	private Monde monde;
	private List<Robot> listeRobot = new ArrayList<Robot>();
    
	public RobotFrame() {
		initUI();
		}
	
	public final void initUI() {
		//mainFrame = new JFrame();
		setTitle("Robot");
		setLayout(new BorderLayout());
		setSize (850, 600);
		
		initMenuUI();
	 
    	monde = new Monde();
    	panelMonde = new JPanel(new GridLayout(10,10));
		panelCase = new JPanel[10][10];
		for(int j=0; j< 10; j++) {
			for(int i=0; i<10; i++) {
				panelCase[i][j] = new JPanel();
				panelCase[i][j].setBackground(Color.GRAY);
				panelCase[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				panelCase[i][j].addMouseListener(this);
				panelCase[i][j].setSize(50, 50);
				panelMonde.add(panelCase[i][j]);
			}
		}
		add(panelMonde, BorderLayout.CENTER);
	 
		panelNavNet = new JPanel(new BorderLayout());
		panelNavNet.setLayout(new BoxLayout(panelNavNet, BoxLayout.Y_AXIS));
		panelNavNet.setSize(100, 100);
		panelNavPol = new JPanel(new BorderLayout());
		panelNavPol.setLayout(new BoxLayout(panelNavPol, BoxLayout.Y_AXIS));
		panelNavPol.setSize(100, 100);

		JLabel labelNavNet = new JLabel("  Liste de Robot Nettoyeur  ");
		panelNavNet.add(labelNavNet);
		JLabel labelNavPol = new JLabel("  Liste de Robot Pollueur   ");
		panelNavPol.add(labelNavPol);
		
		add(panelNavNet, BorderLayout.WEST);
		add(panelNavPol, BorderLayout.EAST);
		
		//pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
    }	
	
	public void initMenuUI() {
		menubarRobot = new JMenuBar();
		menuJouer = new JMenu("Jouer");
		menuAide = new JMenu("Aide");
		
		miNouveauMonde = new JMenuItem("Nouveau Monde");
		miAjouteRobotNett = new JMenu("Robot Nettoyeur");
		miAjouteRobotPoll = new JMenu("Robot Polluer");
		miAjouteRobotNett1 = new JMenuItem("Nettoyeur Mario");
		miAjouteRobotNett2 = new JMenuItem("Nettoyeur Sonic");
		miAjouteRobotNett3 = new JMenuItem("Nettoyeur 3");
		miAjouteRobotNett3.setVisible(false);
		miAjouteRobotPoll1 = new JMenuItem("Polluer Champignon");
		miAjouteRobotPoll2 = new JMenuItem("Polluer Dr Eggman");
		miAjouteRobotPoll3 = new JMenuItem("Polluer 3");
		miAjouteRobotPoll3.setVisible(false);
		miQuitter = new JMenuItem("Quitter");
		

		miNouveauMonde.addActionListener(this);
		miAjouteRobotNett1.addActionListener(this);
		miAjouteRobotNett2.addActionListener(this);
		miAjouteRobotNett3.addActionListener(this);
		miAjouteRobotPoll1.addActionListener(this);
		miAjouteRobotPoll2.addActionListener(this);
		miAjouteRobotPoll3.addActionListener(this);
		miQuitter.addActionListener(this);
		
		miAjouteRobotNett.add(miAjouteRobotNett1);
		miAjouteRobotNett.add(miAjouteRobotNett2);
		miAjouteRobotNett.add(miAjouteRobotNett3);
		miAjouteRobotPoll.add(miAjouteRobotPoll1);
		miAjouteRobotPoll.add(miAjouteRobotPoll2);
		miAjouteRobotPoll.add(miAjouteRobotPoll3);
		miAjouteRobotNett.setEnabled(false);
		miAjouteRobotPoll.setEnabled(false);

		menuJouer.add(miNouveauMonde);
		menuJouer.addSeparator();
		menuJouer.add(miAjouteRobotNett);
		menuJouer.add(miAjouteRobotPoll);
		menuJouer.addSeparator();
		menuJouer.add(miQuitter);
		
		menubarRobot.add(menuJouer);
		menubarRobot.add(menuAide);
		
		setJMenuBar(menubarRobot);
	}
	

    public void initMonde() {  	
		for(int j=0; j< 10; j++) {
			for(int i=0; i<10; i++) {
				try {
					if (monde.getMatVal(i, j)) {
						panelCase[i][j].setBackground(Color.RED);	
						}
					else {
						panelCase[i][j].setBackground(Color.CYAN);	
						}
						
					panelCase[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
					//panelMonde.add(panelCase[i][j]);
					//System.out.println("["+i+"]["+j+"] panel case ("+panelCase[i][j].getX()+
					//		","+panelCase[i][j].getY()+")");
					
				} catch (ErrDimension e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//updatePanels();
		
	}public void updateMonde() {
		for(int j=0; j< 10; j++) {
			for(int i=0; i<10; i++) {
				try {
					if (monde.getMatVal(i, j)) {
						panelCase[i][j].setBackground(Color.RED);	
						}
					else {
						panelCase[i][j].setBackground(Color.CYAN);	
						}
						
					panelCase[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
					//panelMonde.add(panelCase[i][j]);
					
				} catch (ErrDimension e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		for(Robot robot : listeRobot) {
			if(robot.getIsSelected()){
				panelCase[robot.getPosX()][robot.getPosY()].setBackground(Color.ORANGE);
				System.out.println("panel case robot ");
			}
		}
		updatePanels();
	}
	
	public void updatePanels() {
		panelMonde.revalidate();
		panelMonde.repaint();
		panelNavNet.revalidate();
		panelNavNet.repaint();
		panelNavPol.revalidate();
		panelNavPol.repaint();
	}
	
	public void createRobot(int type, String image) {
		Robot robot;
		JButton bouton;
		JLabel label;
		switch (type){
			case 1:
				robot = new RobotNettoyeur(monde);
				listeRobot.add(robot);
				bouton = new JButton ("Mario") ;
				label = new JLabel();
				break;
			case 4:
				robot = new RobotPollueurToutDroit(monde);
				listeRobot.add(robot);
				bouton = new JButton ("Champignon") ;
				label = new JLabel();
				break;
			case 2:
				robot = new RobotNettoyeurSmart(monde);
				listeRobot.add(robot);
				bouton = new JButton ("Sonic") ;
				label = new JLabel();
				break;
			case 5:
				robot = new RobotPollueurSauteur(monde);
				listeRobot.add(robot);
				bouton = new JButton ("Dr Eggman") ;
				label = new JLabel();
				break;
/*			case 3:
				robotPol1 = new RobotpolluerToutDroit(monde);
				break;
			case 6:
				robotPol1 = new RobotpolluerToutDroit(monde);
				break;*/
			default:
				robot = null;
				bouton = null;
				label = null;
				break;
		}
		int i = robot.getPosX();
		int j = robot.getPosY();
		robot.setImageIcon(new ImageIcon("img/"+image));
		robot.setImage(robot.getImageIcon().getImage());
		robot.setCoorX(panelCase[i][j].getX());
		robot.setCoorY(panelCase[i][j].getY());
		robot.setButtonParcourir(bouton); //
		robot.setLabelImage(label); //
		robot.setType(type);
		//label.setVisible(false);
		label.setIcon(robot.getImageIcon());
		panelCase[i][j].add(label);
		System.out.println("i: "+i+" j: "+j);
		System.out.println("XRN: "+robot.getCoorX()+" YRN: "+robot.getCoorY());
		System.out.println("XPC: "+panelCase[i][j].getX()+" YPC: "+panelCase[i][j].getY());
		bouton.addActionListener(this);
		if(type>3)
			panelNavPol.add(bouton);
		else
			panelNavNet.add(bouton);
		label.addMouseListener(this);
		repaint();
		updatePanels();
	}
	
	public void parcourirRobot(Robot robot){
		System.out.println("start Robot Parcourir!");
		robot.setIsSelected(false);
		if(robot.getIsAction()){
			robot.parcourir();
			//robot.setMat();
		}
		else
			robot.deplacerVers();
		robot.setIndexChemin(0);
		robot.setIsMoving(true);
		//labels[robot.getType()].setVisible(false);
		robot.getLabelImage().setVisible(false);
		
		timer = new Timer(60, paintTimerRobot);
		timer.start();
		System.out.println("Stop moving!");
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g2d = (Graphics2D) g;
		for(Robot robot : listeRobot) {
			if(robot!=null&& robot.getIsMoving()) {
				g2d.drawImage(robot.getImage(), 
					robot.getCoorX()+panelMonde.getX(), 
					robot.getCoorY()+panelMonde.getY()+robot.getImageIcon().getIconHeight(), 
					panelMonde); //Draws the ball Image at the correct X and Y co-ordinates.									
			System.out.println("repaint Robot RX: "+robot.getCoorX()+" RY: "+robot.getCoorY());
			}
		}
		Toolkit.getDefaultToolkit().sync(); // necessary for linux users to draw  and animate image correctly
		g.dispose();
	}

	Action paintTimerRobot = new AbstractAction() { // functionality of our timer:
		public void actionPerformed(ActionEvent e) {
			for(Robot robot : listeRobot) {
				if(robot!=null && robot.getIsMoving()) {
					robotDeplacer(robot);
				}
			}
		}
	};
	
	public void robotDeplacer(Robot robot) {
		updateMonde();
		active=false;
		if(robot.getIsAction()) robot.setMat();
		if(robot.getChemin().size()>1) {
			System.out.println("getChemin : "+robot.getIndexChemin());
			Chemin c1 = robot.getChemin().get(robot.getIndexChemin());
			Chemin c2 = robot.getChemin().get(robot.getIndexChemin()+1);
			System.out.println("["+c1.getI()+"]["+c1.getJ()+"] -> ("+panelCase[c1.getI()][c1.getJ()].getX()+","+panelCase[c1.getI()][c1.getJ()].getY()+")");	
			System.out.println("["+c2.getI()+"]["+c2.getJ()+"] -> ("+panelCase[c2.getI()][c2.getJ()].getX()+","+panelCase[c2.getI()][c2.getJ()].getY()+")");
			
			int distx = (panelCase[c2.getI()][c2.getJ()].getX() - panelCase[c1.getI()][c1.getJ()].getX())/5;
			int disty = (panelCase[c2.getI()][c2.getJ()].getY() - panelCase[c1.getI()][c1.getJ()].getY())/5;
			System.out.println("pos x: "+Math.abs(robot.getCoorX2() - robot.getCoorX())+" pos y: "+Math.abs(robot.getCoorY2() - robot.getCoorY()));
			System.out.println("Distance x: "+distx+" Distance y: "+disty);
			robot.setCoorX2(panelCase[c2.getI()][c2.getJ()].getX());
			robot.setCoorY2(panelCase[c2.getI()][c2.getJ()].getY());
			System.out.println("getCoorX: "+robot.getCoorX()+" getCoorY: "+robot.getCoorY());
			System.out.println("getCoorX2: "+robot.getCoorX2()+" getCoorY2: "+robot.getCoorY2());
			
			if(Math.abs(robot.getCoorX2() - robot.getCoorX()) > Math.abs(distx) || 
					Math.abs(robot.getCoorY2() - robot.getCoorY()) > Math.abs(disty) ){
				//System.out.println("repaint x1 : "+robot.getCoorX()+" y1 : "+robot.getCoorY());
				//System.out.println("repaint x2 : "+robot.getCoorX2()+" y2 : "+robot.getCoorY2());
				if(Math.abs(robot.getCoorX2() - robot.getCoorX()) > Math.abs(distx))
					robot.setCoorX(robot.getCoorX() + distx);
				if(Math.abs(robot.getCoorY2() - robot.getCoorY()) > Math.abs(disty))
					robot.setCoorY(robot.getCoorY() + disty);
				repaint();

			}
			else {
				System.out.println("Type: "+robot.getType());
				panelCase[c1.getI()][c1.getJ()].remove(robot.getLabelImage());
				panelCase[c2.getI()][c2.getJ()].add(robot.getLabelImage());
				robot.getLabelImage().setVisible(false);

				robot.setCoorX(robot.getCoorX2());
				robot.setCoorY(robot.getCoorY2());
				try {
					robot.changerPosition(c2.getI(), c2.getJ());
					if(robot.getIsAction())
						robot.setMat();
				} catch (ErrDimension e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				repaint();
				//updateMonde();
				robot.setIndexChemin(robot.getIndexChemin()+1);
				System.out.println("arrive");
			}
		}
		if(robot.getIndexChemin() == robot.getChemin().size()-1) {
			//labels[robot.getType()].setVisible(true);
			robot.getLabelImage().setVisible(true);
			robot.setIsMoving(false);
			robot.setIsAction(false);
			//repaint();
			indexRobot=0;
			updateMonde();
			active=true;
			timer.stop();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RobotFrame ex = new RobotFrame();
		ex.setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		if(active) {
			if (e.getSource() == boutonNettoyer )
				System.out.println("Nettoyer!");
			if (e.getSource() == boutonPolluer )
				System.out.println("Polluer!");
			if (e.getSource() == boutonReinitialiser )
				System.out.println("Réinitialiser!");
			if (e.getSource() == boutonQuitter )
				System.out.println("Quitter!");
			if (e.getSource() == miNouveauMonde ) {
				//monde = new Monde();
				initMonde();
				miAjouteRobotNett.setEnabled(true);
				miAjouteRobotPoll.setEnabled(true);
				panelMonde.setDoubleBuffered(true);
				System.out.println("Nouveau Monde!");
			}
			if (e.getSource() == miAjouteRobotNett1 ) {
				createRobot(1,"mario.gif");
				System.out.println("Ajoute Robot Nett 1!");
			}
			if (e.getSource() == miAjouteRobotNett2 ) {
				createRobot(2,"sonic.gif");
				System.out.println("Ajoute Robot Nett 2!");
			}
			if (e.getSource() == miAjouteRobotPoll1 ) {
				createRobot(4,"mushroom.gif");
				System.out.println("Ajoute Robot Poll 1!");
			}
			if (e.getSource() == miAjouteRobotPoll2 ) {
				createRobot(5,"eggman.gif");
				System.out.println("Ajoute Robot Saut 2!");
			}
			for(Robot robot : listeRobot){
				if (e.getSource() == robot.getButtonParcourir() ) {
					robot.setIsAction(true);
					parcourirRobot(robot);
					System.out.println("Parcourir Robot liste!");
				}							
			}
		}
	}
	
    public void mouseClicked(MouseEvent me) {
		if(active) {
			for(Robot robot : listeRobot) {
				if (robot!=null) robot.setIsSelected(false);
				
				if (me.getSource() == robot.getLabelImage() ) {
					System.out.println("Image Icon Label !");
					System.out.println("Position i: "+ robot.getPosX() +" j: "+robot.getPosY());
					//panelCase[robotNet1.getPosX()][robotNet1.getPosY()].setBackground(Color.ORANGE);	
					if(indexRobot!=robot.numero) {
						indexRobot=robot.numero;
						robot.setIsSelected(true);
					}
					else {
						indexRobot=0;
						robot.setIsSelected(false);
						//updateMonde();	
					}
					updateMonde();
				}
		    	
			}
			
			for(int i=0; i< 10; i++) {
				for(int j=0; j<10; j++) {
					if (me.getSource() == panelCase[i][j] ) {
						System.out.println("Panel Case clicked, i: "+i+" j: "+j);
						if(indexRobot>0){
							Robot robot = listeRobot.get(indexRobot-1);
							robot.setPosX2(i);
							robot.setPosY2(j);
							robot.setIsAction(false);
							//robotNet1.setIsSelected(false);
							parcourirRobot(robot);
						}
					}
				}
			}
		}
    }
    
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
