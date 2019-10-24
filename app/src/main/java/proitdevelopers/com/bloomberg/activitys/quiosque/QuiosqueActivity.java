package proitdevelopers.com.bloomberg.activitys.quiosque;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;


import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;
import proitdevelopers.com.bloomberg.R;
import proitdevelopers.com.bloomberg.adapter.RevistasAdapter;
import proitdevelopers.com.bloomberg.dbConnection.ApiClient;
import proitdevelopers.com.bloomberg.dbConnection.ApiInterface;
import proitdevelopers.com.bloomberg.modelo.Revistas;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static proitdevelopers.com.bloomberg.communs.Common.conexaoInternetTrafego;
import static proitdevelopers.com.bloomberg.communs.Common.mostrarMensagem;
import static proitdevelopers.com.bloomberg.communs.Common.mostrarMensagemTextView;
import static proitdevelopers.com.bloomberg.communs.Common.msgAprocessar;
import static proitdevelopers.com.bloomberg.communs.Common.msgErroSemResultados;
import static proitdevelopers.com.bloomberg.communs.Common.msgErroTentar;
import static proitdevelopers.com.bloomberg.communs.Common.msgSemResultadoJornais;

public class QuiosqueActivity extends AppCompatActivity {

    Toolbar toolbarQuiosque;

    private RelativeLayout errorLayout;
    private TextView btnTentarDeNovo;


    private LinearLayout linearLayoutCarregando,linearCarregarMercado,linearCarregarVanguarda,linearCarregarRumo;
    private AVLoadingIndicatorView progressMercado,progressVanguarda,progressRumo;
    private TextView txtCarregandoMercado,txtCarregandoVanguarda,txtCarregandoRumo;
    private CardView cardMercado,cardVanguarda,cardRumo;

    private TextSwitcher mTitle,mTitle2,mTitle3;
    private FeatureCoverFlow coverFlow,coverFlow2,coverFlow3;

    private List<Revistas> mercadoList,vanguardaList,rumoList;


