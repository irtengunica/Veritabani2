package com.example.sbaran.veritabani;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
EditText txtMarka,txtKategori,txtResim;
    Button btnEkle,btnListele;
    ListView lst;

    @Override
    protected void onResume() {
        super.onResume();
        btnEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String marka = txtMarka.getText().toString();
                String kategori = txtKategori.getText().toString();
                int resim = Integer.parseInt(txtResim.getText().toString());

                MyDbHelper dbHelper = MyDbHelper.VtOrnekGetir(MainActivity.this);

                long sonuc = dbHelper.KayitEkle(marka, kategori, resim);
                if (sonuc > 0) {
                    Toast.makeText(MainActivity.this, "Eklendi İd:" + sonuc, Toast.LENGTH_SHORT).show();
                    txtMarka.setText("");
                    txtKategori.setText("");
                    txtResim.setText("");
                } else
                    Toast.makeText(MainActivity.this, "Kayıt olmadı:", Toast.LENGTH_SHORT).show();

            }
        });

        btnListele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDbHelper dbHelper=MyDbHelper.VtOrnekGetir(MainActivity.this);
                ArrayList<String> kayitlar=dbHelper.AraclariGetir();
                ArrayAdapter<String> adp=new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        kayitlar);
                lst.setAdapter(adp);

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtKategori=(EditText)findViewById(R.id.txtKategori);
        txtMarka=(EditText)findViewById(R.id.txtMarka);
        txtResim=(EditText)findViewById(R.id.txtResim);
        btnEkle=(Button)findViewById(R.id.btnEkle);
        btnListele=(Button)findViewById(R.id.btnListe);
        lst=(ListView)findViewById(R.id.lst);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
