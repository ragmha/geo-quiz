package com.raghibm.android.geoquiz;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;

/**
 * Created by rgb_211 on 3.1.2015.
 */
public class CheatActivity extends ActionBarActivity {

public static final String EXTRA_ANSWER_IS_TRUE = "com.raghibm.android.geoquiz.answer_is_true";
public static final String EXTRA_ANSWER_SHOWN ="com.raghibm.android.geoquiz.answer_shown";

    private boolean mAnswerIsTrue;

    private TextView mAnswerTextView;
    private Button mShowAnswer;


public void setAnswerShownResult(boolean isAnswerShown){
    Intent data = new Intent();
    data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
    setResult(RESULT_OK,data);
}

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cheat_activity);

        mAnswerIsTrue=getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);

        mAnswerTextView=(TextView)findViewById(R.id.answerTextView);
        //Answer will not be shown until the user pressed the button
        setAnswerShownResult(false);

        mShowAnswer=(Button)findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
            if(mAnswerIsTrue){
                mAnswerTextView.setText(R.string.true_button);
            }
                else{
                mAnswerTextView.setText(R.string.false_button);
            }
            setAnswerShownResult(true);
        }
        });


    }





}
