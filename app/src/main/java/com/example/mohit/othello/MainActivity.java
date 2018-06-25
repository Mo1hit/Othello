package com.example.mohit.othello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout rootLayout;
    public int SIZE;
    public ArrayList<LinearLayout> rows;
    public Button board[][];

    public static int BLACK=0;
    public static int WHITE=1;
    public static int NOPLAYER=-1;

    public int currentPlayer=BLACK;

    public ArrayList<Button> whiteDiscs;
    public ArrayList<Button> blackDiscs;

    public int flag=0;

    public int[] x={-1,-1,-1,0,+1,+1,+1,0},y={-1,0,+1,+1,+1,0,-1,-1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout=findViewById(R.id.rootLayout);

        setupBoard();
        SIZE=6;
    }

    public void setupBoard()
    {
        rows=new ArrayList<>();
        board=new Button[SIZE][SIZE];
        rootLayout.removeAllViews();

        whiteDiscs=new ArrayList<>();
        blackDiscs=new ArrayList<>();

        for(int i=0;i<SIZE;i++)
        {
            LinearLayout linearLayout=new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);
            linearLayout.setLayoutParams(layoutParams);
            rows.add(linearLayout);
        }

        for(int i=0;i<SIZE;i++)
        {
            LinearLayout row=rows.get(i);
            for(int j=0;j<SIZE;j++)
            {
                Button button=new Button(this,i,j);
                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
                button.setLayoutParams(layoutParams);
                button.setOnClickListener(this);
                button.setDisabled(true);
                row.addView(button);
                board[i][j]=button;
            }
        }
        board[SIZE/2-1][SIZE/2-1].setButton(WHITE);
        whiteDiscs.add(board[SIZE/2-1][SIZE/2-1]);

        board[SIZE/2-1][SIZE/2].setButton(BLACK);
        blackDiscs.add(board[SIZE/2-1][SIZE/2]);

        board[SIZE/2][SIZE/2-1].setButton(BLACK);
        blackDiscs.add(board[SIZE/2][SIZE/2-1]);

        board[SIZE/2][SIZE/2].setButton(WHITE);
        whiteDiscs.add(board[SIZE/2][SIZE/2]);

        setClickable();
    }

    public void togglePlayer(int currentPlayer)
    {
        if(currentPlayer==BLACK)
        {
            currentPlayer=WHITE;
        }
        else if(currentPlayer==WHITE)
        {
            currentPlayer=BLACK;
        }
    }

    public void setClickable()
    {
        if(currentPlayer==WHITE)
        {
            int count=0;
            for(int i=0;i<whiteDiscs.size();i++)
            {
                int xcd=whiteDiscs.get(i).x;
                int ycd=whiteDiscs.get(i).y;
                for(int j=0;j<8;j++)
                {
                    int xa=xcd+x[j];
                    int ya=ycd+y[j];
                    if(xa>=0&&xa<SIZE&&ya>=0&&ya<SIZE&&board[xa][ya].getButton()==BLACK)
                    {
                        while(xa>=0&&xa<SIZE&&ya>=0&&ya<SIZE&&board[xa][ya].getButton()!=WHITE)
                        {
                            if(board[xa][ya].getButton()==NOPLAYER)
                            {
                                count++;
                                board[xa][ya].setDisabled(false);
                                break;
                            }
                            xa=xcd+x[j];
                            ya=ycd+y[j];
                        }
                    }
                }
            }
            if(count==0)
            {
                if(flag==0)
                {
                    currentPlayer=BLACK;
                    Toast.makeText(this, "WHITE's TURN FORFEITED, LACK OF MOVES", Toast.LENGTH_LONG).show();
                    flag++;
                    setClickable();
                }
                else
                {
                    if(whiteDiscs.size()>blackDiscs.size())
                    {
                        Toast.makeText(this, "WHITE WON", Toast.LENGTH_LONG).show();
                    }
                    else if(whiteDiscs.size()<blackDiscs.size())
                    {
                        Toast.makeText(this, "BLACK WON", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(this, "DRAW", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
        else if(currentPlayer==BLACK)
        {
            int count=0;
            for(int i=0;i<blackDiscs.size();i++)
            {
                int xcd=blackDiscs.get(i).x;
                int ycd=blackDiscs.get(i).y;
                for(int j=0;j<8;j++)
                {
                    int xa=xcd+x[j];
                    int ya=ycd+y[j];
                    if(xa>=0&&xa<SIZE&&ya>=0&&ya<SIZE&&board[xa][ya].getButton()==WHITE)
                    {
                        while(xa>=0&&xa<SIZE&&ya>=0&&ya<SIZE&&board[xa][ya].getButton()!=BLACK)
                        {
                            if(board[xa][ya].getButton()==NOPLAYER)
                            {
                                count++;
                                board[xa][ya].setDisabled(false);
                                break;
                            }
                            xa=xcd+x[j];
                            ya=ycd+y[j];
                        }
                    }
                }
            }
            if(count==0)
            {
                if(flag==0)
                {
                    currentPlayer=WHITE;
                    Toast.makeText(this, "BLACK's TURN FORFEITED, LACK OF MOVES", Toast.LENGTH_LONG).show();
                    flag++;
                    setClickable();
                }
                else
                {
                    if(whiteDiscs.size()>blackDiscs.size())
                    {
                        Toast.makeText(this, "WHITE WON", Toast.LENGTH_LONG).show();
                    }
                    else if(whiteDiscs.size()<blackDiscs.size())
                    {
                        Toast.makeText(this, "BLACK WON", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(this, "DRAW", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    @Override
    public void onClick(View view)
    {

    }
}