package rokas.armandas.guessinggame;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnGuess;
    private Button btnYes;
    private Button btnNo;
    private EditText txtGuess;
    private TextView lblOutput;
    private TextView lblEnterNumber;
    private int theNumber;
    private int triesLeft;

    public void checkGuess(){

        String userGuess = txtGuess.getText().toString();

        try {
            int guess = Integer.parseInt(userGuess);
            triesLeft--;
            if(guess > theNumber) {
                lblOutput.setText("Too high. Try again. You have " + triesLeft + " times left.");
            } else if (guess < theNumber) {
                lblOutput.setText("Too low. Try again. You have " + triesLeft + " times left.");
            } else {
                int triesInTotal = 7 - triesLeft;
                lblOutput.setText("Congratulation! The answer is: " + theNumber + ". It took " + triesInTotal + " times.");
                setTheNumberAndTries();
            }
        } catch (Exception e){
                lblOutput.setText("Please enter the whole number between 1 and 100");
        } finally {
            txtGuess.requestFocus();
            txtGuess.selectAll();
        }
        if (triesLeft <= 0){

            btnYes.setVisibility(View.VISIBLE);
            btnNo.setVisibility(View.VISIBLE);
            lblOutput.setText("You Lost! The number was " + theNumber);
            txtGuess.setVisibility(View.GONE);
            lblEnterNumber.setVisibility(View.GONE);
            btnGuess.setVisibility(View.GONE);

            // startActivity(new Intent(MainActivity.this, Pop.class)); // PopUp window, but no needed.

        }

    }

    public int getRandomNumbet() {
        return (int)(Math.random()*100 + 1);
    }
    public void setTheNumberAndTries() {
        theNumber = this.getRandomNumbet();
        triesLeft = 7;
    }

    public void newGame() {
        lblOutput.setText("Enter the number and click Guess! You have 7 tries!");
        setTheNumberAndTries();
        btnYes.setVisibility(View.GONE);
        btnNo.setVisibility(View.GONE);
        txtGuess.setVisibility(View.VISIBLE);
        lblEnterNumber.setVisibility(View.VISIBLE);
        btnGuess.setVisibility(View.VISIBLE);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGuess = (Button) findViewById(R.id.btnGuess);
        btnYes = (Button) findViewById(R.id.btnYes);
        btnNo = (Button) findViewById(R.id.btnNo);
        txtGuess = (EditText) findViewById(R.id.txtGuess);
        lblOutput = (TextView) findViewById(R.id.lblOutput);
        lblEnterNumber = (TextView) findViewById(R.id.lblEnterNumber);

        btnYes.setVisibility(View.GONE);
        btnNo.setVisibility(View.GONE);

        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });


        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });


        txtGuess.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                checkGuess();
                return true;
            }
        });

        newGame();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
