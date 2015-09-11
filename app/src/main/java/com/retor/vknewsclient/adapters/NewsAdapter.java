package com.retor.vknewsclient.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.retor.vknewsclient.R;
import com.retor.vknewsclient.db.model.NewsWithAttachments;
import com.retor.vknewsclient.pictures.PicLoader;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by retor on 28.08.2015.
 */
public class NewsAdapter extends BaseAdapter<NewsAdapter.NewsHolder, NewsWithAttachments> {
    private PicLoader loader;

    @Inject
    public NewsAdapter(PicLoader loader) {
        setList(new ArrayList<NewsWithAttachments>());
        this.loader = loader;
    }

    @Override
    public NewsHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(final NewsHolder holder, final int position) {
        NewsWithAttachments post = getPost(position);
        holder.headerText.setText(post.getNews().getText());
        holder.date.setText(DateFormat.getInstance().format(new Date(post.getNews().getDate() * 1000)));
        loader.loadPic(post.getNews().getAuthor_pic(), holder.photo);
        holder.author.setText(post.getNews().getAuthor());
        holder.content.removeAllViews();
//        TextView textView = new TextView(holder.itemView.getContext());
//        textView.setText(post.getNews().getText());
//        holder.content.addView(textView,0);
        if (post.getAttachmentList()!=null && !post.getAttachmentList().isEmpty()){
            ImageView photo = new ImageView(holder.itemView.getContext());
            loader.loadPic(post.getAttachmentList().get(0).getUrl(), photo);
//            textView.setText((post.getAttachmentList().get(0).getUrl()));
//            holder.content.addView(textView, holder.content.getLayoutParams());
            holder.content.addView(photo,holder.content.getLayoutParams());
        }
        holder.content.setScrollContainer(true);
    }

    @Override
    public int getItemCount() {
        return getList().size();
    }

    public static class NewsHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.header_text)
        TextView headerText;
        @Bind(R.id.photo)
        ImageView photo;
        @Bind(R.id.content)
        LinearLayout content;
        @Bind(R.id.author)
        TextView author;
        @Bind(R.id.date)
        TextView date;
        @Bind(R.id.card_view)
        CardView card;

        public NewsHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(NewsHolder.this, itemView);
        }
    }
}
