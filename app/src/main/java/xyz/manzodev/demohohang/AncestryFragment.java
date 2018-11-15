package xyz.manzodev.demohohang;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import xyz.manzodev.demohohang.Adapter.AncestryAdapter;
import xyz.manzodev.demohohang.Model.Model;
import xyz.manzodev.demohohang.databinding.FragmentAncestryBinding;


public class AncestryFragment extends Fragment {
    FragmentAncestryBinding fragmentAncestryBinding;
    ArrayList<Model> models;
    ArrayList<ArrayList<Model>> m;
    public Model model;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        model = (Model) bundle.getSerializable("model");
        models = new ArrayList<>();
        models.add(model);
        m = new ArrayList<>();
        m.add(models);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentAncestryBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_ancestry, container, false);
        AncestryAdapter ancestryAdapter = new AncestryAdapter(m);
        fragmentAncestryBinding.rvAncestry.setAdapter(ancestryAdapter);
        fragmentAncestryBinding.rvAncestry.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        return fragmentAncestryBinding.getRoot();
    }

    public void updateChild(ArrayList<Model> model2){
        int index = m.indexOf(models);
        if (index > m.size()-2){
            m.add(model2);
        }
        else if(index<=m.size()-2) {
            m.set(index+1,model2);
//            int temp = index+2;
//            while(temp<m.size()){
//                m.remove(temp);
//            }
        }

        fragmentAncestryBinding.rvAncestry.getAdapter().notifyDataSetChanged();
    }

    public void updateParent(ArrayList<Model> model1) {
        int index = m.indexOf(models);
        if (index==0){
            m.add(0,model1);
        }
        else m.set(index-1,model1);
        fragmentAncestryBinding.rvAncestry.getAdapter().notifyDataSetChanged();
    }

    public void updateSibling(ArrayList<Model> siblings) {
        models.clear();
        models.addAll(siblings);
        fragmentAncestryBinding.rvAncestry.getAdapter().notifyDataSetChanged();
    }

    //todo 3 : create method to receive list  , if list.size()>1 , m.add(index+1,list);
}
