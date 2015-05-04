package me.tahnok.blueboxer;

import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class BlueBoxActivity extends ActionBarActivity {

    public static final int STAR = 10;
    public static final int POUND = 11;
    public static final int TONE_2600 = 12;
    //VIEWS
    @InjectView(R.id.button_1) Button button_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_box);
        ButterKnife.inject(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_blue_box, menu);
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

    //BUTTON BINDING

    @OnClick(R.id.button_1)
    void button1Click() {
        playTone(1);
    }

    @OnClick(R.id.button_2)
    void button2Click() {
        playTone(2);
    }

    @OnClick(R.id.button_3)
    void button3Click() {
        playTone(3);
    }

    @OnClick(R.id.button_4)
    void button4Click() {
        playTone(4);
    }

    @OnClick(R.id.button_5)
    void button5Click() {
        playTone(5);
    }

    @OnClick(R.id.button_6)
    void button6Click() {
        playTone(6);
    }

    @OnClick(R.id.button_7)
    void button7Click() {
        playTone(7);
    }

    @OnClick(R.id.button_8)
    void button8Click() {
        playTone(8);
    }

    @OnClick(R.id.button_9)
    void button9Click() {
        playTone(9);
    }

    @OnClick(R.id.button_0)
    void button0Click() {
        playTone(0);
    }

    @OnClick(R.id.button_star)
    void buttonStarClick() {
        playTone(STAR);
    }

    @OnClick(R.id.button_pound)
    void buttonPoudClick() {
        playTone(POUND);
    }

    @OnClick(R.id.button_2600)
    void button2600Click() {
        playTone(TONE_2600);
    }
    // MEDIA PLAYING
    private void playTone(int i) {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.tone2600);
        mediaPlayer.start();
    }

}
