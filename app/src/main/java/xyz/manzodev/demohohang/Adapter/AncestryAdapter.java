package xyz.manzodev.demohohang.Adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import xyz.manzodev.demohohang.DatabaseHandle;
import xyz.manzodev.demohohang.IMainActivity;
import xyz.manzodev.demohohang.Model.Model;
import xyz.manzodev.demohohang.R;
import xyz.manzodev.demohohang.databinding.LayoutNestedBinding;
import xyz.manzodev.demohohang.databinding.LayoutPeopleBinding;

public class AncestryAdapter extends RecyclerView.Adapter<AncestryAdapter.ViewHolder> {
    ArrayList<ArrayList<Model>> models;
    DatabaseHandle databaseHandle;
    IMainActivity iMainActivity;

    public AncestryAdapter(ArrayList<ArrayList<Model>> models) {
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        iMainActivity = (IMainActivity) viewGroup.getContext();
        databaseHandle = new DatabaseHandle(viewGroup.getContext());
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_nested,viewGroup,false);
        return new AncestryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.layoutNestedBinding.setModel(models.get(i));
        viewHolder.layoutNestedBinding.executePendingBindings();
        //viewHolder.layoutPeopleBinding.setModel(models.get(i));
//        viewHolder.layoutPeopleBinding.container.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                iMainActivity.updateView(models.get(i));
//                Log.d("OK", "onClick: q" + models.get(i).getName());
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        //LayoutPeopleBinding layoutPeopleBinding;
        LayoutNestedBinding layoutNestedBinding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //layoutPeopleBinding = DataBindingUtil.bind(itemView);
            layoutNestedBinding = DataBindingUtil.bind(itemView);
        }
    }
}
