package me.tahnok.blueboxer;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class BlueBoxActivity extends Activity {

  public static final int STAR = 10;
  public static final int POUND = 11;
  public static final int TONE_2600 = 12;
  private static final String LOG_TAG = BlueBoxActivity.class.getSimpleName();
  public static final String GITHUB_SOURCE = "https://github.com/tahnok/Blueboxer";
  public static final String WIKIPEDIA_ARTICLE = "https://en.wikipedia.org/wiki/Blue_box";

  //VIEWS
  @InjectView(R.id.button_1) Button button_1;

  final ArrayBlockingQueue<Integer> integerQueue = new ArrayBlockingQueue<>(100);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_blue_box);
    ButterKnife.inject(this);
    setFontForActionbar();

    Observable<Integer> soundQueue = Observable.create(new Observable.OnSubscribe<Integer>() {
      @Override
      public void call(Subscriber<? super Integer> subscriber) {
        while(true) {
          try {
            subscriber.onNext(integerQueue.take());
          } catch (InterruptedException e) {
            subscriber.onError(e);
          }
        }
      }
    });

    soundQueue
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe(new Action1<Integer>() {
              @Override
              public void call(Integer integer) {
                blockingPlayTone(integer);
              }
            });
  }

  private void setFontForActionbar() {

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
    switch (id) {
      case R.id.action_view_source:
        viewSource();
        break;
      case R.id.action_more_info:
        moreInfo();
        break;
    }

    return super.onOptionsItemSelected(item);
  }

  private void moreInfo() {
    startActivity(openUrl(WIKIPEDIA_ARTICLE));
  }

  private void viewSource() {
    startActivity(openUrl(GITHUB_SOURCE));
  }

  private Intent openUrl(String url) {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse(url));
    return intent;
  }

  //BUTTON BINDING

  @OnClick(R.id.button_1)
  void button1Click() {
    queueTone(1);
  }

  @OnClick(R.id.button_2)
  void button2Click() {
    queueTone(2);
  }

  @OnClick(R.id.button_3)
  void button3Click() {
    queueTone(3);
  }

  @OnClick(R.id.button_4)
  void button4Click() {
    queueTone(4);
  }

  @OnClick(R.id.button_5)
  void button5Click() {
    queueTone(5);
  }

  @OnClick(R.id.button_6)
  void button6Click() {
    queueTone(6);
  }

  @OnClick(R.id.button_7)
  void button7Click() {
    queueTone(7);
  }

  @OnClick(R.id.button_8)
  void button8Click() {
    queueTone(8);
  }

  @OnClick(R.id.button_9)
  void button9Click() {
    queueTone(9);
  }

  @OnClick(R.id.button_0)
  void button0Click() {
    queueTone(0);
  }

  @OnClick(R.id.button_star)
  void buttonStarClick() {
    queueTone(STAR);
  }

  @OnClick(R.id.button_pound)
  void buttonPoudClick() {
    queueTone(POUND);
  }

  @OnClick(R.id.button_2600)
  void button2600Click() {
    queueTone(TONE_2600);
  }
  // MEDIA PLAYING
  private void queueTone(int i) {
    try {
      integerQueue.put(i);
    } catch (InterruptedException e) {
      Log.e(LOG_TAG, "????????", e);
    }
  }

  private void blockingPlayTone(int tone_id) {
    MediaPlayer mediaPlayer = MediaPlayer.create(this, getToneId(tone_id));
    final CountDownLatch latch = new CountDownLatch(1);
    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
      @Override
      public void onCompletion(MediaPlayer mediaPlayer) {
        latch.countDown();
      }
    });
    mediaPlayer.start();
    try {
      latch.await();
    } catch (InterruptedException e) {
      Log.e(LOG_TAG, "couldn't wait", e);
    }
    mediaPlayer.release();
  }

  private int getToneId(int tone_id) {
    switch(tone_id) {
      case TONE_2600:
        return R.raw.tone2600;
      case 0:
        return R.raw.tone_0;
      case 1:
        return R.raw.tone_1;
      case 2:
        return R.raw.tone_2;
      case 3:
        return R.raw.tone_3;
      case 4:
        return R.raw.tone_4;
      case 5:
        return R.raw.tone_5;
      case 6:
        return R.raw.tone_6;
      case 7:
        return R.raw.tone_7;
      case 8:
        return R.raw.tone_8;
      case 9:
        return R.raw.tone_9;
      case POUND:
        return R.raw.tone_kp;
      case STAR:
        return R.raw.tone_st;
      default:
        return R.raw.tone2600;
    }
  }
}
