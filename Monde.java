
public class Monde {
	private int nbL;
	private int nbC;
	private boolean Mat[][];

	public Monde() {
		nbL=10;
		nbC=10;
		Mat = new boolean[nbL][nbC];
	}

	public Monde(int i, int j) {
		nbL=i;
		nbC=j;
		Mat = new boolean[nbL][nbC];
	}

	public void setMatTrue(int i, int j) throws ErrDimension {
		if((i>nbL) || (j>nbC) || (i<0) || (j<0))
			throw new ErrDimension("Erreur de construction avec coordonnees " + i + " " + j);
		Mat[i][j] = true;
	}

	public void setMatFalse(int i, int j) throws ErrDimension {
		if((i>nbL) || (j>nbC) || (i<0) || (j<0))
			throw new ErrDimension("Erreur de construction avec coordonnees " + i + " " + j);
		Mat[i][j] = false;
	}

	public boolean getMatVal(int i, int j) throws ErrDimension {
		if((i>nbL) || (j>nbC) || (i<0) || (j<0))
			throw new ErrDimension("Erreur de coordonnees demandés " + i + " " + j);
		return Mat[i][j];	
	}

	public int countPapierGras() {
		int count=0;
		for(int i=0; i<nbL; i++) {
			for(int j=0; j<nbC; j++)
				if(Mat[i][j])
					count+=1;
		}
		return count;
	}

	public void affiche() {
		for(int i=0; i<nbL; i++) {
			for(int j=0; j<nbC; j++)
				System.out.println("Mat["+i+"]["+j+"]:"+Mat[i][j]);
		}	
	}

	public void afficheGraphique() {
		for(int i=0; i<nbL; i++) {
			for(int j=0; j<nbC; j++) {
				if(Mat[i][j])
					System.out.print("  *");
				else 
					System.out.print("  .");
			}
			System.out.println("");
		}	
	}

	public int getNbl() {
		return nbL;
	}

	public int getNbc() {
		return nbC;
	}
}
