package com.example.lab.fintech_momo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class SetSearch extends AppCompatActivity {

    private ImageView img;
    private Spinner spinner;
    private Button search;

    public String selected_type;
    public Bitmap bmp;
    private String filename;
    private Button go_home_page;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_search);

        img = (ImageView) findViewById(R.id.show_image);
        spinner = (Spinner) findViewById(R.id.spinner);
        search = (Button) findViewById(R.id.btn_search);
        Bundle bundle = this.getIntent().getExtras();
        filename = bundle.getString("image");

        bmp = null;
            try {
//                File file = new File(new File("/sdcard/"), filename);
//            FileInputStream file_input_stream = this.openFileInput(file);
            bmp = BitmapFactory.decodeFile("/sdcard/DCIM/"+filename);
//            file_input_stream.close();
            img.setImageBitmap(bmp);
        } catch (Exception e) {
            e.printStackTrace();
        }


        final String[] type = {"美妝", "保健", "食品", "旅遊", "婦幼", "3C", "家電", "服飾", "內衣", "鞋包錶", "精品/配飾", "生活用品", "傢俱寢飾", "宗教/藝術", "運動休閒"};
        final String[] search_code = {
            "1190000000",
            "1290000000",
            "2090000000",
            "2590000000",
            "2790000000",
            "1990000000",
            "2990000000",
            "1390000000",
            "1490000000",
            "1590000000",
            "3190000000",
            "1790000000",
            "1890000000",
            "2890000000",
            "1690000000"};

        ArrayAdapter<String> titleList = new ArrayAdapter<>(SetSearch.this,
                android.R.layout.simple_spinner_dropdown_item,
                type);
        spinner.setAdapter(titleList);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(ViewImage.this, "你選的是" + type[position], Toast.LENGTH_SHORT).show();
                selected_type = search_code[position];
//                Toast.makeText(SetSearch.this, "品項：" + type, Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(SetSearch.this, "品項：" + type, Toast.LENGTH_SHORT).show();

                try {
//                    //Write file
//                    String filename = "bitmap.png";
//                    FileOutputStream stream = SetSearch.this.openFileOutput(filename, Context.MODE_PRIVATE);
//                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
//
//                    //Cleanup
//                    stream.close();
//                    bmp.recycle();

                    Bundle bundle = new Bundle();
                    bundle.putString("type", selected_type);
                    bundle.putString("image", filename);
                    //Pop intent
                    Intent intent = new Intent(SetSearch.this, SearchResultByImage.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        go_home_page = (Button) findViewById(R.id.goHome);
        go_home_page.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                finish();
            }
        });
    }
}

