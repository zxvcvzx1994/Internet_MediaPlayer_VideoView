package vo.cvcompany.com.myapplication;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG =MainActivity.class.getSimpleName() ;
    private String url  ="http://192.168.1.11/music/1234ChiDan.mp3";
    private String urlMp4 = "http://192.168.1.11/music/TuMinhThanhDuy.mp4";
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.videoView)
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnPlay)
    public void btnPlay(){
        Log.i(TAG, "btnPlay: ");
        final MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync();
            progressBar.setVisibility(View.VISIBLE);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    progressBar.setVisibility(View.GONE);
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.btnPlay1)
    public void btnPlay1(){
        videoView.setVideoURI(Uri.parse(urlMp4));
        videoView.start();
        videoView.setMediaController(new MediaController(this));
    }
}
