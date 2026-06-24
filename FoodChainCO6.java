public class FoodChainCO6 {

    // 0/1 Knapsack
    static int knapsack(int W, int wt[], int val[], int n) {

        int dp[][] = new int[n+1][W+1];

        for(int i=0;i<=n;i++) {

            for(int w=0;w<=W;w++) {

                if(i==0 || w==0)
                    dp[i][w]=0;

                else if(wt[i-1] <= w)
                    dp[i][w] = Math.max(
                            val[i-1]+dp[i-1][w-wt[i-1]],
                            dp[i-1][w]
                    );

                else
                    dp[i][w]=dp[i-1][w];
            }
        }

        return dp[n][W];
    }

    // LCS
    static int lcs(String X, String Y){

        int m=X.length();
        int n=Y.length();

        int dp[][]=new int[m+1][n+1];

        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){

                if(X.charAt(i-1)==Y.charAt(j-1))
                    dp[i][j]=dp[i-1][j-1]+1;

                else
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }

        return dp[m][n];
    }

    // Matrix Chain Multiplication
    static int matrixChain(int p[]){

        int n=p.length;

        int m[][]=new int[n][n];

        for(int L=2;L<n;L++){

            for(int i=1;i<n-L+1;i++){

                int j=i+L-1;

                m[i][j]=Integer.MAX_VALUE;

                for(int k=i;k<j;k++){

                    int q=m[i][k]
                            +m[k+1][j]
                            +p[i-1]*p[k]*p[j];

                    if(q<m[i][j])
                        m[i][j]=q;
                }
            }
        }

        return m[1][n-1];
    }

    public static void main(String[] args) {

        int values[] = {300,250,500,150};
        int weights[] = {4,3,6,2};

        int capacity = 15;

        System.out.println("Knapsack Revenue = ₹" +
                knapsack(capacity,weights,values,4));

        String customerA = "PIZZAWRAPBIRYANI";
        String customerB = "PIZZAWRAP";

        System.out.println("LCS Length = " +
                lcs(customerA,customerB));

        int matrices[] = {10,20,30,40};

        System.out.println("Minimum Multiplications = " +
                matrixChain(matrices));
    }
}