package proitdevelopers.com.bloomberg.activitys.quiosque;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;
import proitdevelopers.com.bloomberg.R;

@SuppressLint("SetJavaScriptEnabled")
public class RevistaViewActivity extends AppCompatActivity {

    private Toolbar toolbar;
    String viewType;

    private WebView webView;
    private RelativeLayout coordinatorLayout;
    private RelativeLayout errorLayout;
    private TextView btnTentarDeNovo;

    private LinearLayout progressBar;
    RingProgressBar anelprogressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revista_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Media Rumo");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        webView = (WebView) findViewById(R.id.webViewPdf);
        coordinatorLayout = (RelativeLayout) findViewById(R.id.coordinatorLayout);
        errorLayout = (RelativeLayout) findViewById(R.id.erroLayout);
        btnTentarDeNovo = (TextView) findViewById(R.id.btn);
        btnTentarDeNovo.setText("Tentar de Novo");
        btnTentarDeNovo.setTextColor(getResources().getColor(R.color.colorExemplo));

        progressBar = (LinearLayout) findViewById(R.id.linearProgresso);
        anelprogressbar = (RingProgressBar) findViewById(R.id.progressbar_1);

        if (getIntent() != null){
            viewType = getIntent().getStringExtra("ViewType");
            if (viewType != null || !TextUtils.isEmpty(viewType)){
                verifConecxao(viewType);
            }
        }
    }

    private void verifConecxao(String viewType) {

        if (getBaseContext() != null){
            ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

            if (conMgr!=null){
                NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
                if (netInfo == null){
                    mostarMsnErro();
                    progressBar.setVisibility(ProgressBar.INVISIBLE);
                }else{
                    carregarPDFView(viewType);
                }
            }


        }

    }


    private void carregarPDFView(String pdf){

        progressBar.setVisibility(ProgressBar.VISIBLE);
        anelprogressbar.setVisibility(View.VISIBLE);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://docs.google.com/gview?embedded=true&url="+pdf);
//        webView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url="+pdf);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('ndfHFb-c4YZDc-GSQQnc-LgbsSe ndfHFb-c4YZDc-to915-LgbsSe VIpgJd-TzA9Ye-eEGnhe ndfHFb-c4YZDc-LgbsSe')[0].style.display='none'; })()");
//                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                progressBar.setVisibility(View.VISIBLE);


            }
        });
        webView.setWebChromeClient(new WebChromeClient() {

            public void onProgressChanged(WebView view, int progress) {

                view.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('ndfHFb-c4YZDc-GSQQnc-LgbsSe ndfHFb-c4YZDc-to915-LgbsSe VIpgJd-TzA9Ye-eEGnhe ndfHFb-c4YZDc-LgbsSe')[0].style.display='none'; })()");

                if (progress < 100){
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                    anelprogressbar.setProgress(progress);
                }

                if(progress < 100 && progressBar.getVisibility() == ProgressBar.GONE){
                    anelprogressbar.setProgress(progress);
                    progressBar.setVisibility(ProgressBar.VISIBLE);


                }

                if(progress == 100) {
                    progressBar.setVisibility(ProgressBar.GONE);
                    anelprogressbar.setVisibility(View.GONE);
                }
            }
        });



    }

    private void mostarMsnErro(){

        if (errorLayout.getVisibility() == View.GONE){
            errorLayout.setVisibility(View.VISIBLE);
            coordinatorLayout.setVisibility(View.GONE);
        }

        btnTentarDeNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coordinatorLayout.setVisibility(View.VISIBLE);
                errorLayout.setVisibility(View.GONE);
                verifConecxao(viewType);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.revista_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;

            case R.id.action_refresh:
                verifConecxao(viewType);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(@NotNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

}
