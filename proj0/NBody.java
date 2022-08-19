public class NBody{

  public static double readRadius(String path){
    In in = new In(path);
    double firstItem = in.readDouble();
    double secondItem = in.readDouble();
    return secondItem;
  }

  public static Planet[] readPlanets(String path){

    int i = 0;
    In in = new In(path);
    int firstItem = in.readInt();
    double secondItem = in.readDouble();
    Planet[] inputPlanets = new Planet[firstItem];
    while( i < firstItem ){

      double xxPos = in.readDouble();
      double yyPos = in.readDouble();
      double xxVel = in.readDouble();
      double yyVel = in.readDouble();
      double mass = in.readDouble();
      String imgFileName = in.readString();
      Planet singlePlanet = new Planet(xxPos, yyPos, xxVel, yyVel, mass ,imgFileName);

      inputPlanets[i] = singlePlanet;
      i += 1;
    }
    return inputPlanets;
  }

  public static void main(String[] args){
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    double universeRadius = NBody.readRadius(filename);
    Planet[] lotsPlanets = NBody.readPlanets(filename);

    StdDraw.setScale(-universeRadius, universeRadius);
    StdDraw.picture(0, 0, "images/starfield.jpg");
    StdDraw.show();
    StdDraw.pause(2000);

    for(Planet q : lotsPlanets){
    q.draw();
    }
    StdDraw.enableDoubleBuffering();

    for(double timeCount = 0; timeCount < T; timeCount += dt){
      double[] xForces = new double[lotsPlanets.length];
      double[] yForces = new double[lotsPlanets.length];

      for(int i = 0; i < lotsPlanets.length; i += 1){
      xForces[i] = lotsPlanets[i].calcNetForceExertedByX(lotsPlanets);
      yForces[i] = lotsPlanets[i].calcNetForceExertedByY(lotsPlanets);
      }

      for(Planet q : lotsPlanets){
        q.update(dt, q.calcNetForceExertedByX(lotsPlanets), q.calcNetForceExertedByY(lotsPlanets));
      }
      StdDraw.picture(0, 0, "images/starfield.jpg");
      for(Planet q : lotsPlanets){
      q.draw();
      }
      StdDraw.show();
      StdDraw.pause(10);
    }
    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    }
  }

}
