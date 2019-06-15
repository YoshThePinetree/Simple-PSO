// Main function
public class PSOmain {
	public static void main (String arg[]) {
		System.out.println("PSO: Particle Swarm Optimization");
		
		// Initial configuration
		int fnum=5;			// the function number to solve
		int trial=20;		// the number of trials with different random initial
		int ite=300;		// the number of iterations for a trial
		int pop=50;			// the number of population
		double w=0.729;		// the PSO inertia coefficient
		double c1=1.49445;	// the PSO personal coefficient
		double c2=1.49445;	// the PSO global coefficient
		
		int PG[]= {fnum,trial,ite,pop};
		double PP[]= {w,c1,c2};
		
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
		
	}
}
