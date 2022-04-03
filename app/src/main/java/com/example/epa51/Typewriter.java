package com.example.epa51;

import android.content.Context;
import android.util.AttributeSet;
import android.os.Handler;


import androidx.annotation.Nullable;



public class Typewriter extends androidx.appcompat.widget.AppCompatTextView {

    private CharSequence myText;
    private int myIndex;
    private long myDelay=150;

    public Typewriter(Context context) {
        super(context);
    }

    public Typewriter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    private Handler myhandler=new Handler();
    private Runnable charAdd=new Runnable(){
        @Override

        public void run()
        {
            setText(myText.subSequence(0,myIndex++));
            if (myIndex<=myText.length())
            {
                myhandler.postDelayed(charAdd,myDelay);
            }
        }
    };

    public void animatedText(CharSequence mytext)
    {

        myText=mytext;
        myIndex=0;
        setText("");
        myhandler.removeCallbacks(charAdd);
        myhandler.postDelayed(charAdd,myDelay);
    }

    public void setCharDelay(long md)
    {

        myDelay=md;
    }


}
