// Main function
import java.util.Random;

public class PSOmain {
	public static void main (String arg[]) {
		System.out.println("PSO: Particle Swarm Optimization");
		
		///////////////////////////
		// Initial configuration //
		///////////////////////////
		
		int fnum=2;			// the function number to solve
		int trial=20;		// the number of trials with different random initial
		int ite=1000;		// the number of iterations for a trial
		int pop=100;			// the number of particles
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
		
		double ans=ObjFunc.EvalFunc(0, 0, fnum);
		System.out.printf("%f\n",ans);
		
		///////////////////////////////////////////////
		// Particle Generation & Initial Evaluation  //
		///////////////////////////////////////////////
		Sfmt rnd = new Sfmt(rseed);
		double[][] X = new double[pop][2];	// the particle position matrix
		double[][] V = new double[pop][2];	// the particle velocity matrix
		double[] F = new double[pop];		// the objective function for the personal best
		double[] Fpb = new double[pop];		// the current objective function
		double[][] Pb = new double[pop][2];		// the personal best vector
		double[] Gb = new double[2];		// the global best vector
		double[] xylim=ObjFunc.GetLimit(fnum);
		
		double minfnc=1000;
		int minind=0;
		// Particle generation for their position and velocity
		
		for(int i=0;i<pop;i++) {
			for(int j=0;j<2;j++) {
				if(j==0) {
					X[i][j]=rnd.NextUnif()*(Math.abs(xylim[0])+Math.abs(xylim[1]))-Math.abs(xylim[0]);
				}
				if(j==1) {
					X[i][j]=rnd.NextUnif()*(Math.abs(xylim[2])+Math.abs(xylim[3]))-Math.abs(xylim[2]);
				}
				V[i][j]=rnd.NextUnif();
//				System.out.printf("%f\t",X[i][j]);
//				if(j==1) {
//					System.out.printf("\n");
//				}
			}
			F[i]=ObjFunc.EvalFunc(X[i][0], X[i][1], fnum);	// particle evaluation
//			System.out.printf("%f\n",F[i]);
			if(minfnc>F[i]) {
				minfnc=F[i];
				minind=i;
			}
		}

		Pb=X;
		Fpb=F;
		for(int j=0; j<2; j++) {
			Gb[j]=X[minind][j];
//			System.out.printf("%f\n",Gb[j]);
		}
		

		///////////////////////////
		// PSO solution search   //
		///////////////////////////
		
		for(int k=0; k<ite; k++) {
			System.out.printf("Iteration %d\n",k+1);
			// the position update
			double[][] X1 = new double[pop][2];	// the particle position matrix
			double[][] V1 = new double[pop][2];	// the particle velocity matrix
			double r1, r2;
			
			for(int i=0;i<pop;i++) {
				for(int j=0;j<2;j++) {
					X1[i][j]=X[i][j] + V[i][j];	// position update
					//System.out.printf("%f\t",X1[i][j]);
					//if(j==1) {
					//	System.out.printf("\n");
					//}
					r1=rnd.NextUnif();
					r2=rnd.NextUnif();
					V1[i][j]=w*V[i][j] + c1*r1*(Pb[i][j]-X[i][j]) + c2*r2*(Gb[j]-X[i][j]);	// velocity update
					
					if(V1[i][j]>vlim) {	// velocity limitation
						V1[i][j]=vlim;
					}
				}
				
				F[i]=ObjFunc.EvalFunc(X1[i][0], X1[i][1], fnum);	// particle evaluation
//				System.out.printf("%f\n",F[i]);
				
				if(minfnc>F[i]) {	// the global best update
					minfnc=F[i];
					minind=i;
					for(int j=0;j<2;j++) {
						Gb[j]=X1[i][j];
					}
				}
				if(Fpb[i]>F[i]) {	// the personal best update
					Fpb[i]=F[i];
					for(int j=0;j<2;j++) {
						Pb[i][j]=X1[i][j];
					}
				}
			}
			
			X=X1;
			V=V1;
			
			System.out.printf("The best fitness: \t");
			System.out.printf("%f\n",minfnc);
		}
		
	}
}
