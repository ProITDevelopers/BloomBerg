package proitdevelopers.com.bloomberg.fragmentos.principalSessaoIniciada;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import dmax.dialog.SpotsDialog;
import proitdevelopers.com.bloomberg.communs.Common;
import proitdevelopers.com.bloomberg.modelo.AudioModel;
import proitdevelopers.com.bloomberg.interfaces.ItemClickListener;
import proitdevelopers.com.bloomberg.R;
import proitdevelopers.com.bloomberg.adapter.AudioAdapterRecycler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static proitdevelopers.com.bloomberg.communs.Common.mostrarMensagemTextView;
import static proitdevelopers.com.bloomberg.communs.Common.msgErroTentar;


public class AudioFragment extends Fragment {



    private RecyclerView mRecyclerView;
    private TextView title;


    private FrameLayout video_content;
    private ProgressBar progressBar;

    private static final String LOG_TAG = "FileViewerFragment";
    private List<AudioModel>recordingItemList;
    private AudioModel item;

    private Handler mHandler = new Handler();

    private MediaPlayer mMediaPlayer = null;

    private SeekBar mSeekBar = null;
    private FloatingActionButton mPlayButton = null;
    private TextView mCurrentProgressTextView = null;
    private TextView mFileNameTextView = null;
    private TextView mFileLengthTextView = null;


    private LinearLayoutManager llm;


    //stores whether or not the mediaplayer is currently playing audio
    private boolean isPlaying = false;



    private RelativeLayout errorLayout;
    private TextView btnTentarDeNovo;
    private ConstraintLayout linearLayoutCarregando;

    public AudioFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_audio, container, false);

        initViews(v);


//        img = (ImageView) v.findViewById(R.id.frag_mais_btn_voltar);




//        loadNews();