    int indexMercado,indexVanguarda,indexRumo=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiosque);

        toolbarQuiosque = findViewById(R.id.toolbarQuiosque);
        toolbarQuiosque.setTitle("Quiosque");
        setSupportActionBar(toolbarQuiosque);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        initView();

        verifConecxaoRevistas();
    }

    private void initView() {

        Animation in = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_in_top);
        Animation out = AnimationUtils.loadAnimation(getBaseContext(), R.anim.slide_out_bottom);
        errorLayout = findViewById(R.id.erroLayout);
        btnTentarDeNovo = findViewById(R.id.btn);
        mostrarMensagemTextView(btnTentarDeNovo,msgErroTentar);
        btnTentarDeNovo.setTextColor(getResources().getColor(R.color.colorExemplo));

        linearLayoutCarregando = findViewById(R.id.linearLayoutCarregando);
        linearCarregarMercado = findViewById(R.id.linearCarregarMercado);
        linearCarregarVanguarda = findViewById(R.id.linearCarregarVanguarda);
        linearCarregarRumo = findViewById(R.id.linearCarregarRumo);
        progressMercado = findViewById(R.id.progressMercado);
        progressVanguarda = findViewById(R.id.progressVanguarda);
        progressRumo = findViewById(R.id.progressRumo);
        txtCarregandoMercado = findViewById(R.id.txtCarregandoMercado);
        txtCarregandoVanguarda = findViewById(R.id.txtCarregandoVanguarda);
        txtCarregandoRumo = findViewById(R.id.txtCarregandoRumo);
        cardMercado = findViewById(R.id.cardMercado);
        cardVanguarda = findViewById(R.id.cardVanguarda);
        cardRumo = findViewById(R.id.cardRumo);

        mostrarMensagemTextView(txtCarregandoMercado,msgAprocessar);
        mostrarMensagemTextView(txtCarregandoVanguarda,msgAprocessar);
        mostrarMensagemTextView(txtCarregandoRumo,msgAprocessar);

        mTitle = (TextSwitcher)findViewById(R.id.title);
        mTitle.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater = LayoutInflater.from(getBaseContext());
                TextView txt = (TextView)inflater.inflate(R.layout.layout_title,null);
                return txt;
            }
        });
        mTitle.setInAnimation(in);
        mTitle.setOutAnimation(out);

        mTitle2 = (TextSwitcher)findViewById(R.id.title2);
        mTitle2.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater = LayoutInflater.from(getBaseContext());
                TextView txt = (TextView)inflater.inflate(R.layout.layout_title,null);
                return txt;
            }
        });
        mTitle2.setInAnimation(in);
        mTitle2.setOutAnimation(out);

        mTitle3 = (TextSwitcher)findViewById(R.id.title3);
        mTitle3.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater = LayoutInflater.from(getBaseContext());
                TextView txt = (TextView)inflater.inflate(R.layout.layout_title,null);
                return txt;
            }
        });

        mTitle3.setInAnimation(in);
        mTitle3.setOutAnimation(out);

        coverFlow = (FeatureCoverFlow)findViewById(R.id.coverflow);
        coverFlow2 = (FeatureCoverFlow)findViewById(R.id.coverflow2);
        coverFlow3 = (FeatureCoverFlow)findViewById(R.id.coverflow3);



    }

    private void verifConecxaoRevistas() {

        ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr!=null) {
            NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
            if (netInfo == null){
                mostarMsnErro();
            } else {
                carregarRevistas();
            }
        }

    }

    private void mostarMsnErro(){

        if (errorLayout.getVisibility() == View.GONE){
            errorLayout.setVisibility(View.VISIBLE);
            linearLayoutCarregando.setVisibility(View.GONE);
        }

        btnTentarDeNovo.setOnClickListener(view -> {
            linearLayoutCarregando.setVisibility(View.VISIBLE);
            errorLayout.setVisibility(View.GONE);
            verifConecxaoRevistas();
        });
    }

    private void carregarRevistas() {
        ApiInterface apiInterface = ApiClient.apiClient().create(ApiInterface.class);
        Call<List<Revistas>> rv = apiInterface.getRevistas();
        rv.enqueue(new Callback<List<Revistas>>() {
            @Override
            public void onResponse(@NonNull Call<List<Revistas>> call, @NonNull Response<List<Revistas>> response) {

                mercadoList = new ArrayList<>();
                vanguardaList = new ArrayList<>();
                rumoList = new ArrayList<>();

                if (!response.isSuccessful()) {
                    progressMercado.setVisibility(View.GONE);
                    progressVanguarda.setVisibility(View.GONE);
                    progressRumo.setVisibility(View.GONE);
                    mostrarMensagemTextView(txtCarregandoMercado,msgErroSemResultados);
                    mostrarMensagemTextView(txtCarregandoVanguarda,msgErroSemResultados);
                    mostrarMensagemTextView(txtCarregandoRumo,msgErroSemResultados);
                } else {



                    if (response.body()!=null){

                        filtrarRevistas(response.body());

                    } else {
                        progressMercado.setVisibility(View.GONE);
                        progressVanguarda.setVisibility(View.GONE);
                        progressRumo.setVisibility(View.GONE);
                        mostrarMensagemTextView(txtCarregandoMercado,msgErroSemResultados);
                        mostrarMensagemTextView(txtCarregandoVanguarda,msgErroSemResultados);
                        mostrarMensagemTextView(txtCarregandoRumo,msgErroSemResultados);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Revistas>> call, @NonNull Throwable t) {
                progressMercado.setVisibility(View.GONE);
                progressVanguarda.setVisibility(View.GONE);
                progressRumo.setVisibility(View.GONE);
                mostrarMensagemTextView(txtCarregandoMercado,msgErroSemResultados);
                mostrarMensagemTextView(txtCarregandoVanguarda,msgErroSemResultados);
                mostrarMensagemTextView(txtCarregandoRumo,msgErroSemResultados);
                if (!conexaoInternetTrafego(QuiosqueActivity.this)){
                    mostrarMensagem(QuiosqueActivity.this,R.string.txtMsg);
                }else  if ("timeout".equals(t.getMessage())) {
                    mostrarMensagem(QuiosqueActivity.this,R.string.txtTimeout);
                }else {
                    mostrarMensagem(QuiosqueActivity.this,R.string.txtProblemaMsg);
                }
            }
        });



    }


    private void filtrarRevistas(List<Revistas> revistasList){

        // Order the list by regist date.
        Collections.sort(revistasList, new Revistas());

        for (int i = 0; i <revistasList.size() ; i++) {
            Revistas revistas = new Revistas(Integer.parseInt(String.valueOf(revistasList.get(i).id_jornal)),
                    String.valueOf(revistasList.get(i).nome), String.valueOf(revistasList.get(i).fotoJornal),
                    String.valueOf(revistasList.get(i).link), String.valueOf(revistasList.get(i).categoria),
                    String.valueOf(revistasList.get(i).dataEdicao), String.valueOf(revistasList.get(i).descricao));

//            if (revistas.getCategoria().equals("mercado") || revistas.getCategoria().equals("Mercado")){
            if (revistas.getCategoria().equalsIgnoreCase("mercado")){

                if(!revistas.getNome().equals("") && !revistas.getNome().isEmpty() ||
                        !revistas.getFotoJornal().equals("") && !revistas.getFotoJornal().isEmpty() ||
                        !revistas.getLink().equals("") &&!revistas.getLink().isEmpty() ||
                        !revistas.getDataEdicao().equals("") &&!revistas.getDataEdicao().isEmpty()){

                    mercadoList.add(revistas);
                }

            }

            if (revistas.getCategoria().equalsIgnoreCase("vanguarda")){

                if(!revistas.getNome().equals("") && !revistas.getNome().isEmpty() ||
                        !revistas.getFotoJornal().equals("") && !revistas.getFotoJornal().isEmpty() ||
                        !revistas.getLink().equals("") &&!revistas.getLink().isEmpty() ||
                        !revistas.getDataEdicao().equals("") &&!revistas.getDataEdicao().isEmpty()){

                    vanguardaList.add(revistas);
                }
            }

            if (revistas.getCategoria().equalsIgnoreCase("rumo")){

                if(!revistas.getNome().equals("") && !revistas.getNome().isEmpty() ||
                        !revistas.getFotoJornal().equals("") && !revistas.getFotoJornal().isEmpty() ||
                        !revistas.getLink().equals("") &&!revistas.getLink().isEmpty()||
                        !revistas.getDataEdicao().equals("") &&!revistas.getDataEdicao().isEmpty()){


                    rumoList.add(revistas);
                }
            }

        }
        revistasList.clear();


        if (mercadoList.size()>0){
            setMercadoAdapter(mercadoList);
        } else {
            cardMercado.setVisibility(View.INVISIBLE);
            progressMercado.setVisibility(View.GONE);
            mostrarMensagemTextView(txtCarregandoMercado,msgSemResultadoJornais);

        }

        if (vanguardaList.size()>0){
            setVanguardaAdapter(vanguardaList);
        } else {
            cardVanguarda.setVisibility(View.INVISIBLE);
            progressVanguarda.setVisibility(View.GONE);
            mostrarMensagemTextView(txtCarregandoVanguarda,msgSemResultadoJornais);

        }

        if (rumoList.size()>0){
            setRumoAdapter(rumoList);
        } else {
            cardRumo.setVisibility(View.INVISIBLE);
            progressRumo.setVisibility(View.GONE);
            mostrarMensagemTextView(txtCarregandoRumo,msgSemResultadoJornais);
        }



    }


    private void setMercadoAdapter(List<Revistas> mercadoList){

        //===========================================MERCADO==============================================
        //===============================================================================================

        // Order the list by regist date.
        Collections.reverse(mercadoList);

        RevistasAdapter revistasMercadoAdapter = new RevistasAdapter(mercadoList, this);
        revistasMercadoAdapter.notifyDataSetChanged();
        coverFlow.setAdapter(revistasMercadoAdapter);


        coverFlow.scrollToPosition(mercadoList.size());
        coverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                indexMercado = position;
//                    mTitle.setText((position + 1)+" de "+mercadoList.size());
                mTitle.setText(String.valueOf(mercadoList.get(position).getNome()));
            }

            @Override
            public void onScrolling() {

            }
        });


        cardMercado.setVisibility(View.VISIBLE);
        linearCarregarMercado.setVisibility(View.GONE);




    }

    private void setVanguardaAdapter(List<Revistas> vanguardaList){


        //===========================================VANGUARDA==============================================
        //=========================================================================================

        // Order the list by regist date.
        Collections.reverse(vanguardaList);

        RevistasAdapter revistasVanguardaAdapter = new RevistasAdapter(vanguardaList, this);
        revistasVanguardaAdapter.notifyDataSetChanged();
        coverFlow2.setAdapter(revistasVanguardaAdapter);




        coverFlow2.scrollToPosition(vanguardaList.size());
        coverFlow2.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                indexVanguarda = position;
//                    mTitle2.setText((position + 1)+" de "+vanguardaList.size());
                mTitle2.setText(String.valueOf(vanguardaList.get(position).getNome()));
            }

            @Override
            public void onScrolling() {

            }
        });

        cardVanguarda.setVisibility(View.VISIBLE);
        linearCarregarVanguarda.setVisibility(View.GONE);


    }

    private void setRumoAdapter(List<Revistas> rumoList){

        //==============================================RUMO===========================================
        //=========================================================================================

        // Order the list by regist date.
        Collections.reverse(rumoList);

        RevistasAdapter revistasRumoAdapter = new RevistasAdapter(rumoList, this);
        revistasRumoAdapter.notifyDataSetChanged();
        coverFlow3.setAdapter(revistasRumoAdapter);


        coverFlow3.scrollToPosition(rumoList.size());
        coverFlow3.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                indexRumo = position;
//                    mTitle3.setText((position + 1)+" de "+rumoList.size());
                mTitle3.setText(String.valueOf(rumoList.get(position).getNome()));
            }

            @Override
            public void onScrolling() {

            }
        });

        cardRumo.setVisibility(View.VISIBLE);
        linearCarregarRumo.setVisibility(View.GONE);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
