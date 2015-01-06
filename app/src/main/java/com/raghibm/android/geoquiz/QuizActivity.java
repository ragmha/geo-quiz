package com.raghibm.android.geoquiz;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class QuizActivity extends ActionBarActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;


    private int mCurrentIndex = 0;

    private boolean mIsCheater;


    //Adding TAG constant
    private static final String TAG = "QuizActivity";

    //Adding KEY for the value
    private static final String KEY_INDEX = "index";

    //Adding CHEAT_LIST constant
    private static final String CHEAT_LIST = "cheat";


    //Adding variables to TrueFalse array
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, false),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, false),
            new TrueFalse(R.string.question_5, false),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, true),
            new TrueFalse(R.string.question_13, true),
            new TrueFalse(R.string.question_14, false),
            new TrueFalse(R.string.question_15, true),
            new TrueFalse(R.string.question_16, false),
            new TrueFalse(R.string.question_17, false),
            new TrueFalse(R.string.question_18, false),
            new TrueFalse(R.string.question_19, true),
            new TrueFalse(R.string.question_20, false),
            new TrueFalse(R.string.question_21, true),
            new TrueFalse(R.string.question_22, true),
            new TrueFalse(R.string.question_23, false),
            new TrueFalse(R.string.question_24, true),
            new TrueFalse(R.string.question_25, false),
            new TrueFalse(R.string.question_26, false),
            new TrueFalse(R.string.question_27, true),
            new TrueFalse(R.string.question_28, true),
            new TrueFalse(R.string.question_29, false),
            new TrueFalse(R.string.question_30, true),
    };

    //Storing no. cheated
    private ArrayList<Integer> mCheat = new ArrayList<Integer>();



    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void cheatFromList() {
        if(mCheat.contains(mCurrentIndex)){
            mIsCheater = true;
        }
        else{
            mIsCheater = false;
        }
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();

        int messageResId = 0;

        if(mIsCheater){
            messageResId = R.string.judgement_toast;
        }else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this, messageResId,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

       //Wiring the TextView
       mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
       mQuestionTextView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v){
            mCurrentIndex = (mCurrentIndex+1)%mQuestionBank.length;
            updateQuestion();
           }
       });


       //Wiring the True and False Buttons
        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkAnswer(false);

            }
        });

        //Wiring the Prev Button
        mPrevButton = (ImageButton)findViewById(R.id.previous_button);
        mPrevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mCurrentIndex == 0){
                    mCurrentIndex = mQuestionBank.length - 1;
                    mIsCheater=false;
                }
                else {
                    mCurrentIndex = mCurrentIndex -1;
                    mIsCheater=false;
                }
                cheatFromList();
                updateQuestion();
            }
        });

        //Wiring the Next Button
        mNextButton = (ImageButton)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
               mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
               mIsCheater = false;
               cheatFromList();
               updateQuestion();
        }
        });

        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mCheat = savedInstanceState.getIntegerArrayList(CHEAT_LIST);
        }


        //Wiring the Cheat! Button

        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
                Intent i = new Intent(QuizActivity.this,CheatActivity.class);
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE,answerIsTrue);
                startActivityForResult(i, 0);
            }

        });

        updateQuestion();

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);
        savedInstanceState.putIntegerArrayList(CHEAT_LIST,mCheat);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCOde, Intent data){
        if(data == null){
            return;
        }
        mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN,false);
            //Add cheater to the list
        if (!mCheat.contains(mCurrentIndex) && mIsCheater == true){
            mCheat.add(mCurrentIndex);
        }
            //Check if user has cheated in previous round
        else if(mIsCheater == false && mCheat.contains(mCurrentIndex)){
            mIsCheater = true;
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause() called");
    }


    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }


}