package com.theking.eqsol;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.math.MathUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalculatorFragment extends Fragment {
    private StringBuilder exp,expShow;
    private String prevAns;
    private int openBra=0;
    public CalculatorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View layout =inflater.inflate(R.layout.fragment_calculator, container, false);
        final Context context = layout.getContext();
        Button[] numbers=new Button[10];
        numbers[0]=layout.findViewById(R.id.button0);
        numbers[1]=layout.findViewById(R.id.button1);
        numbers[2]=layout.findViewById(R.id.button2);
        numbers[3]=layout.findViewById(R.id.button3);
        numbers[4]=layout.findViewById(R.id.button4);
        numbers[5]=layout.findViewById(R.id.button5);
        numbers[6]=layout.findViewById(R.id.button6);
        numbers[7]=layout.findViewById(R.id.button7);
        numbers[8]=layout.findViewById(R.id.button8);
        numbers[9]=layout.findViewById(R.id.button9);
        Button plus=layout.findViewById(R.id.buttonAdd),minus=layout.findViewById(R.id.buttonSub),mult=layout.findViewById(R.id.buttonMult),div=layout.findViewById(R.id.buttonDivide);
        Button pow=layout.findViewById(R.id.buttonPow),expo=layout.findViewById(R.id.buttonExpo),open=layout.findViewById(R.id.buttonOpen),closed=layout.findViewById(R.id.buttonClosed);
        final Button func=layout.findViewById(R.id.buttonFunc),cont=layout.findViewById(R.id.buttonConts),cl=layout.findViewById(R.id.buttonClear),del=layout.findViewById(R.id.buttonDel);
        Button ans=layout.findViewById(R.id.buttonAns);
        Button point=layout.findViewById(R.id.buttonPoint),equals=layout.findViewById(R.id.buttonEquals);
        final float sp=getResources().getDisplayMetrics().scaledDensity;
        final TextView expression=layout.findViewById(R.id.expBox),answer=layout.findViewById(R.id.ansBox);
        expression.setMovementMethod(new ScrollingMovementMethod());
        exp = new StringBuilder();
        answer.setHorizontallyScrolling(true);
        answer.setMovementMethod(new ScrollingMovementMethod());
        numbers[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exp.append(0);
                expression.setText(exp.toString());
                expression.scrollTo(0, (int) (-sp*10+((expression.getLineCount()-1)*(expression.getLineHeight()))));
            }
        });

        numbers[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exp.append(1);
                expression.setText(exp.toString());
                expression.scrollTo(0, (int) (-sp*10+((expression.getLineCount() - 1) * (expression.getLineHeight()))));

            }
        });
        numbers[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exp.append(2);
                expression.setText(exp.toString());
                expression.scrollTo(0, (int) (-sp*10+((expression.getLineCount()-1)*(expression.getLineHeight()))));
            }
        });
        numbers[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exp.append(3);
                expression.setText(exp.toString());
                expression.scrollTo(0, (int) (-sp*10+((expression.getLineCount()-1)*(expression.getLineHeight()))));
            }
        });
        numbers[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exp.append(4);
                expression.setText(exp.toString());
                expression.scrollTo(0, (int) (-sp*10+((expression.getLineCount()-1)*(expression.getLineHeight()))));
            }
        });
        numbers[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exp.append(5);
                expression.setText(exp.toString());
                expression.scrollTo(0, (int) (-sp*10+((expression.getLineCount()-1)*(expression.getLineHeight()))));
            }
        });
        numbers[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exp.append(6);
                expression.setText(exp.toString());
                expression.scrollTo(0, (int) (-sp*10+((expression.getLineCount()-1)*(expression.getLineHeight()))));
            }
        });
        numbers[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exp.append(7);
                expression.setText(exp.toString());
                expression.scrollTo(0, (int) (-sp*10+((expression.getLineCount()-1)*(expression.getLineHeight()))));
            }
        });
        numbers[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exp.append(8);
                expression.setText(exp.toString());
                expression.scrollTo(0, (int) (-sp*10+((expression.getLineCount()-1)*(expression.getLineHeight()))));
            }
        });
        numbers[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exp.append(9);
                expression.setText(exp.toString());
                expression.scrollTo(0, (int) (-sp*10+((expression.getLineCount()-1)*(expression.getLineHeight()))));
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exp.append('+');
                expression.setText(exp.toString());
                expression.scrollTo(0, (int) (-sp*10+((expression.getLineCount()-1)*(expression.getLineHeight()))));

            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exp.append('-');
                expression.setText(exp.toString());
                expression.scrollTo(0, (int) (-sp*10+((expression.getLineCount()-1)*(expression.getLineHeight()))));
            }
        });
        mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exp.append('x');
                expression.setText(exp.toString());
                expression.scrollTo(0, (int) (-sp*10+((expression.getLineCount()-1)*(expression.getLineHeight()))));
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exp.append('/');
                expression.setText(exp.toString());
                expression.scrollTo(0, (int) (-sp*10+((expression.getLineCount()-1)*(expression.getLineHeight()))));
            }
        });
        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exp.append('.');
                expression.setText(exp.toString());
                expression.scrollTo(0, (int) (-sp*10+((expression.getLineCount()-1)*(expression.getLineHeight()))));
            }
        });

        cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(exp.length()!=0) {
                   exp.deleteCharAt(exp.length()-1);
                       while (exp.length()>0&&Character.isLetter(exp.toString().toCharArray()[exp.length() - 1])) {
                           exp.deleteCharAt(exp.length() - 1);
                       }

                   expression.setText(exp.toString());
               }
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(exp.length()!=0) {
                    exp.delete(0,exp.length());
                    expression.setText(exp.toString());
                }
                expression.scrollTo(0,0);
                answer.setText(exp.toString());
            }
        });
        func.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(context,func);
                MenuInflater inflater1 = popupMenu.getMenuInflater();
                inflater1.inflate(R.menu.functions,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch(item.getItemId()) {
                            case R.id.sin:
                                exp.append("Sin(");
                                break;
                            case R.id.cos:
                                exp.append("Cos(");
                                break;
                            case R.id.tan:
                                exp.append("Tan(");
                                break;
                            case R.id.sinh:
                                exp.append("Sinh(");
                                break;
                            case R.id.cosh:
                                exp.append("Cosh(");
                                break;
                            case R.id.abs:
                                exp.append("Abs(");
                                break;
                            case R.id.radian:
                                exp.append("Rad(");
                                break;
                            case R.id.degree:
                                exp.append("Deg(");
                                break;
                            case R.id.sininv:
                                exp.append("Sininv(");
                                break;
                            case R.id.cosinv:
                                exp.append("Cosinv(");
                                break;
                            case R.id.ln:
                                exp.append("Ln(");
                                break;
                        }
                        expression.setText(exp.toString());
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(context,cont);
                MenuInflater inflater1 = popupMenu.getMenuInflater();
                inflater1.inflate(R.menu.constants,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch(item.getItemId()) {
                            case R.id.pi:
                                exp.append("3.1416");
                                break;
                            case R.id.na:
                                exp.append("6.022x10^23");
                                break;
                                case R.id.c:
                                exp.append("3x10^8");
                                break;
                                case R.id.e:
                                exp.append("2.7182");
                                break;
                                case R.id.k:
                                exp.append("1.38x10^(-23)");
                                break;
                        }
                        expression.setText(exp.toString());
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exp.append('(');
                expression.setText(exp.toString());
                expression.scrollTo(0, (int) (-sp*10+((expression.getLineCount()-1)*(expression.getLineHeight()))));
            }
        });
        closed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exp.append(')');
                expression.setText(exp.toString());
                expression.scrollTo(0, (int) (-sp*10+((expression.getLineCount()-1)*(expression.getLineHeight()))));
            }
        });
        expo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exp.append("10^");
                expression.setText(exp.toString());
                expression.scrollTo(0, (int) (-sp*10+((expression.getLineCount()-1)*(expression.getLineHeight()))));
            }
        });
        pow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exp.append('^');
                expression.setText(exp.toString());
                expression.scrollTo(0, (int) (-sp*10+((expression.getLineCount()-1)*(expression.getLineHeight()))));
            }
        });
        ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(prevAns!=null) {
                    exp.append(prevAns);
                    expression.setText(exp.toString());
                }
            }
        });
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(exp.toString().length()!=0) {
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                        try {

                                Double value=Float.isInfinite((float)evaluator(exp.toString()))?evaluator(exp.toString()):(float)evaluator(exp.toString());
                                prevAns= String.valueOf(value);
                                prevAns=prevAns.replaceAll("E","x10^");

                                answer.setText(Html.fromHtml(prevAns.split("\\^",0)[0]));
                                if(prevAns.split("\\^",0)[0].length()!=prevAns.length()) {
                                    answer.setText(Html.fromHtml(prevAns.split("\\^",0)[0]+"<sup>"+prevAns.split("\\^",0)[1]+"</sup>"));
                                }

                    }catch(Exception e) {
                        String error = "Error";
                        answer.setText(error);
                    }
                        }
                    });
                }

            }
        });
        return layout;
    }
    private double evaluator (final String exp) {


                char[] expC=exp.toCharArray();
                StringBuilder formattedExp=new StringBuilder();
                formattedExp.append(exp);
                int brackets=0;
        for (char c :expC) {
            if(c=='(') brackets++;
            else if(c==')') brackets--;
        }
        while(brackets!=0) {
            formattedExp.append(')');
            brackets--;
        }
        expC=formattedExp.toString().toCharArray();
                for(int i=0;i<expC.length;i++) {
                    StringBuilder func=new StringBuilder();StringBuilder subExp=new StringBuilder();
                    if(expC[i]>='A' && expC[i]<='Z') {
                        int j=i+1;
                        func.append(expC[i]);
                        while((expC[j]>='a'&& expC[j]<='z')) {
                            func.append(expC[j]);
                            j++;
                        }
                        int closeB=1,k=j+1;
                        while( k<expC.length) {
                            if(expC[k]=='(') {
                                closeB++;
                            }
                            if(expC[k]==')') {
                                closeB--;
                                if(closeB==0) {
                                    break;
                                }
                            }

                                subExp.append(expC[k]);
                                k++;
                        }
                        String tempExp;
                        tempExp=String.valueOf(formattedExp);
                        formattedExp.delete(0,formattedExp.length());
                        String rep = func.toString()+"("+subExp.toString()+")";
                        if(i!=0 && (Character.isDigit(expC[i-1])||expC[i-1]==')') ){
                            formattedExp.append(tempExp.replace(rep, "x"+String.valueOf(funcCalc(evaluator(subExp.toString()), func.toString()))));
                        }
                        else formattedExp.append(tempExp.replace(rep, String.valueOf(funcCalc(evaluator(subExp.toString()), func.toString()))));
                    }
                }
        return reqFunctions.eval(formattedExp.toString().replaceAll("E", "x10^"));
    }

    private double funcCalc(double value,String func) {

        switch(func) {
            case "Sin":
                return Math.sin(Math.toRadians(value));
            case "Cos":
                return value==90?0:Math.cos(Math.toRadians(value));
            case "Tan" :
                return value==90?Double.parseDouble("bb"):Math.tan(Math.toRadians(value));
            case "Abs":
                return Math.abs(value);
            case "Cosh":
                return Math.cosh(Math.toRadians(value));
            case "Sinh":
                return Math.sinh(Math.toRadians(value));
            case "Rad":
                return Math.toRadians(value);
            case "Deg":
                return Math.toDegrees(value);
            case "Sininv" :
                return Math.toDegrees(Math.asin(value));
            case "Cosinv" :
                return Math.toDegrees(Math.acos(value));
            case "Ln":
                return  Math.log(value);
            default:
                return 0;
        }
    }
}
