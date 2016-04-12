import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public abstract class Robot {
	private int posx;
	private int posy;
	private int posx2;
	private int posy2;
	private int coorx;
	private int coory;
	private int coorx2;
	private int coory2;
	private int indexchemin;
	private boolean isMoving=false;
	private Monde m;
	private ImageIcon imageicon;
	private Image image;
	private List<Chemin> chemin;
	private int type;
	private boolean isAction=false;
	private boolean isSelected;
	private JButton buttonParcourir, buttonKill;
	private JLabel labelImage;
	private static int total=0;
	public int numero;

	public Robot(int x, int y) {
		this.posx = x;
		this.posy = y;
		this.m = new Monde();
		total++;
		numero=total;
	}
	public Robot(int x, int y, Monde m) {
		this.posx = x;
		this.posy = y;
		this.m = m;
		total++;
		numero=total;
	}
	
	public Robot(int x, int y, Monde m, int type) {
		this.posx = x;
		this.posy = y;
		this.m = m;
		this.type = type;
		total++;
		numero=total;
	}

	public Robot() {
		this.posx = (int)(Math.random()*10);
		this.posy = (int)(Math.random()*10);
		this.m = new Monde();
		total++;
		numero=total;
	}
	
	public Robot(Monde monde) {
		this.posx = (int)(Math.random()*10);
		this.posy = (int)(Math.random()*10);
		this.m = monde;
		total++;
		numero=total;
	}
	
	public Robot(Monde monde, int type) {
		this.posx = (int)(Math.random()*10);
		this.posy = (int)(Math.random()*10);
		this.m = monde;
		this.type = type;
		total++;
		numero=total;
	}

	public void changerPosition(int i, int j) throws ErrDimension {
		if((i>this.m.getNbl()) || (j>this.m.getNbc())) 
			throw new ErrDimension("Erreur de deplacement avec coor "+i+","+j);
		this.posx = i;
		this.posy = j;
	}
	
	public void deplacerVers() {
		setChemin(new ArrayList<Chemin>());
		Chemin c; 
		if(posx2!=posx){
			int pasx = (posx2-posx)/(Math.abs(posx2-posx)); 
			for(int i=posx; i!=posx2 ; i+=pasx) {
				c = new Chemin(i,posy);
				getChemin().add(c);
			}
		}

		//c = new Chemin(posx2,posy);
		//getChemin().add(c);
		
		if(posy2!=posy){
			int pasy = (posy2-posy)/(Math.abs(posy2-posy)); 
			for(int j=posy; j!=posy2; j+=pasy) {
				c = new Chemin(posx2,j);
				getChemin().add(c);
			}
		}
		c = new Chemin(posx2,posy2);
		getChemin().add(c);
		
		for(Chemin c1 : getChemin()){
			System.out.println("chemin x: "+c1.getI()+" j: "+c1.getJ());
		}

	}

	public Monde getM() {
		return this.m;
	}

	public int getPosX() {
		return this.posx;
	}

	public int getPosY() {
		return this.posy;
	}

	public int getPosX2() {
		return this.posx2;
	}

	public int getPosY2() {
		return this.posy2;
	}
	
	public void setPosX2(int x){
		this.posx2 = x;
	}
	public void setPosY2(int y){
		this.posy2 = y;
	}
	public void setCoorX(int x){
		this.coorx = x;
	}
	
	public int getCoorX() {
		return this.coorx;
	}
	
	public void setCoorY(int y){
		this.coory = y;
	}

	public int getCoorY() {
		return this.coory;
	}
	public void setCoorX2(int x){
		this.coorx2 = x;
	}
	
	public int getCoorX2() {
		return this.coorx2;
	}
	
	public void setCoorY2(int y){
		this.coory2 = y;
	}

	public int getCoorY2() {
		return this.coory2;
	}
	
	public void setIndexChemin(int i){
		this.indexchemin = i;
	}

	public int getIndexChemin() {
		return this.indexchemin;
	}
	
	public void setIsMoving(boolean b){
		this.isMoving = b;
	}

	public boolean getIsMoving() {
		return this.isMoving;
	}
	
	public void setImageIcon(ImageIcon imageicon) {
		this.imageicon = imageicon;
	}
	
	public ImageIcon getImageIcon() {
		return this.imageicon;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public Image getImage() {
		return this.image;
	}
	
	public void setChemin(List<Chemin> chemin) {
		this.chemin = chemin;
	}
	
	public List<Chemin> getChemin() {
		return this.chemin;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getType() {
		return this.type;
	}
	public void setIsAction(boolean b){
		this.isAction = b;
	}

	public boolean getIsAction() {
		return this.isAction;
	}
	
	public void setIsSelected(boolean b){
		this.isSelected = b;
	}

	public boolean getIsSelected() {
		return this.isSelected;
	}
	
	public void setButtonParcourir(JButton b){
		this.buttonParcourir = b;
	}

	public JButton getButtonParcourir() {
		return this.buttonParcourir;
	}
	
	public void setButtonKill(JButton b){
		this.buttonKill = b;
	}

	public JButton getButtonKill() {
		return this.buttonKill;
	}
	

	public void setLabelImage(JLabel b){
		this.labelImage = b;
	}

	public JLabel getLabelImage() {
		return this.labelImage;
	}
	
	
	public abstract void parcourir();
	
	public abstract void setMat();
}