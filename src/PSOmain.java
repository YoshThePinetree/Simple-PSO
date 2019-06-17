// Main function
import java.util.Random;

public class PSOmain {
	public static void main (String arg[]) {
		System.out.println("PSO: Particle Swarm Optimization");
		
		///////////////////////////
		// Initial configuration //
		///////////////////////////
		
		int fnum=1;			// the function number to solve
		int trial=20;		// the number of trials with different random initial
		int ite=300;		// the number of iterations for a trial
		int pop=50;			// the number of particles
		int rseed=1;
		double w=0.729;		// the PSO inertia coefficient
		double c1=1.49445;	// the PSO personal coefficient
		double c2=1.49445;	// the PSO global coefficient
		double vlim=4.0;	// the particle speed limitation
		
		int PG[]= {fnum,trial,ite,pop};
		double PP[]= {w,c1,c2,vlim};
		
		System.out.printf("General Parameters:\n");
		System.out.printf("trial\titeration\tparticles\n");
		System.out.printf("%d\t%d\t\t%d\n",trial,ite,pop);
		System.out.printf("w\tc1\tc2\n");
		System.out.printf("%f\t%f\t%f\n",w,c1,c2);
		
		ObjFunc ObjFuncObject = new ObjFunc();
		System.out.printf("Function:\n");
		ObjFunc.FuncName(fnum);
		
//		double ans=ObjFunc.EvalFunc(0, 0, fnum);
//		System.out.printf("%f",ans);
		
		///////////////////////////////////////////////
		// Particle Generation & Initial Evaluation  //
		///////////////////////////////////////////////
		Sfmt rnd = new Sfmt(rseed);
		double[][] X = new double[pop][2];
		double[][] V = new double[pop][2];
		double[] xylim=ObjFunc.GetLimit(fnum);
		// Particle generation for their position and verocity
		for(int i=0;i<pop;i++) {
			for(int j=0;j<2;j++) {
				if(j==0) {
					X[i][j]=rnd.NextUnif()*(Math.abs(xylim[0])+Math.abs(xylim[1]))-Math.abs(xylim[0]);
				}
				if(j==1) {
					X[i][j]=rnd.NextUnif()*(Math.abs(xylim[2])+Math.abs(xylim[3]))-Math.abs(xylim[2]);
				}
				V[i][j]=rnd.NextUnif();
				System.out.printf("%f\t",X[i][j]);
//				if(j==1) {
//					System.out.printf("\n");
//				}
			}
		}
		
		
	}
}
