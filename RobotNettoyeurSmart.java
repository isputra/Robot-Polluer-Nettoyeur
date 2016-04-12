import java.util.ArrayList;

public class RobotNettoyeurSmart extends Robot {

	public RobotNettoyeurSmart(Monde monde) {
		super(monde, 2);
	}

	@Override
	public void parcourir() {
		this.setChemin(new ArrayList<Chemin>());
		Chemin c = new Chemin(this.getPosX(),this.getPosY());
		this.getChemin().add(c);
		for(int j=0;j<this.getM().getNbl();j++) {
			for(int i=0;i<this.getM().getNbc();i++) {
				try {
					if(this.getM().getMatVal(i, j)) {
						c = new Chemin(i,j);
						this.getChemin().add(c);
						System.out.println("Sonic i:"+i+" j:"+j);
					}
				} catch (ErrDimension e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void setMat() {
		try {
			this.getM().setMatFalse(this.getPosX(), this.getPosY());
		} catch (ErrDimension e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
