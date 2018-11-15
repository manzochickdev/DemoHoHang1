package xyz.manzodev.demohohang;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.Date;

import xyz.manzodev.demohohang.Model.Model;
import xyz.manzodev.demohohang.databinding.FragmentAddBinding;

public class AddFragment extends Fragment {
    FragmentAddBinding fragmentAddBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentAddBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_add, container, false);
        final IMainActivity iMainActivity = (IMainActivity) getContext();

        fragmentAddBinding.tvFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iMainActivity.findPeople();
            }
        });

        fragmentAddBinding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int id = (int) c.getTimeInMillis();
                String name = fragmentAddBinding.etName.getText().toString();
                Model model = new Model(id,name);

                if (fragmentAddBinding.getModel() !=null){
                    iMainActivity.onDataBack(model);
                    iMainActivity.addRelative(model,fragmentAddBinding.getModel(), Integer.parseInt(fragmentAddBinding.etRelative.getText().toString()));
                    iMainActivity.onBack();
                }
                else {
                    iMainActivity.onDataBack(model);
                    iMainActivity.onBack();
                }
            }
        });
        return fragmentAddBinding.getRoot();
    }
}
