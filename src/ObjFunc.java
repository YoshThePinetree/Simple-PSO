// Objective function class
public class ObjFunc {	
	// Method to evaluate the objective function
	public static double EvalFunc(double x, double y, int a){
		double f = 0;
		double st1=0,st2=0;
		
		switch(a) {		
		case 1:		//Rosenbrock function constrained with a cubic and a line
			st1 = Math.pow((x-1),3) - y + 1;
			st2 = x + y - 2;
		
			if(st1<=0 && st2<=0) {
				f = Math.pow((1-x),2) + 100*Math.pow((y-Math.pow(x, 2)),2);
			}else{
				f = 1000;
			}
			break;
			
		case 2:		// Rosenbrock function constrained to a disk
			st1 = x*x + y*y;
		
			if(st1<=2) {
				f = Math.pow((1-x),2) + 100*Math.pow((y-Math.pow(x, 2)),2);
			}else{
				f = 1000;
			}
			break;
					
		case 3:		// Mishra's Bird function - constrained
			st1 = Math.pow(x+5,2) + Math.pow(y+5,2);
			
			if(st1<25) {
				f = Math.sin(y)*Math.exp(Math.pow(1-Math.cos(x),2)) + Math.cos(x)*Math.exp(Math.pow(1-Math.sin(y),2)) + Math.pow(x-y,2);
			}else{
				f = 1000;
			}
			break;
			
		case 4:		// Simionescu function
			double rt=1, rs=0.2;
			int n=8;
			double u=Math.pow( rt + rs*Math.cos(n*Math.atan(x/y)), 2);
			st1 = x*x + y*y;
			
			if(st1<=u) {
				f = 0.1*x*y;
			}else{
				f = 1000;
			}
			break;
			
		case 5:		// 2-D Rastrigin function
			int A=10;
			f = A*2+ + (x*x - A*Math.cos(2*Math.PI*x)) + (y*y - A*Math.cos(2*Math.PI*y));
		}
		return f;
	}
	
	// Method to display
	public static void FuncName(int x){
		switch(x) {
		case 1:
			System.out.println("Rosenbrock function constrained with a cubic and a line");
			break;
		case 2:
			System.out.println("Rosenbrock function constrained to a disk");
			break;
		case 3:
			System.out.println("Mishra's Bird function - constrained");
			break;
		case 4:
			System.out.println("Simionescu function");
			break;
		case 5:
			System.out.println("2-D Rastrigin function");
			break;
		default :
			System.out.println("Error");
			break;
		}
	}
}
