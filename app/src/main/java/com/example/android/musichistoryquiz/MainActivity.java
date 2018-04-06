package com.example.android.musichistoryquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    /**
     * This method is called when the submit answers button is clicked.
     */

    public void submitAnswers(View view) {

        // Correct answer for Bach music period question
        RadioButton bachPeriodRadioButton = (RadioButton) findViewById(R.id.bach_baroque);
        boolean bachPeriodCorrect = bachPeriodRadioButton.isChecked();

        // Correct answer for Mozart country question
        RadioButton mozartCountryRadioButton = (RadioButton) findViewById(R.id.mozart_austria);
        boolean mozartCountryCorrect = mozartCountryRadioButton.isChecked();

        // Answer field for Ode to Joy question
        EditText joyField = (EditText) findViewById(R.id.joy_answer);
        String joyAnswer = joyField.getText().toString();

        // Correct answer for Bach denomination question
        RadioButton bachDenominationRadioButton = (RadioButton) findViewById(R.id.bach_lutheran);
        boolean bachDenominationCorrect = bachDenominationRadioButton.isChecked();

        // Correct answers for Handel contemporaries

        CheckBox pachelbelCheckBox = (CheckBox) findViewById(R.id.handel_pachelbel);
        boolean pachelbelCorrect = pachelbelCheckBox.isChecked();

        CheckBox bachCheckBox = (CheckBox) findViewById(R.id.handel_bach);
        boolean bachCorrect = bachCheckBox.isChecked();

        CheckBox buxtehudeCheckBox = (CheckBox) findViewById(R.id.handel_buxtehude);
        boolean buxtehudeCorrect = buxtehudeCheckBox.isChecked();

        int score = calculateScore(bachPeriodCorrect, mozartCountryCorrect, joyAnswer, bachDenominationCorrect,
                pachelbelCorrect, bachCorrect, buxtehudeCorrect);

        // Show a toast message with final score.


        if (score >= 3) {
            Toast.makeText(this, getString(R.string.congratulations) + score + getString(R.string.out_of_five), Toast.LENGTH_LONG).show();
        } else if (score >= 1 && score <= 2) {
            Toast.makeText(this, getString(R.string.not_bad) + score + getString(R.string.out_of_five), Toast.LENGTH_LONG).show();
        } else if (score == 0) {
            Toast.makeText(this, getString(R.string.oh_no) + score + getString(R.string.out_of_five), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * calculates final score
     *
     * @param bachPeriod       is whether or not the user has selected Baroque as the correct answer.
     * @param mozartCountry    is whether or not the user has selected Austria as the correct answer.
     * @param joy              evaluates if the user has typed "joy" in the edit text field
     * @param bachDenomination is whether or not the user has selected Lutheran as the correct answer.
     * @param pachelbel        is whether or not the user has selected Pachelbel as the correct answer.
     * @param bach             is whether or not the user has selected Bach as the correct answer.
     * @param buxtehude        is whether or not the user has selected Buxtehude as the correct answer.
     * @return total score.
     */
    private int calculateScore(boolean bachPeriod, boolean mozartCountry, String joy, boolean bachDenomination,
                               boolean pachelbel, boolean bach, boolean buxtehude) {
        int baseScore = 0;

        // Evaluates if Baroque is selected and adds 1 point if true.
        if (bachPeriod) {
            baseScore += 1;
        }

        // Evaluates if Austria is selected and adds 1 point if true.
        if (mozartCountry) {
            baseScore += 1;
        }

        // Evaluates if answer is Joy and adds 1 point if true.
        if (joy.equals("Joy") || joy.equals("joy")) {
            baseScore += 1;
        }

        // Evaluates if Lutheran is selected and adds 1 point if true
        if (bachDenomination) {
            baseScore += 1;
        }

        // Evaluates if Pachelbel, Bach, and Buxtehude are selected as the correct answers.
        if (pachelbel && bach && buxtehude) {
            baseScore += 1;
        }

        return baseScore;
    }
}