//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        mRecyclerView.setLayoutManager(linearLayoutManager);



        verifConecxao();

        return v;
    }

    private void initViews(View v) {


        linearLayoutCarregando = v.findViewById(R.id.constraintLayout);
        errorLayout = v.findViewById(R.id.erroLayout);
        btnTentarDeNovo = v.findViewById(R.id.btn);
        mostrarMensagemTextView(btnTentarDeNovo,msgErroTentar);
        btnTentarDeNovo.setTextColor(getResources().getColor(R.color.colorExemplo));


        video_content =  v.findViewById(R.id.img_destaque);
        progressBar = v.findViewById(R.id.progressBar);
        title = (TextView) v.findViewById(R.id.destaque_noticia_tv);


        mFileNameTextView = (TextView) v.findViewById(R.id.file_name_text_view);
        mFileLengthTextView = (TextView) v.findViewById(R.id.file_length_text_view);
        mCurrentProgressTextView = (TextView) v.findViewById(R.id.current_progress_text_view);

        mSeekBar = (SeekBar) v.findViewById(R.id.seekbar);
        mPlayButton = (FloatingActionButton) v.findViewById(R.id.fab_play);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerTendencias);
        mRecyclerView.setHasFixedSize(true);
        llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        //newest to oldest order (database stores from oldest to newest)
        llm.setReverseLayout(true);
        llm.setStackFromEnd(true);

        mRecyclerView.setLayoutManager(llm);








    }

    private void mostarMsnErro(){

        if (errorLayout.getVisibility() == View.GONE){
            errorLayout.setVisibility(View.VISIBLE);
            linearLayoutCarregando.setVisibility(View.GONE);
        }

        btnTentarDeNovo.setOnClickListener(view -> {
            linearLayoutCarregando.setVisibility(View.VISIBLE);
            errorLayout.setVisibility(View.GONE);
            verifConecxao();
        });
    }


    private void setRandomNews(AudioModel item) {

        title.setText(item.getTitle());
        mFileNameTextView.setText(item.getTitle());
        Common.getDurationTime(getContext(),item, mFileLengthTextView);

        if (mMediaPlayer != null) {
            stopPlaying();
        }
        if(mMediaPlayer == null) {
            isPlaying = !isPlaying;
            startPlaying(item); //start from beginning
        } else {
            resumePlaying(); //resume the currently paused MediaPlayer
        }



    }

    @Override
    public void onPause() {
        super.onPause();

        if (mMediaPlayer != null) {
            stopPlaying();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mMediaPlayer != null) {
            stopPlaying();
        }
    }

    // Play start/stop
    private void onPlay(boolean isPlaying, AudioModel item){
        if (!isPlaying) {
            //currently MediaPlayer is not playing audio
            if(mMediaPlayer == null) {
                startPlaying(item); //start from beginning
            } else {
                resumePlaying(); //resume the currently paused MediaPlayer
            }

        } else {
            //pause the MediaPlayer
            pausePlaying();
        }
    }

    private void startPlaying(AudioModel item) {
        AlertDialog waitingDialog = new SpotsDialog.Builder().setContext(getContext()).build();
        waitingDialog.setMessage("Carregando...");
        waitingDialog.show();
        mPlayButton.setImageResource(R.drawable.ic_media_pause);
        mMediaPlayer = new MediaPlayer();

        Uri myUri = Uri.parse(item.getFileLink());
        try {
//            mMediaPlayer.setDataSource(item.getFilePath());
            if (getActivity()!=null)
                mMediaPlayer.setDataSource(getActivity(), myUri);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.prepare();
            mSeekBar.setMax(mMediaPlayer.getDuration());

            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayer.start();
                    waitingDialog.dismiss();
                }
            });
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlaying();
            }
        });

        updateSeekBar();

        //keep screen on while playing audio
        if (getActivity()!=null)
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    private void prepareMediaPlayerFromPoint(int progress, AudioModel item) {
        //set mediaPlayer to start from middle of the audio file

        mMediaPlayer = new MediaPlayer();

        Uri myUri = Uri.parse(item.getFileLink());
        try {
//            mMediaPlayer.setDataSource(item.getFilePath());
            if (getActivity()!=null)
                mMediaPlayer.setDataSource(getActivity(), myUri);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.prepare();
            mSeekBar.setMax(mMediaPlayer.getDuration());
            mMediaPlayer.seekTo(progress);

            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlaying();
                }
            });

        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        //keep screen on while playing audio
        if (getActivity()!=null)
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    private void pausePlaying() {
        mPlayButton.setImageResource(R.drawable.ic_media_play);
        mHandler.removeCallbacks(mRunnable);
        mMediaPlayer.pause();
    }

    private void resumePlaying() {
        mPlayButton.setImageResource(R.drawable.ic_media_pause);
        mHandler.removeCallbacks(mRunnable);
        mMediaPlayer.start();
        updateSeekBar();
    }

    private void stopPlaying() {
        mPlayButton.setImageResource(R.drawable.ic_media_play);
        mHandler.removeCallbacks(mRunnable);
        mMediaPlayer.stop();
        mMediaPlayer.reset();
        mMediaPlayer.release();
        mMediaPlayer = null;

        mSeekBar.setProgress(mSeekBar.getMax());
        isPlaying = !isPlaying;

        mCurrentProgressTextView.setText(mFileLengthTextView.getText());
        mSeekBar.setProgress(mSeekBar.getMax());



        //allow the screen to turn off again once audio is finished playing
        if (getActivity()!=null)
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    //updating mSeekBar
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if(mMediaPlayer != null){

                int mCurrentPosition = mMediaPlayer.getCurrentPosition();
                mSeekBar.setProgress(mCurrentPosition);

                long minutes = TimeUnit.MILLISECONDS.toMinutes(mCurrentPosition);
                long seconds = TimeUnit.MILLISECONDS.toSeconds(mCurrentPosition)
                        - TimeUnit.MINUTES.toSeconds(minutes);
                mCurrentProgressTextView.setText(String.format("%02d:%02d", minutes, seconds));

                updateSeekBar();
            }
        }
    };

    private void updateSeekBar() {
        mHandler.postDelayed(mRunnable, 1000);
    }

    private void verifConecxao() {

        AlertDialog waitingDialog = new SpotsDialog.Builder().setContext(getContext()).build();
        waitingDialog.setMessage("Carregando...");
        waitingDialog.show();

        if (getContext()!=null) {
            ConnectivityManager conMgr =  (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (conMgr!=null) {
                NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
                if (netInfo == null){
                    waitingDialog.dismiss();
                    mPlayButton.setEnabled(false);
                    mSeekBar.setEnabled(false);
                    Toast.makeText(getContext(), "Network", Toast.LENGTH_SHORT).show();
                    mostarMsnErro();
                } else {
                    carregarAudios(waitingDialog);
                }
            }

        }

    }

    private void carregarAudios(final AlertDialog waitingDialog) {

        mPlayButton.setEnabled(true);
        mSeekBar.setEnabled(true);

        recordingItemList = Common.getAllNews();

        item = recordingItemList.get(recordingItemList.size()-1);

        title.setText(item.getTitle());
        mFileNameTextView.setText(item.getTitle());
        Common.getDurationTime(getContext(),item,mFileLengthTextView);

        ColorFilter filter = new LightingColorFilter
                (getResources().getColor(R.color.primary), getResources().getColor(R.color.primary));
        mSeekBar.getProgressDrawable().setColorFilter(filter);
        mSeekBar.getThumb().setColorFilter(filter);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mMediaPlayer != null && fromUser) {
                    mMediaPlayer.seekTo(progress);
                    mHandler.removeCallbacks(mRunnable);

                    long minutes = TimeUnit.MILLISECONDS.toMinutes(mMediaPlayer.getCurrentPosition());
                    long seconds = TimeUnit.MILLISECONDS.toSeconds(mMediaPlayer.getCurrentPosition())
                            - TimeUnit.MINUTES.toSeconds(minutes);
                    mCurrentProgressTextView.setText(String.format("%02d:%02d", minutes,seconds));

                    updateSeekBar();

                } else if (mMediaPlayer == null && fromUser) {
                    prepareMediaPlayerFromPoint(progress,item);
                    updateSeekBar();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if(mMediaPlayer != null) {
                    // remove message Handler from updating progress bar
                    mHandler.removeCallbacks(mRunnable);
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mMediaPlayer != null) {
                    mHandler.removeCallbacks(mRunnable);
                    mMediaPlayer.seekTo(seekBar.getProgress());

                    long minutes = TimeUnit.MILLISECONDS.toMinutes(mMediaPlayer.getCurrentPosition());
                    long seconds = TimeUnit.MILLISECONDS.toSeconds(mMediaPlayer.getCurrentPosition())
                            - TimeUnit.MINUTES.toSeconds(minutes);
                    mCurrentProgressTextView.setText(String.format("%02d:%02d", minutes,seconds));
                    updateSeekBar();
                }
            }
        });


        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlay(isPlaying,item);
                isPlaying = !isPlaying;
            }
        });

        AudioAdapterRecycler mFileViewerAdapter = new AudioAdapterRecycler(getActivity(),recordingItemList);
        mRecyclerView.setAdapter(mFileViewerAdapter);


        mFileViewerAdapter.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                item = recordingItemList.get(position);
                setRandomNews(item);

            }
        });



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                waitingDialog.dismiss();
            }
        }, 3000);






    }




//    private void setVideoAdapter(List<AudioModel> audioModelList){
//        adapter = new AudioAdapterRecycler(getContext(), audioModelList);
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//
//
//        adapter.setClickListener(new ItemClickListener() {
//            @Override
//            public void onClick(View view, int position) {
////                setRandomNews(position);
//            }
//        });
//    }




}
