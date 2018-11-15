package xyz.manzodev.demohohang.Adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import xyz.manzodev.demohohang.IMainActivity;
import xyz.manzodev.demohohang.Model.Model;
import xyz.manzodev.demohohang.R;
import xyz.manzodev.demohohang.databinding.LayoutHorizontalBinding;

public class SiblingAdapter extends RecyclerView.Adapter<SiblingAdapter.ViewHolder> {
    ArrayList<Model> models;
    IMainActivity iMainActivity;

    public SiblingAdapter(ArrayList<Model> models) {
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        iMainActivity = (IMainActivity) viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_horizontal,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.layoutHorizontalBinding.setModel(models.get(i));
        viewHolder.layoutHorizontalBinding.horizontalContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iMainActivity.updateView(models,i);
            }
        });
        viewHolder.layoutHorizontalBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        LayoutHorizontalBinding layoutHorizontalBinding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutHorizontalBinding = DataBindingUtil.bind(itemView);
        }
    }
}
