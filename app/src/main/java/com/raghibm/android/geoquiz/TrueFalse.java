package com.raghibm.android.geoquiz;

/**
 * Created by rgb_211 on 3.1.2015.
 */
public class TrueFalse {

    // 2 Member variables
    private int mQuestion;

    private boolean mTrueQuestion;

    //True and False constructor
    public TrueFalse(int question, boolean trueQuestion)
    {
        mQuestion = question;
        mTrueQuestion = trueQuestion;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int question) {
        mQuestion = question;
    }

    public boolean isTrueQuestion() {
        return mTrueQuestion;
    }

    public void setTrueQuestion(boolean trueQuestion) {
        mTrueQuestion = trueQuestion;
    }
}
