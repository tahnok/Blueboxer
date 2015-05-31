package me.tahnok.blueboxer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class BlueBoxActivity extends Activity {

  private static final String LOG_TAG = BlueBoxActivity.class.getSimpleName();
  public static final String GITHUB_SOURCE = "https://github.com/tahnok/Blueboxer";
  public static final String WIKIPEDIA_ARTICLE = "https://en.wikipedia.org/wiki/Blue_box";


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_blue_box);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_blue_box, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
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

}
