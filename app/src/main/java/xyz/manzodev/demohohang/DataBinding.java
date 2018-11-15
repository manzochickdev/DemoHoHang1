package xyz.manzodev.demohohang;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import xyz.manzodev.demohohang.Adapter.SiblingAdapter;
import xyz.manzodev.demohohang.Model.Model;

public class DataBinding {
    @BindingAdapter("setAdapter")
    public static void setAdapter(RecyclerView view,ArrayList<Model> model){
        SiblingAdapter siblingAdapter = new SiblingAdapter(model);
        view.setAdapter(siblingAdapter);
        view.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
    }
}
