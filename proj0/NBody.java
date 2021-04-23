public class NBody{

	public static double readRadius(String file) {
		In in = new In(file);
		in.readInt();
		return in.readDouble();
	}

	
	public static Planet[] readPlanets(String file){
		In in = new In(file);
		int N = in.readInt();
		Planet[] p = new Planet[N];
		int i = 0;
			in.readDouble();
		for(i = 0; i < N; i++){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();	
			p[i] = new Planet(xxPos, yyPos, xxVel, yyVel,mass, imgFileName); 
	}
	return p;
}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		Planet[] p = readPlanets(filename);
		double radius = readRadius(filename);
		int N = p.length;

		String imageToDraw = "images/"+"starfield.jpg";
		
		StdDraw.setScale(-radius,radius);
		StdDraw.clear();
		StdDraw.picture(0,0,imageToDraw);

		for(Planet element : p){
			element.draw(); 
		}

		StdDraw.enableDoubleBuffering();

		for(double t = 0 ; t<T ; t = t + dt){
			double[] XForces = new double[N];
			double[] YForces = new double[N];

				for(int k = 0; k<N ; k ++){
					XForces[k] = p[k].calcNetForceExertedByX(p);
					YForces[k] = p[k].calcNetForceExertedByY(p);
				}

				for(int k = 0; k<N ; k ++){
					p[k].update(t,XForces[k],YForces[k]);
				}
					
				//	StdDraw.setScale(-radius,radius);
				//	StdDraw.clear();
					StdDraw.picture(0,0,imageToDraw);
					for(Planet element : p){
					element.draw(); 
					}
					StdDraw.show();
					//StdDraw.pause(10);
					
		}
			StdOut.printf("%d\n", p.length);
			StdOut.printf("%.2e\n", radius);
			for (int i = 0; i < p.length; i++) {
	    	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
	        p[i].xxPos, p[i].yyPos, p[i].xxVel,
	        p[i].yyVel, p[i].mass, p[i].imgFileName);
}
}
}


