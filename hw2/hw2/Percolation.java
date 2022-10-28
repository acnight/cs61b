package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.HashSet;
import java.util.Set;


public class Percolation {
    // create N-by-N grid, with all sites initially blocked
    private int N;
    private Set<Integer> openSite = new HashSet<>();
    private WeightedQuickUnionUF fullSite;
    private WeightedQuickUnionUF singleVirtualSite;
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("N cant be under zero");
        }
        this.N = N;
        int size = N * N + 2;
        fullSite = new WeightedQuickUnionUF(size);
        singleVirtualSite = new WeightedQuickUnionUF(size - 1);
    }

    /* Return the number of a coordinate.*/
    private int repNumber(int row, int col) {
        return row * N + col;
    }

    /*Check will this item be filled after open(四周一圈的也是不同的)*/
    private boolean adjecent(int A) {
        if (A > N * N - N && A < N * N - 1) { // the down side
            return fullSite.connected(A - 1, N * N) || fullSite.connected(A + 1, N * N) || fullSite.connected(A - N, N * N);
        } else if (A == N * N - N) { //the down left corner
            return fullSite.connected(N * N - 2 * N, N * N) || fullSite.connected(N * N - N + 1, N * N);
        } else if (A == N * N - 1) { //the down right corner
            return fullSite.connected(N * N - 2, N * N) || fullSite.connected(N * N - 1 - N, N * N);
        } else if (A % N == 0 && A != 0 && A != N * N - N) { // the far left side situation
            return fullSite.connected(A - N, N * N) || fullSite.connected(A + N, N * N) || fullSite.connected(A + 1, N * N);
        } else if (A % N == 5 && A != N - 1 && A != N * N - 1) { // the far RIGHT side situation
            return fullSite.connected(A - N, N * N) || fullSite.connected(A + N, N * N) || fullSite.connected(A - 1, N * N);
        } else if (A >= 0 && A < N) { //the first row must be full
            return true;
        } else {
            return fullSite.connected(A - 1, N * N) || fullSite.connected(A + 1, N * N) || fullSite.connected(A + N, N * N) ||fullSite.connected(A - N, N * N);
        }
    }

    /*返回相邻的数字组成的数组*/
    private int[] nearBy(int N, int A) {
        if (A > N * N - N && A < N * N - 1) { // the down side
            int[] a = {A - 1, A + 1, A - N};
            return a;
        } else if (A == N * N - N) { //the down left corner
            int[] a = {N * N - 2 * N, N * N - N + 1};
            return a;
        } else if (A == N * N - 1) { //the down right corner
            int[] a = {N * N - 2, N * N - 1 - N};
            return a;
        } else if (A % N == 0 && A != 0 && A != N * N - N) { // the far left side situation
            int[] a = {A - N, A + N, A + 1};
            return a;
        } else if (A % N == 5 && A != N - 1 && A != N * N - 1) { // the far RIGHT side situation
            int[] a = {A - N, A + N, A - 1};
            return a;
        } else if (A > 0 && A < N - 1) { //the first row
            int[] a = {A - 1, A + 1, A + N};
            return a;
        } else if (A == 0) { //the first site
            int[] a = {1, N};
            return a;
        } else if (A == N - 1) { //the up right corner site
            int[] a = {2 * N -1, N - 2};
            return a;
        } else {
            int[] a = {A - 1, A + 1, A + N, A - N};
            return a;
        }

    }

    private void nearByOpenUnion(int N, int A) {
        int[] temp = nearBy(N, A);
        for (int i = 0; i < temp.length; i++) {
            if (isOpenSimp(temp[i])){
                fullSite.union(temp[i], A);
                singleVirtualSite.union(temp[i], A);
            }
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1  ) {
            throw new java.lang.IndexOutOfBoundsException("row or col out of bound");
        } else if (isOpen(row, col)){ //if it is already opened,then stop
            return;
        } else if(row == 0) {   /*如果open的第一行，open即fill*/
            fullSite.union(N * N, repNumber(row, col));
            singleVirtualSite.union(N * N, repNumber(row, col));
        } else if(row == N - 1) { /*如果open的最后一行，，open就与终点相连*/
            fullSite.union(N * N + 1, repNumber(row, col));
        }else if(N == 1) { /*特殊情况*/
            fullSite.union(N * N + 1, repNumber(row, col));
            fullSite.union(N * N, repNumber(row, col));
        }else if(isOpen(row, col)) { /*特殊情况*/
            return;
        }
        // 先加入open的set
        openSite.add(repNumber(row, col));
        // 与open的相互union
        nearByOpenUnion(N, repNumber(row, col));

    }

    private static int[] converNumber(int rank, int size){
        int[] temp = new int[2];
        temp[0] = rank / size;
        temp[1] = rank % size;
        return temp;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1  ) {
            throw new java.lang.IndexOutOfBoundsException("row or col out of bound");
        } else {
            return openSite.contains(repNumber(row, col));
        }
    }

    private boolean isOpenSimp(int A) {
            return openSite.contains(A);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1  ) {
            throw new java.lang.IndexOutOfBoundsException("row or col out of bound");
        }
        return singleVirtualSite.connected(N * N, repNumber(row, col));
    }

    // number of open sites
    public int numberOfOpenSites(){
        return openSite.size();
    }

    // does the system percolate?
    public boolean percolates() {
        return fullSite.connected(N * N, N * N + 1);
    }

    // use for unit testing (not required)
    public static void main(String[] args) {
    }
}
