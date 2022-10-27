package hw2;
import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.StdRandom;

import static edu.princeton.cs.algs4.StdRandom.shuffle;
import static edu.princeton.cs.algs4.StdRandom.uniform;

public class PercolationStats {
    private double[] experminentCount;
    private int[] randomHelper;
    private int N;
    private int T;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf){
        this.N = N;
        this.T = T;
        randomHelper = new int[N * N];

        if (N <= 0 || T <= 0 ) {
            throw new java.lang.IllegalArgumentException("N or T out of bound");
        }

        experminentCount = new double[T];
        for(int j = 0; j < T; j++){
/*initialize randonHelper*/
            for(int k = 0; k < N * N; k ++) {
                randomHelper[k] = k;
            }

            shuffle(randomHelper);

            Percolation po = pf.make(N);// create percolation like homework sheet describe
            int count = 0;
            for(int i = 0; i < N * N; i ++){
                if(po.percolates()){
                    break;
                } else {
                    po.open(converNumber(randomHelper[count], N)[0], converNumber(randomHelper[count], N)[1]);
//                    System.out.print(converNumber(randomHelper[count], N)[0]);
//                    System.out.print(",");
//                    System.out.println(converNumber(randomHelper[count], N)[1]);
                    count ++;
                }
            }
            experminentCount[j] = count;
        }
    }

    //convert an int to coordinate
    private int[] converNumber(int rank, int size){
        int[] temp = new int[2];
        temp[0] = rank / size;
        temp[1] = rank % size;
        return temp;
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(this.experminentCount);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(this.experminentCount);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow(){
        return mean() - 1.96 * stddev()/(Math.sqrt(T));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh(){
        return mean() + 1.96 * stddev()/(Math.sqrt(T));
    }
//    public static void main(String[] args){
//        PercolationFactory m = new PercolationFactory();
//        PercolationStats p = new PercolationStats(20,100, m);
////        for(int i = 0; i < 20; i++) {
////            System.out.println(p.experminentCount[i]);
////        }
//        System.out.println(p.mean());
//        System.out.println(p.stddev());
//        System.out.println(p.confidenceLow());
//        System.out.println(p.confidenceHigh());


        //Test converNumber
//      System.out.println(converNumber(12,6)[0]);
//      System.out.println(converNumber(12,6)[1]);

        /* //test how to use shuffle
        int[] a = {1,2,3,4,5};
        shuffle(a);
        System.out.println(a[0]);
        System.out.println(a[1]);
        System.out.println(a[2]);
        System.out.println(a[3]);
        System.out.println(a[4]);
        */
//    }
}
