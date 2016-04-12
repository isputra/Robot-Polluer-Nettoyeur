import java.util.ArrayList;

public class RobotPollueurToutDroit extends Robot {

	public RobotPollueurToutDroit(int ColDepart, Monde monde){
		super(ColDepart, 0, monde);
	}
	public RobotPollueurToutDroit(Monde monde){
		//super(9, 0, monde, 4);
		super((int)(Math.random()*10), 0, monde, 4);
	}

	public void parcourir() {
		this.setChemin(new ArrayList<Chemin>());
		for(int j=this.getPosY(); j<this.getM().getNbl(); j++) {
			//this.deplacer(i,this.getPosY());
			Chemin c = new Chemin(this.getPosX(),j);
			this.getChemin().add(c);
			System.out.println("Polluer PosY: "+j);
			//this.getM().setMatTrue(i, this.getPosY());
		}
	}
	public void setMat() {
		try {
			this.getM().setMatTrue(this.getPosX(), this.getPosY());
		} catch (ErrDimension e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}