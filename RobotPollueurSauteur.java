import java.util.ArrayList;

public class RobotPollueurSauteur extends Robot {

	public RobotPollueurSauteur(Monde monde) {
		super(monde, 5);
	}

//				

	@Override
	public void parcourir() {
		// TODO Auto-generated method stub
this.setChemin(new ArrayList<Chemin>());
		
		int startX=this.getPosX(), endX, pasX;
		int startY=this.getPosY(), endY, pasY;
		
		if(startX==this.getM().getNbc()-1) {
			endX = -1;
			pasX = -1;
		}
		else {
			endX = this.getM().getNbc();
			pasX = 1;
		}
		
		
		if(startY==this.getM().getNbl()-1) {
			endY = -1;
			pasY = -1;
		}
		else {
			endY = this.getM().getNbl();
			pasY = 1;		
		}
		for(int j=startY; j!=endY; j+=pasY) {
			for(int i=startX; i!=endX; i+=pasX) {
				if(probabilite_pourcent_true(30)){
					Chemin c = new Chemin(i,j);
					this.getChemin().add(c);
					//System.out.println("i:"+i+" j:"+j);
				}
			}
			pasX = pasX * (-1);
			if(pasX==-1) {
				startX=this.getM().getNbc()-1;
				endX=-1;
			}
			else {
				startX=0;
				endX=this.getM().getNbc();
			}
		}
	}
	
	private boolean probabilite_pourcent_true(int n) {
		if((int)(Math.random()*100)<n)
			return true;
		else
			return false;
	}

	@Override
	public void setMat() {
		try {
			this.getM().setMatTrue(this.getPosX(), this.getPosY());
		} catch (ErrDimension e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
