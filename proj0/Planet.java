public class Planet{
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;

  public static final double G = 6.67e-11;

  public Planet(double xP, double yP, double xV,double yV, double m, String img){
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }
  public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
      }
  //
  public double calcDistance(Planet other){
    double differInSquare=(this.xxPos - other.xxPos)*(this.xxPos - other.xxPos)+(this.yyPos - other.yyPos)*(this.yyPos - other.yyPos);
    return Math.sqrt(differInSquare);
  }
 //
  public double calcForceExertedBy(Planet other){
    //double G = 6.67e-11;
    double Differ = this.calcDistance(other);
    return G*this.mass*other.mass/(Differ*Differ);
  }
  //
  public double calcForceExertedByX(Planet other){
    return (other.xxPos - this.xxPos) * this.calcForceExertedBy(other) / this.calcDistance(other);
  }
  public double calcForceExertedByY(Planet other){
    return (other.yyPos - this.yyPos) * this.calcForceExertedBy(other) / this.calcDistance(other);
  }
// somehow it already gave the equals method ,doesn't need to write it ourself
  // public boolean equals(Planet other){
  //  if(other == this){
  //    return true;
  //  }else { return false ;}
  // }

//My original way to use while plus for loop
  public double calcNetForceExertedByX(Planet[] allPlanets){
    int i = 0;
    double allForceX=0;
    while (i < allPlanets.length){
      if( !this.equals( allPlanets[i] )){
        allForceX += this.calcForceExertedByX(allPlanets[i]);
        i = i + 1;
    }else{i = i + 1;}
    }
    return allForceX;
  }

  public double calcNetForceExertedByY(Planet[] allPlanets){
    int i = 0;
    double allForceY=0;
    while (i < allPlanets.length){
      if( !this.equals( allPlanets[i] ) ){
        allForceY += this.calcForceExertedByY(allPlanets[i]);
        i = i + 1;
    }else{i = i + 1;}
    }
    return allForceY;
  }


  public void update(double dt, double fX, double fY){
    double accelerationX = fX / this.mass;
    double accelerationY = fY / this.mass;

    this.xxVel += dt * accelerationX;
    this.yyVel += dt * accelerationY;

    this.xxPos += dt * this.xxVel;
    this.yyPos += dt * this.yyVel;
  }
  public void draw(){
    StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
  }
}
