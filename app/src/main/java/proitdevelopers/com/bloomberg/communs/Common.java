package proitdevelopers.com.bloomberg.communs;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.krishna.fileloader.FileLoader;
import com.krishna.fileloader.listener.FileRequestListener;
import com.krishna.fileloader.pojo.FileResponse;
import com.krishna.fileloader.request.FileLoadRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import proitdevelopers.com.bloomberg.modelo.AudioModel;

public class Common {

    private static String TAG = "FalhaSis";

    public static String msgErroSemResultados = "Sem resultados";
    public static String msgAprocessar = "A processar...!";
    public static String msgErroTentar = "Tentar de Novo";
    public static String msgSemResultadoJornais = "Sem resultados dos jornais Mercado!";


    public static String METADATA_KEY_DURATION ="0";
    public static String audioUri="https://www.android-examples.com/wp-content/uploads/2016/04/Thunder-rumble.mp3";
    public static String audioUri2="https://file-examples.com/wp-content/uploads/2017/11/file_example_MP3_5MG.mp3";

    public static List<AudioModel> recordingItemList;

    public static List<AudioModel> getAllNews(){

        recordingItemList=new ArrayList<>();
        recordingItemList.add(new AudioModel("setember 1","1 Sydney News - Breaking local news",audioUri));
        recordingItemList.add(new AudioModel("setember 2","2 World News - International Headlines",audioUri2));
        recordingItemList.add(new AudioModel("setember 3","3 NBC News",audioUri));
        recordingItemList.add(new AudioModel("setember 4","4 World News - International News",audioUri2));

        recordingItemList.add(new AudioModel("setember 5","5 World News - International Headlines",audioUri2));
        recordingItemList.add(new AudioModel("setember 6","6 Sydney News - Latest news today",audioUri));
        recordingItemList.add(new AudioModel("setember 7","7 World News",audioUri2));
        recordingItemList.add(new AudioModel("setember 8","8 World News - Breaking Sky News",audioUri2));



        return recordingItemList;
    }

    private static String getDuration(String file) {
        String time ="";
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(file);

            time = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            mediaMetadataRetriever.release();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return time;
    }

    public static void getDurationTime(Context context, AudioModel item, final TextView mFileLengthTextView) {

        FileLoader.with(context).load(item.getFileLink())
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest fileLoadRequest, FileResponse<File> fileResponse) {

                        File audioFile = fileResponse.getBody();
                        METADATA_KEY_DURATION = getDuration(audioFile.getAbsolutePath());
                        long itemDuration = Long.parseLong(METADATA_KEY_DURATION);
                        long minutes = TimeUnit.MILLISECONDS.toMinutes(itemDuration);
                        long seconds = TimeUnit.MILLISECONDS.toSeconds(itemDuration) - TimeUnit.MINUTES.toSeconds(minutes);


                        mFileLengthTextView.setText(String.format("%02d:%02d", minutes,seconds));



                    }

                    @Override
                    public void onError(FileLoadRequest fileLoadRequest, Throwable throwable) {

                    }
                });
    }


    public static void mostrarMensagemTextView(TextView textView, String valorString) {
        textView.setText(valorString);
    }

    public static void mostrarMensagem(Context mContexto, int mensagem) {
        Toast.makeText(mContexto,mensagem,Toast.LENGTH_SHORT).show();
    }



    public static boolean conexaoInternetTrafego(Context context){
        String site = "www.google.com";
        WebView webViewInternet = new WebView(context);
        final boolean[] valorRetorno = new boolean[1];

        webViewInternet.setWebViewClient(new WebViewClient());
        webViewInternet.loadUrl(site);

        webViewInternet.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
                super.onReceivedSslError(view, handler, error);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String descricaoErro, String failingUrl) {
                super.onReceivedError(view, errorCode, descricaoErro, failingUrl);
                if (errorCode == -2) {
                    valorRetorno[0] = false;
                    Log.i(TAG,"webView ERROR " + descricaoErro );
                    Log.i(TAG,"webView ERROR " + errorCode );
                }
            }
        });

        webViewInternet.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                valorRetorno[0] = true;
                Log.i(TAG,"webView " + progress );
            }
        });
        Log.i(TAG,"webView " + valorRetorno[0]);

        return valorRetorno[0];
    }

}
