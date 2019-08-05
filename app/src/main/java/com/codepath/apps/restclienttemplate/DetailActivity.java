package com.codepath.apps.restclienttemplate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;
import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {
    ImageView ivProfileImage2;
    TextView tvScreenName2;
    TextView tvBody2;
    TextView tvName2;
    TextView tvDate2;

    Tweet tweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ivProfileImage2 = findViewById(R.id.ivProfileImage2);
        tvScreenName2 = findViewById(R.id.tvScreenName2);
        tvBody2 = findViewById(R.id.tvBody2);
        tvName2 = findViewById(R.id.tvName2);
        tvDate2 = findViewById(R.id.tvDate2);

        tweet= new Tweet();

        tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));
        tvBody2.setText(tweet.body);
        tvScreenName2.setText("@"+tweet.user.screenName);
        tvName2.setText(tweet.user.name);
         tvDate2.setText(tweet.getFormattedTimestamp);
        Glide.with(this).load(tweet.user.profileImageUrl)
                .into(ivProfileImage2);
    }

}
