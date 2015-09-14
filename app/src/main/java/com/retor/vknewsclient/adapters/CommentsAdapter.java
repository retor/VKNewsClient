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
import com.retor.vknewsclient.db.model.CommentWithAttachments;
import com.retor.vknewsclient.utils.PicLoader;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by retor on 10.09.2015.
 */
public class CommentsAdapter extends BaseAdapter<CommentsAdapter.CommentsHolder, CommentWithAttachments> {
    private PicLoader loader;

    @Inject
    public CommentsAdapter(PicLoader loader) {
        setList(new ArrayList<CommentWithAttachments>());
        this.loader = loader;
    }


    @Override
    public CommentsHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card, parent, false);
        return new CommentsHolder(view);
    }

    @Override
    public void onBindViewHolder(final CommentsHolder holder, final int position) {
        CommentWithAttachments post = getPost(position);
        holder.headerText.setText(post.getComment().getText());
        holder.date.setText(DateFormat.getInstance().format(new Date(post.getComment().getDate() * 1000)));
        loader.loadPic(post.getComment().getAuthor_pic(), holder.photo);
        holder.author.setText(post.getComment().getAuthor());
        holder.content.removeAllViews();
        if (post.getAttachmentList()!=null && !post.getAttachmentList().isEmpty()){
            ImageView photo = new ImageView(holder.itemView.getContext());
            loader.loadPic(post.getAttachmentList().get(0).getUrl(), photo);
            holder.content.addView(photo,holder.content.getLayoutParams());
        }
        holder.content.setScrollContainer(true);
    }

    @Override
    public int getItemCount() {
        return getList().size();
    }

    public static class CommentsHolder extends RecyclerView.ViewHolder {
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

        public CommentsHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(CommentsHolder.this, itemView);
        }
    }
}
