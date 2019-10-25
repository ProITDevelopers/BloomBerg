package proitdevelopers.com.bloomberg.activitys.quiosque;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import proitdevelopers.com.bloomberg.R;
import proitdevelopers.com.bloomberg.modelo.Revistas;

public class RevistasAdapter extends BaseAdapter {

    private List<Revistas> revistasList;
    private Context mContext;
    private LayoutInflater inflater;

    public RevistasAdapter(List<Revistas> revistasList, Context mContext) {
        this.revistasList = revistasList;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }



    @Override
    public int getCount() {
        return revistasList.size();
    }

    @Override
    public Revistas getItem(int i) {
        return revistasList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View rowView = view;
        Revistas revistas = revistasList.get(i);
        if (rowView == null){

            View itemView = inflater.inflate(R.layout.layout_item,viewGroup,false);
            TextView name = (TextView)itemView.findViewById(R.id.label);
            ImageView image = (ImageView)itemView.findViewById(R.id.image);



//            rowView = LayoutInflater.from(mContext).inflate(R.layout.layout_item,null);
//
//            TextView name = (TextView)rowView.findViewById(R.id.label);
//            ImageView image = (ImageView)rowView.findViewById(R.id.image);

            //Set Data

            name.setText(revistas.getNome());
            Picasso.with(mContext).load(revistas.getFotoJornal()).resize(210, 300).onlyScaleDown().placeholder(R.drawable.revista_placeholder).error(R.drawable.revista_image_error).into(image);

            image.setOnClickListener(onClickListener(i));


            return itemView;
        }

        return rowView;
    }



    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                verifConecxaoRevistas(position);

            }
        };
    }

    private void verifConecxaoRevistas(int position) {

        ConnectivityManager conMgr =  (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr!=null) {
            NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
            if (netInfo == null){
                Toast.makeText(mContext, "Verifique a sua ligação à internet.", Toast.LENGTH_SHORT).show();;
            } else {
                goToDetalheActivity(position);
            }
        }

    }

    private void goToDetalheActivity(int position){
        Intent intent = new Intent(mContext, RevistaDetalheActivity.class);
        intent.putExtra("img",getItem(position).getFotoJornal());
        intent.putExtra("name",getItem(position).getNome());
        intent.putExtra("description",getItem(position).getDescricao());
        intent.putExtra("link",getItem(position).getLink());
        mContext.startActivity(intent);
    }
}
