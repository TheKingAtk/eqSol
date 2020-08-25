package com.theking.eqsol;

public class matrixType {
    public float[][] coeff;
    public String[] variables;
    public float[] constants;
    public matrixType(int n) {
        coeff=new float[n][n];
        variables=new String[n];
        constants=new float[n];
        for(int i=0;i<n;i++){
            constants[i]=0;
            for(int j=0;j<n;j++) {
                coeff[i][j]=0;
            }
        }
    }
}
