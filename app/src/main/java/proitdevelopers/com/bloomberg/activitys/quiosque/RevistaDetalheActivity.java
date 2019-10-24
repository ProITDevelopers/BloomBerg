package proitdevelopers.com.bloomberg.activitys.quiosque;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import proitdevelopers.com.bloomberg.R;

public class RevistaDetalheActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView img;
    private TextView txt_name, txt_description;
    private Button btnLer;
    String image,name,description,link;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revista_detalhe);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (getIntent()!=null){
            image = getIntent().getStringExtra("img");
            name = getIntent().getStringExtra("name");
            description = getIntent().getStringExtra("description");
            link = getIntent().getStringExtra("link");
            toolbar.setTitle(name);
        }

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        img = findViewById(R.id.imgRev);
        txt_name = findViewById(R.id.txt_name);
        txt_description = findViewById(R.id.txt_description);
        btnLer = findViewById(R.id.btnLer);

        Picasso.with(this).load(image).resize(310, 400).onlyScaleDown().placeholder(R.drawable.revista_placeholder).error(R.drawable.revista_image_error).into(img);
        txt_name.setText(name);
        txt_description.setText(description);

        btnLer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RevistaDetalheActivity.this, RevistaViewActivity.class);
                intent.putExtra("ViewType",link);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
