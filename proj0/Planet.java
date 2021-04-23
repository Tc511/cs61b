public class Planet{ 

	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;

	public Planet(double xP, double yP, double xV,
	      double yV, double m, String img)
	{
	     xxPos = xP;
	     yyPos = yP;
	     xxVel = xV;
	     yyVel = yV;
	     mass = m;
	     imgFileName = img;
	}
	public Planet(Planet p)
	{
	     p.xxPos = xxPos;
             p.yyPos = yyPos;
             p.xxVel = xxVel;
             p.yyVel = yyVel;
             p.mass = mass;
             p.imgFileName = imgFileName;
	}

	public double calcDistance(Planet dis_p)
	{
		return Math.sqrt(Math.pow(this.xxPos-dis_p.xxPos,2) + Math.pow(this.yyPos-dis_p.yyPos,2));
	}
	
	public double calcForceExertedBy(Planet force_p)
	{
		double G = 6.67E-11;
		double r = this.calcDistance(force_p);
		double F = G*this.mass*force_p.mass/(r*r);
		return F;
	}

	public double calcForceExertedByX(Planet Fx_p)
	{
		double Fx = this.calcForceExertedBy(Fx_p)*(-this.xxPos + Fx_p.xxPos)/this.calcDistance(Fx_p);
		return Fx;
	}

	public double calcForceExertedByY(Planet Fy_p)
        {
                double Fy = this.calcForceExertedBy(Fy_p)*(-this.yyPos + Fy_p.yyPos)/this.calcDistance(Fy_p);
                return Fy;
        }
	
	public double calcNetForceExertedByX(Planet[] allPlanets)
	{
		double sumx = 0;
		for (Planet element : allPlanets)
			{
				if (element.equals(this))
				{
					continue;
				}
				else
				{sumx = sumx + this.calcForceExertedByX(element);
				}
			}
		return sumx;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets)
        {
                double sumy = 0;
                for (Planet element : allPlanets)
                        {
                                if (element.equals(this))
				{
					continue;
				}
				else
				{
				sumy = sumy + this.calcForceExertedByY(element);
                       
				}
			}
                return sumy;
        }


	public void update(double dt, double fX, double fY)
	{
		double anetx = fX/this.mass;
		double anety = fY/this.mass;
		this.xxVel = this.xxVel + dt*anetx;
		this.yyVel = this.yyVel + dt*anety;
		this.xxPos = this.xxPos + dt*this.xxVel;
		this.yyPos = this.yyPos + dt*this.yyVel;
		
	}	

	public void draw()
	{
		StdDraw.picture(this.xxPos,this.yyPos,"images/" + this.imgFileName);
	}
	

}



