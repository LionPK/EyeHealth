package com.crud.singl.eyehealth.introHealth;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.crud.singl.eyehealth.R;
import com.crud.singl.eyehealth.entities.Knowledge;

import java.util.List;

public class KnowledgeOneAdapter extends RecyclerView.Adapter<KnowledgeOneAdapter.KnowledgeOneViewHolder> {

    private Context mCtx;
    private List<Knowledge> knowledgeList;

    public KnowledgeOneAdapter(Context mCtx, List<Knowledge> knowledgeList) {
        this.mCtx = mCtx;
        this.knowledgeList = knowledgeList;
    }

    @Override
    public KnowledgeOneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.knowledge_one_list, null);
        return new KnowledgeOneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KnowledgeOneViewHolder holder, int position) {
        Knowledge knowledge = knowledgeList.get(position);

        //loading the image
        Glide.with(mCtx)
                .load(knowledge.getImage())
                .into(holder.imageView);

        holder.textViewTitle.setText(knowledge.getName());
    }

    @Override
    public int getItemCount() {
        return knowledgeList.size();
    }

    class KnowledgeOneViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        ImageView imageView;

        public KnowledgeOneViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }



}
