package com.example.mohit.othello;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

public class Button extends AppCompatButton
{
    private int button=MainActivity.NOPLAYER;
    public int x,y;

    public Button(Context context,int x, int y)
    {
        super(context);
        this.x=x;
        this.y=y;
    }

    public int getButton()
    {
        return this.button;
    }

    public void setButton(int button)
    {
        this.button = button;
        setEnabled(false);
        setText(button);
    }

    public void setDisabled(boolean var)
    {
        setEnabled(!var);
    }
}