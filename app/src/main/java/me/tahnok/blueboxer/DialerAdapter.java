package me.tahnok.blueboxer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class DialerAdapter extends FragmentPagerAdapter {
  @Override
  public CharSequence getPageTitle(int position) {
    switch (position) {
      case 0:
        return "MF";
      case 1:
        return "DTMF";
      default:
        return "???????";
    }
  }

  @Override
  public int getCount() {
    return 2;
  }

  @Override
  public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return MFFragment.newInstance();
      case 1:
        return DTMFFragment.newInstance();
      default:
        return null;
    }
  }

  public DialerAdapter(FragmentManager fm) {
    super(fm);
  }
}
