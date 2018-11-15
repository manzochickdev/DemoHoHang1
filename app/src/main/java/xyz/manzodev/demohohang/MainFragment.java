package xyz.manzodev.demohohang;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import xyz.manzodev.demohohang.Adapter.PeopleAdapter;
import xyz.manzodev.demohohang.Model.Model;
import xyz.manzodev.demohohang.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {
    String mode;
    FragmentMainBinding fragmentMainBinding;
    ArrayList<Model> models;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle!=null){
            mode = bundle.getString("mode");
        }
        else mode="";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMainBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_main, container, false);
        fragmentMainBinding.setIMainActivity((IMainActivity) getContext());
        DatabaseHandle databaseHandle = new DatabaseHandle(getContext());
        models = databaseHandle.showPeople();

        PeopleAdapter peopleAdapter = new PeopleAdapter(models,mode);
        fragmentMainBinding.rvPeople.setAdapter(peopleAdapter);
        fragmentMainBinding.rvPeople.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        return fragmentMainBinding.getRoot();
    }
}
