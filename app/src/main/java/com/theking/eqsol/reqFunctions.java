package com.theking.eqsol;


class reqFunctions {
    static float determinantOf(float[][] a, int n){
        if(n==1) {
            return a[0][0];
        }
        else {
            int det=0;
            for(int i=0;i<n;i++){
                det+= Math.pow(-1,i)*a[0][i]*determinantOf(subMatrix(a,n,i),n-1);
            }
            return det;
        }
    }

    private static float[][] subMatrix(float[][] a, int n, int _j) {
    float[][] b =new float[n-1][n-1];
    for(int i=1, x=0;i<n;i++) {
        for(int j=0,y=0;j<n;j++) {
            if(j==_j){
                continue;
            }
            b[x][y]=a[i][j];
            y++;
        }
        x++;
    }
    return b;
    }
    public static matrixType StringToMatrix(String[] equations, int n) {
        matrixType matrices =new matrixType(n);
        for(int i=0;i<n;i++) {
            equations[i]=equations[i].trim();
            equations[i]=equations[i].replaceAll(" ","");
            char[] equation =equations[i].toCharArray();
            StringBuilder tempNo= new StringBuilder();
            StringBuilder tempVar= new StringBuilder();
            int encounteredEqual=0,terms=-1;
            for (int j=0;j<equation.length;j++) {
                if ((equation[j] >= 'a' && equation[j] <= 'z') || (equation[j] >= 'A' && equation[j] <= 'Z')) {
                    tempVar.append(equation[j]);
                } else if ((equation[j]>= '0' && equation[j] <= '9')||equation[j]=='.') {
                    tempNo.append(equation[j]);
                }
                else {
                    if (equation[j] != '-' && equation[j] != '+' && equation[j] != '=' && equation[j]!='.') {
                        return null;
                    }
                    if ((tempVar.length() == 0 && (tempNo.length() == 0 ||tempNo.toString().equals("-")))&&(equation[j]!='-'&& equation[j-1]!='=')) {
                        return null;
                    }

                    if (tempVar.length() != 0) {
                      if(tempNo.length()==0 || tempNo.toString().equals("-")) {
                          tempNo.append("1");
                      }
                      for(int k=0;k<n;k++){
                          if(matrices.variables[k]==null) {
                              matrices.variables[k]=tempVar.toString();
                              if(encounteredEqual==0) {
                                  matrices.coeff[i][k]+=Float.parseFloat(tempNo.toString());
                              }
                              else if(encounteredEqual==1) {
                                  matrices.coeff[i][k]-=(Float.parseFloat(tempNo.toString()));
                              }
                              terms++;
                              break;
                          }
                          else if(matrices.variables[k].equals(tempVar.toString())) {
                              if(encounteredEqual==0) {
                                  matrices.coeff[i][k]+=Float.parseFloat(tempNo.toString());
                              }
                              else if(encounteredEqual==1){
                                  matrices.coeff[i][k]-=(Float.parseFloat(tempNo.toString()));
                              }
                              terms++;
                              break;
                          }
                      }
                      if(terms!=0) {
                          return null;
                      }
                      terms--;
                    }
                    if(tempVar.length()==0 && tempNo.length()!=0) {
                        if( tempNo.toString().equals("-")) {
                            tempNo.append("1");
                        }
                        //matrices.constants[i]=i+1;//delete
                        if (encounteredEqual == 1) {
                            matrices.constants[i] += Float.parseFloat(tempNo.toString());
                        } else if (encounteredEqual == 0) {
                            matrices.constants[i] += ((-1) * Float.parseFloat(tempNo.toString()));
                        }
                    }
                    tempNo.delete(0, tempNo.length());
                    tempVar.delete(0, tempVar.length());
                    if (encounteredEqual > 1) {
                        return null;
                    }
                    if (equation[j] == '=') {
                        encounteredEqual ++;
                    }
                    if (equation[j] == '-') {
                        tempNo.append('-');
                    }
                }
            }

            if (tempVar.length() != 0&&tempNo.length()!=0) {
                if(tempNo.length()==0 || tempNo.toString().equals("-")) {
                    tempNo.append("1");
                }
                for(int k=0;k<n;k++){
                    if(matrices.variables[k]==null) {
                        matrices.variables[k]=tempVar.toString();
                        if(encounteredEqual==0) {
                            matrices.coeff[i][k]+=Float.parseFloat(tempNo.toString());
                        }
                        else if(encounteredEqual==1) {
                            matrices.coeff[i][k]-=(Float.parseFloat(tempNo.toString()));
                        }
                        terms++;
                        break;
                    }
                    else if(matrices.variables[k].equals(tempVar.toString())) {
                        if(encounteredEqual==0) {
                            matrices.coeff[i][k]+=Float.parseFloat(tempNo.toString());
                        }
                        else if(encounteredEqual==1){
                            matrices.coeff[i][k]-=(Float.parseFloat(tempNo.toString()));
                        }
                        terms++;
                        break;
                    }
                }
                if(terms!=0) {
                    return null;
                }
            }
            if(tempVar.length()==0) {
                if( tempNo.toString().equals("-")) {
                    tempNo.append("1");
                }

                if (encounteredEqual == 1) {
                    matrices.constants[i] += Float.parseFloat(tempNo.toString());
                } else if (encounteredEqual == 0) {
                    matrices.constants[i] += ((-1) * Float.parseFloat(tempNo.toString()));
                }
            }

        }
        return matrices;
    }
    static double eval(String exp) {
        int top=-1,topResult=-1;
        double answer,skip=0;

        String[] result = new String[50];
        StringBuilder tempNo=new StringBuilder(),tempExp=new StringBuilder();
        tempExp.append(exp);
        for(int i=exp.length()-1;i>=0;i--) {
            if ((exp.toCharArray()[i] >= '0' && exp.toCharArray()[i] <= '9')|| exp.toCharArray()[i]=='.') break;
            tempExp.deleteCharAt(i);
        }
        exp=tempExp.toString();
        char[] stack = new char[50],expression=exp.trim().toCharArray();
        for (char c : expression) {
            if ((c >= '0' && c <= '9')|| c=='.') {
                tempNo.append(c);
            }
            else {

                if(skip==0 && c!='(' ) {
                    if(tempNo.toString().length()==0) {
                        tempNo.append(c);
                        continue;
                    }
                    topResult++;
                    result[topResult] = tempNo.toString();
                    tempNo.delete(0,tempNo.length());
                }
                if(skip==1) skip=0;
                if (top == -1) {
                    top++;
                    stack[top] = c;
                }
                else {
                    if (c == '(') {
                        top++;
                        stack[top] = c;
                    }

                    else if (c == ')') {

                        while ( stack[top] != '(' ) {
                            topResult++;
                            result[topResult] = String.valueOf(stack[top]);

                            top--;
                        }
                        top--;
                        skip=1;

                    }
                    else if (precedence(c) >= precedence(stack[top])) {
                        topResult++;
                        result[topResult] = String.valueOf(stack[top]);
                        stack[top]=c;
                    }
                    else if (precedence(c) < precedence(stack[top])) {
                        top++;
                        stack[top] = c;
                    }
                }
            }

        }
        if(tempNo.toString().length()!=0) {
            topResult++;
            result[topResult]=tempNo.toString();

        }
        while(top!=-1){
            if(stack[top]!='(') {
                topResult++;
                result[topResult]=String.valueOf(stack[top]);

                top--;
            }
            else top--;
        }

        int first=0,second=0;
        for(int i=0;i<=topResult;i++) {
            if(result[i].equals("^")||result[i].equals("x")||result[i].equals("/")||result[i].equals("+")||result[i].equals("-")) {

                    result[first]=String.valueOf(calc(Double.parseDouble(result[first]),Double.parseDouble(result[second]),result[i]));
                    if(second!=0) {
                        result[second]="none";
                    }
                    if(i!=0) {
                        result[i]="none";
                    }
                    second=0;
                    first=0;
                    if(i==topResult) {
                        break;
                    }
                    i=0;

            }
            else if(result[i].equals("none")) {
                continue;
            }
            //else {
                int temp=second;
                second=i;
                first=temp;
            //}
        }
        answer = Double.parseDouble(result[0]);
        return answer;
    }
    private static int precedence(char c) {
        switch(c) {
            case '^':
                return 1;
            case '(':
                return 7;
            case 'x':
            case '/':
                return 3;
            case '+':
            case '-':
                return 5;
            default:
                return -1;
        }
    }
    private static double calc(double f,double s,String c) {
        switch(c) {
            case "^":
                return  Math.pow(f,s);
            case "x":
                return f*s;
            case "/":
                return f/s;
            case "+":
                return f+s;
            case "-":
                return f-s;
            default:
                return -1;
        }
    }
}
