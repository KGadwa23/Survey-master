package com.katrina.survey;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    private static final String CHRISTMAS_RESULT = "com.katrina.surveyactivity.christmasresult";
    private static final String THANKSGIVING_RESULT = "com.katrina.surveyactivity.thanksgivingresult";
    private static final String RESET_REQUESTED = "com.katrina.surveyactivity.resetrequester";
    //private static final String OUTCOMES = "com.katrina.surveyactivity.outcomes";

    private int mChristmasResult;
    private int mThanksgivingResult;

    private TextView mChristmasOutcome;
    private TextView mThanksgivingOutcome;
    private Button mResults;
    private Button mReset;
    //private Button mContinue;

    public static Intent newIntent(Context packageContext, int christmasResults, int thanksgivingResults) {
        Intent i = new Intent(packageContext, ResultsActivity.class);
        i.putExtra(CHRISTMAS_RESULT, christmasResults);
        i.putExtra(THANKSGIVING_RESULT, thanksgivingResults);
        return i;
    }

    public static boolean wasResetRequested(Intent result) {
        return result.getBooleanExtra(RESET_REQUESTED, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        mChristmasResult = getIntent().getIntExtra(CHRISTMAS_RESULT, 0);
        mThanksgivingResult = getIntent().getIntExtra(THANKSGIVING_RESULT, 0);

        mChristmasOutcome = (TextView) findViewById(R.id.christmasOutcome2);
        mThanksgivingOutcome = (TextView) findViewById(R.id.thanksgivingOutcome2);

        mResults = (Button) findViewById(R.id.resultsButton);
        mResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChristmasOutcome.setText("Christmas: " + String.valueOf(mChristmasResult) + "        ");
                mThanksgivingOutcome.setText("Thanksgiving: " + String.valueOf(mThanksgivingResult));
            }
        });

        mReset = (Button) findViewById(R.id.resetButton);
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChristmasResult = 0;
                mThanksgivingResult = 0;
                mChristmasOutcome.setText("Christmas: " + String.valueOf(mChristmasResult) + "        ");
                mThanksgivingOutcome.setText("Thanksgiving: " + String.valueOf(mThanksgivingResult));
                setResultReset(true);
            }
        });

        //mContinue = (Button) findViewById(R.id.continueButton);
        //mContinue.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        setResults(int mChristmasOutcome, int mThanksgivingOutcome);
        //    }
        //});

    }

    //TODO how do I get the reseted results back to survey activity?
    private void setResultReset (boolean resultReset) {
        Intent data = new Intent();
        data.putExtra(RESET_REQUESTED, resultReset);
        setResult(RESULT_OK, data);
    }



   // private void setResults (int christmasOutcome, int thanksgivingOutcome) {
   //     Intent data = new Intent();
   //     data.putExtra(OUTCOMES, christmasOutcome, thanksgivingOutcome);
   //     setResult(RESULT_OK, data);
   // }
}
