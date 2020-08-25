package com.theking.eqsol;

import android.content.Context;
import android.os.Bundle;


import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;


import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;


import java.util.Objects;



/**
 * A simple {@link Fragment} subclass.
 */
public class equationSolver extends Fragment {

    public equationSolver() {
        // Required empty public constructor
    }

    private TextInputLayout[] textView = new TextInputLayout[10];
    private CardView cardView;
    private NestedScrollView scrollView;
    private TextView solutionText;
    private int i=0,k=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View layout =inflater.inflate(R.layout.fragment_equation_solver, container, false);
        scrollView=layout.findViewById(R.id.scroll);
        cardView=layout.findViewById(R.id.card);
        cardView.setVisibility(View.GONE);
        solutionText=layout.findViewById(R.id.solutionContainer);
        textView[0]=layout.findViewById(R.id.e1);
        textView[1]=layout.findViewById(R.id.e2);
        textView[2]=layout.findViewById(R.id.e3);
        textView[3]=layout.findViewById(R.id.e4);
        textView[4]=layout.findViewById(R.id.e5);
        textView[5]=layout.findViewById(R.id.e6);
        textView[6]=layout.findViewById(R.id.e7);
        textView[7]=layout.findViewById(R.id.e8);
        textView[8]=layout.findViewById(R.id.e9);
        textView[9]=layout.findViewById(R.id.e10);
        for(int j=0;j<10;j++) {
            textView[j].setVisibility(View.GONE);
        }
        i++;
        textView[i-1].setVisibility(View.VISIBLE);
        final Context context = inflater.getContext();
        /*text = new TextView(context);
        text.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        text.setTextSize(40);
        text.setGravity(CENTER);*/
        Button button=layout.findViewById(R.id.add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i>9) {
                    Toast.makeText(context, "Maximum number of equations reached !!!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    i++;
                    /*textView[i] = new EditText(context);
                    textView[i].setId(i);
                    textView[i].setTextSize(40);

                    textView[i].setGravity(CENTER);
                    String hint = "eq : " + String.valueOf(i);
                    textView[i].setHint(hint);
                    textView[i].setMaxEms(10);
                    textView[i].setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    f.addView(textView[i]);*/
                    textView[i-1].setVisibility(View.VISIBLE);
                }
                /*if( text.getParent()!=null) {
                    f.removeView(text);
                }*/
                cardView.setVisibility(View.GONE);
            }
        });
        Button button2=layout.findViewById(R.id.remove);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i<1) {
                    Toast.makeText(context, "No equations to remove!!", Toast.LENGTH_SHORT).show();
                }
                else {
                 //f.removeView(textView[i]);
                    textView[i-1].setVisibility(View.GONE);
                 i--;
                }
                /*if( text.getParent()!=null) {
                    f.removeView(text);
                }*/
                cardView.setVisibility(View.GONE);
            }
        });
        Button button3=layout.findViewById(R.id.ok);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String[] equations = new String[15];
                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(layout.getWindowToken(), 0);
                    }
                    if(i!=0) {

                    k=0;
                    for(int j=1;j<i+1;j++) {
                        if(Objects.requireNonNull(textView[j - 1].getEditText()).getText().toString().length()==0) {
                            continue;
                        }
                        equations[k]= Objects.requireNonNull(textView[j - 1].getEditText()).getText().toString();
                        k++;

                    }

                        if(equations[0]!=null){
                            cardView.setVisibility(View.VISIBLE);
                            solutions(equations);}

                }
                }catch(Exception e) {
                    Toast.makeText(context,"Error!!",Toast.LENGTH_SHORT).show();
                }
            }

        });
        return layout ;
    }
    private void solutions(final String[] equations) {

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[][] solutionMatrix=new String[12][2];
                matrixType matrice ;
               try {
                   matrice= reqFunctions.StringToMatrix(equations,k);
               } catch (Exception e) {
                   matrice = null;
                   Toast.makeText(getContext(),e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
               }
               if(matrice==null) {
                    solutionMatrix=null;
                }
               /*else if(matrice.variables.length>k) {
                    solutionMatrix=null;
               }*/
               else{

                       for(int j=0;j<k;j++) {
                           float[][] tempMatrix = new float[k][k];
                           for(int x=0;x<k;x++) {
                               System.arraycopy(matrice.coeff[x], 0, tempMatrix[x], 0, k);
                           }
                           for(int l=0;l<k;l++) {
                               tempMatrix[l][j]=matrice.constants[l];
                           }

                           if(reqFunctions.determinantOf(matrice.coeff,k)!=0) {
                               float answer;
                               answer=reqFunctions.determinantOf(tempMatrix,k)/reqFunctions.determinantOf(matrice.coeff,k);
                               try { solutionMatrix[j][0]=String.valueOf(matrice.variables[j]);
                               solutionMatrix[j][1]=String.valueOf(answer);
                               }catch (Exception e) {
                                   Toast.makeText(getContext(),e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                               }
                           }
                           else {
                               solutionMatrix[j][0]="";
                               solutionMatrix[0][0]="Infinte solutions or No solution!!";
                               solutionMatrix[j][1]="";
                           }
                       }

                }
                StringBuilder results = new StringBuilder();
                if(solutionMatrix==null) {
                    results.append("Check Equations !!!!");
                }
                else {

                        if( solutionMatrix[0][0].equals("Infinte solutions or No solution!!")) {
                            results.append(solutionMatrix[0][0]);
                        }
                        else {
                            for(int j=0;j<k;j++) {
                                results.append(solutionMatrix[j][0]).append("=").append(solutionMatrix[j][1]).append('\n');
                            }
                        }

                }
                solutionText.setText(results);
                scrollView.scrollTo((int)cardView.getX(),(int)cardView.getY());
                results.delete(0,results.length());

            }
        });

    }
}
