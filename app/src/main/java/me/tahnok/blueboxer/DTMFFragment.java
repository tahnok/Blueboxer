package me.tahnok.blueboxer;

import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DTMFFragment extends Fragment {

  public static DTMFFragment newInstance() {
    DTMFFragment fragment = new DTMFFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_dtmf, container, false);
    ButterKnife.inject(this, view);
    return view;
  }

  @OnClick(R.id.button_1)
  void clickButton() {
    ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_MUSIC, ToneGenerator.MAX_VOLUME);
    tg.startTone(ToneGenerator.TONE_DTMF_1, 120);
  }
}
