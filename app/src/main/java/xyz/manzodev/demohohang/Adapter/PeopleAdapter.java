package xyz.manzodev.demohohang.Adapter;

import android.content.Context;
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
import xyz.manzodev.demohohang.databinding.LayoutPeopleBinding;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {
    ArrayList<Model> models;
    String mode;
    IMainActivity iMainActivity;

    public PeopleAdapter(ArrayList<Model> models,String mode) {
        this.models = models;
        this.mode = mode;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        iMainActivity = (IMainActivity) viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_people,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.layoutPeopleBinding.setModel(models.get(i));
        viewHolder.layoutPeopleBinding.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mode.equals("find")){
                    iMainActivity.onFindBack(models.get(i));
                    iMainActivity.onBack();
                }
                else iMainActivity.onAncestryView(models.get(i));
            }
        });
        viewHolder.layoutPeopleBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        LayoutPeopleBinding layoutPeopleBinding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutPeopleBinding = DataBindingUtil.bind(itemView);
        }
    }
}
