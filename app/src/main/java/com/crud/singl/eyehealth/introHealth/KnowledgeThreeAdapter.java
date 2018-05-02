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

public class KnowledgeThreeAdapter extends RecyclerView.Adapter<KnowledgeThreeAdapter.KnowledgeThreeViewHolder> {

    private Context mCtx;
    private List<Knowledge> knowledgeList;

    public KnowledgeThreeAdapter(Context mCtx, List<Knowledge> knowledgeList) {
        this.mCtx = mCtx;
        this.knowledgeList = knowledgeList;
    }

    @Override
    public KnowledgeThreeAdapter.KnowledgeThreeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.knowledge_three_list, null);
        return new KnowledgeThreeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KnowledgeThreeViewHolder holder, int position) {
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

    class KnowledgeThreeViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        ImageView imageView;

        public KnowledgeThreeViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
