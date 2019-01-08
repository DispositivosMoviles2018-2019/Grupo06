package com.optativa.uce.appdownloadimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WebImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebImageView imageView =
                (WebImageView) findViewById(R.id.webImage);
        imageView.setPlaceholderImage(R.drawable.ic_launcher_background);
        imageView.setImageUrl("https://img.depor.com/files/article_main/uploads/2018/12/16/5c16a498981a6.jpeg");
    }
}
