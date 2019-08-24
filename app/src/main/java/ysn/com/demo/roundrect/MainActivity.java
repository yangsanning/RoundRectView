package ysn.com.demo.roundrect;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import ysn.com.view.roundrect.RoundRectView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RoundRectView roundRectView = findViewById(R.id.main_activity_round_rect_view);
        roundRectView.setOnClickListener(view -> {
            roundRectView.setClick(!roundRectView.isClick());
        });
    }
}
