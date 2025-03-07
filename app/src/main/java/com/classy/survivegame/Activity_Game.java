package com.classy.survivegame;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_Game extends AppCompatActivity {
    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_STATE = "EXTRA_STATE";
    private ImageButton[] arrows;
    private int currentLevel = 0;
    private boolean goodToGo = true;
    private int[] steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Get ID and State
        String id = getIntent().getStringExtra(EXTRA_ID);
        String state = getIntent().getStringExtra(EXTRA_STATE);


        // Initialize steps array with the same length as the ID
        steps = new int[id.length()];

        // Populate steps array with directions (0-3)
        for (int i = 0; i < steps.length; i++) {
            steps[i] = Character.getNumericValue(id.charAt(i)) % 4;  // Ensure values are between 0-3
        }

        findViews();
        initViews();
    }

    private void findViews() {
        this.arrows = new ImageButton[]{
                findViewById(R.id.game_BTN_left),  // 0
                findViewById(R.id.game_BTN_right), // 1
                findViewById(R.id.game_BTN_up),    // 2
                findViewById(R.id.game_BTN_down)   // 3
        };
    }

    private void initViews() {
        for (int i = 0; i < this.arrows.length; i++) {
            final int direction = i;  // Store button index
            this.arrows[i].setOnClickListener(v -> arrowClicked(direction));
        }
    }

    private void arrowClicked(int direction) {
        int expectedDirection = steps[currentLevel];

        // Debugging log to see what's happening
        Toast.makeText(this, "Pressed: " + direction + ", Expected: " + expectedDirection, Toast.LENGTH_SHORT).show();

        if (goodToGo && direction != expectedDirection) {
            goodToGo = false;
        }
        currentLevel++;

        if (currentLevel >= steps.length) {
            finishGame();
        }
    }

    private void finishGame() {
        String state = getIntent().getStringExtra(EXTRA_STATE);

        if (goodToGo) {
            Toast.makeText(this, "Survived in " + state, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "You Failed", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
