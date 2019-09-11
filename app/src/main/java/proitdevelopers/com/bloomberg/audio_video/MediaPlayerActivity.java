package proitdevelopers.com.bloomberg.audio_video;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import com.google.android.exoplayer2.ui.PlayerView;
import proitdevelopers.com.bloomberg.R;

public class MediaPlayerActivity extends AppCompatActivity implements CallBacks.playerCallBack {

    String mFilePath = null;
    private static final String FILE_PATH = "PlayerActivity";
    PlayerView mPlayerView;

    private final String STATE_PLAYER_FULLSCREEN = "playerFullscreen";
    private boolean mExoPlayerFullscreen = false;
    private ImageView mFullScreenIcon;

    public static Intent getStartIntent(Context context, String mFilePath) {
        Intent intent = new Intent(context, MediaPlayerActivity.class);
        intent.putExtra(FILE_PATH, mFilePath);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);

        if (getIntent().hasExtra(FILE_PATH)) {
            mFilePath = getIntent().getStringExtra(FILE_PATH);
        }

        mPlayerView = findViewById(R.id.mPlayerView);
        mPlayerView.setPlayer(PlayerManager.getSharedInstance(MediaPlayerActivity.this).getPlayerView().getPlayer());
        PlayerManager.getSharedInstance(MediaPlayerActivity.this).playStream(mFilePath);
        PlayerManager.getSharedInstance(this).setPlayerListener(this);

        // Set the fullscreen button to "close fullscreen" icon
        mFullScreenIcon = mPlayerView.findViewById(R.id.exo_fullscreen_icon);
        mFullScreenIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mExoPlayerFullscreen)
                    openFullscreenDialog();
                else
                    closeFullscreenDialog();
            }
        });
    }

    @Override
    public void onItemClickOnItem(Integer albumId) {

    }

    @Override
    public void onPlayingEnd() {
        PlayerManager.getSharedInstance(MediaPlayerActivity.this).pausePlayer();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        PlayerManager.getSharedInstance(MediaPlayerActivity.this).pausePlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        PlayerManager.getSharedInstance(MediaPlayerActivity.this).resumePlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PlayerManager.getSharedInstance(MediaPlayerActivity.this).pausePlayer();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(MediaPlayerActivity.this, R.drawable.ic_fullscreen_skrink));
            if (getSupportActionBar()!=null){
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                getSupportActionBar().hide();
            }


        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(MediaPlayerActivity.this, R.drawable.ic_fullscreen_expand));
            if (getSupportActionBar()!=null){
                getSupportActionBar().show();
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

            }


        }


    }



    private void openFullscreenDialog() {

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(MediaPlayerActivity.this, R.drawable.ic_fullscreen_skrink));
        mExoPlayerFullscreen = true;

    }


    private void closeFullscreenDialog() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mExoPlayerFullscreen = false;
        mFullScreenIcon.setImageDrawable(ContextCompat.getDrawable(MediaPlayerActivity.this, R.drawable.ic_fullscreen_expand));
    }


}
