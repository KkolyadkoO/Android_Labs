package com.example.lab10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab10.entities.Comment;

import java.util.List;

public class CommentRecyclerAdapter extends RecyclerView.Adapter<CommentRecyclerAdapter.CommentViewHolder> {

    private List<Comment> commentList;

    public CommentRecyclerAdapter(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_recycler_item, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder.bind(comment);
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {

        private TextView tvCommentId;
        private TextView tvPostId;
        private TextView tvCommentName;
        private TextView tvCommentEmail;
        private TextView tvCommentBody;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCommentId = itemView.findViewById(R.id.tvCommentId);
            tvPostId = itemView.findViewById(R.id.tvPostId);
            tvCommentName = itemView.findViewById(R.id.tvCommentName);
            tvCommentEmail = itemView.findViewById(R.id.tvCommentEmail);
            tvCommentBody = itemView.findViewById(R.id.tvCommentBody);
        }

        public void bind(Comment comment) {
            tvCommentId.setText("Id - " + comment.getId());
            tvPostId.setText("PostId - " + comment.getPostId());
            tvCommentName.setText("Name - " + comment.getName());
            tvCommentEmail.setText("Email - " + comment.getEmail());
            tvCommentBody.setText("Body - " + comment.getBody());
        }
    }

}
