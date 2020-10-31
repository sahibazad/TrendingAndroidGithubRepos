package com.sahib.nayantechchallenge.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sahib.nayantechchallenge.R;
import com.sahib.nayantechchallenge.constants.Constants;
import com.sahib.nayantechchallenge.network.models.RepositoryModel;

import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {
    private Context context;
    private OnItemClickListener listener;
    private List<RepositoryModel.Repo> list;

    public RepoAdapter(Context context, OnItemClickListener listener, List<RepositoryModel.Repo> list) {
        this.context = context;
        this.listener = listener;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()) .inflate(R.layout.item_repo, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final RepositoryModel.Repo item = list.get(position);

        holder.textRepoName.setText(item.getName());
        holder.textOwnerName.setText(item.getOwner().getLogin());
        ImageLoader.getInstance().displayImage(item.getOwner().getAvatar_url(), holder.imageOwner, Constants.IMG_OPTIONS);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textRepoName, textOwnerName;
        private ImageView imageOwner;

        public ViewHolder(View view) {
            super(view);

            textRepoName = view.findViewById(R.id.textRepoName);
            textOwnerName = view.findViewById(R.id.textOwnerName);
            imageOwner = view.findViewById(R.id.imageOwner);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(list.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(RepositoryModel.Repo item);
    }

}
