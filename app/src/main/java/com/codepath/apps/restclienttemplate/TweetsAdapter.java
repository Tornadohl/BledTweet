package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import java.sql.Timestamp;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder>{
    private Context context;
    private List<Tweet> tweets;
   // RecyclerView container;

    // pass in context and list of tweets

    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    //for each row, inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }
    // bind values based on the on the position of the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Tweet tweet = tweets.get(position);
        holder.tvBody.setText(tweet.body);
        holder.tvScreenName.setText("@"+tweet.user.screenName);
        holder.tvName.setText(tweet.user.name);
        holder.tvDate.setText(tweet.getFormattedTimestamp);
        GlideApp.with(context).load(tweet.user.profileImageUrl)
                .circleCrop()
                .into(holder.ivProfileImage);


        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tweet tweet = new Tweet();
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("tweet", Parcels.wrap(tweet));
                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return tweets.size();
    }

    // Clear all elements of this recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addTweets(List<Tweet> tweetList){
        tweets.addAll(tweetList);
        notifyDataSetChanged();
    }

    //Define the viewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivProfileImage;
        public TextView tvScreenName;
        public TextView tvBody;
        public TextView tvName;
        public TextView tvDate;
        public RelativeLayout container;
        public ImageView ivtToolbar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
           tvScreenName = itemView.findViewById(R.id.tvScreenName);
           tvBody = itemView.findViewById(R.id.tvBody);
           tvName = itemView.findViewById(R.id.tvName);
           tvDate = itemView.findViewById(R.id.tvDate);
           container = itemView.findViewById(R.id.container);
           ivtToolbar =itemView.findViewById(R.id.ivtToolbar);

        }

    }
}
