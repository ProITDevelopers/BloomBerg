package proitdevelopers.com.bloomberg.communs;

import android.content.Context;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class Common {

    private static String TAG = "FalhaSis";

    public static String msgErroSemResultados = "Sem resultados";
    public static String msgAprocessar = "A processar...!";
    public static String msgErroTentar = "Tentar de Novo";
    public static String msgSemResultadoJornais = "Sem resultados dos jornais Mercado!";

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
