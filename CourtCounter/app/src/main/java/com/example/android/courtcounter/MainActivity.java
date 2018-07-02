package com.example.android.courtcounter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int teamScoreA = 0;
    int teamScoreB = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void add_3_A(View v) {
        teamScoreA = teamScoreA + 3;
        displayForTeamA(teamScoreA);
    }

    public void add_2_A(View v) {
        teamScoreA = teamScoreA + 2;
        displayForTeamA(teamScoreA);
    }

    public void add_1_A(View v) {
        teamScoreA = teamScoreA + 1;
        displayForTeamA(teamScoreA);
    }
    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void add_3_B(View v) {
        teamScoreB = teamScoreB + 3;
        displayForTeamB(teamScoreB);
    }

    public void add_2_B(View v) {
        teamScoreB = teamScoreB + 2;
        displayForTeamB(teamScoreB);
    }

    public void add_1_B(View v) {
        teamScoreB = teamScoreB + 1;
        displayForTeamB(teamScoreB);
    }
    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }
    public void reset_AB(View v) {
        teamScoreA = 0;
        teamScoreB = 0;
        displayForTeamA(teamScoreA);
        displayForTeamB(teamScoreB);
    }
}
