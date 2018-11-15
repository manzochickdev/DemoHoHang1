package xyz.manzodev.demohohang;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

import java.util.ArrayList;

import xyz.manzodev.demohohang.Model.Model;
import xyz.manzodev.demohohang.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements IMainActivity {
    ActivityMainBinding activityMainBinding;
    DatabaseHandle databaseHandle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        databaseHandle = new DatabaseHandle(getBaseContext());

        MainFragment mainFragment = new MainFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.layout_container,mainFragment,"MainFragment");
        fragmentTransaction.addToBackStack("MainFragment");
        fragmentTransaction.commit();
    }

    @Override
    public void addPeople() {
        AddFragment addFragment = new AddFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.layout_container,addFragment,"AddFragment");
        fragmentTransaction.addToBackStack("AddFragment");
        fragmentTransaction.commit();
    }

    @Override
    public void onDataBack(Model model) {
        databaseHandle.addPeople(model);
    }

    @Override
    public void findPeople() {
        Bundle bundle = new Bundle();
        bundle.putString("mode","find");
        MainFragment mainFragment = new MainFragment();
        mainFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.layout_container,mainFragment,"MainFragment1");
        fragmentTransaction.addToBackStack("MainFragment1");
        fragmentTransaction.commit();
    }

    @Override
    public void onFindBack(Model model) {
        AddFragment addFragment = (AddFragment) getSupportFragmentManager().findFragmentByTag("AddFragment");
        addFragment.fragmentAddBinding.setModel(model);
    }

    @Override
    public void addRelative(Model m1, Model m2, int relative) {
        databaseHandle.addRelative(m1,m2,relative);
    }

    @Override
    public void onAncestryView(Model model) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("model",model);
        AncestryFragment ancestryFragment = new AncestryFragment();
        ancestryFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.layout_container,ancestryFragment,"AncestryFragment");
        fragmentTransaction.addToBackStack("AncestryFragment");
        fragmentTransaction.commit();
    }

    @Override
    public void updateView(ArrayList<Model> m,int i) {
        int id = m.get(i).getId();
        AncestryFragment ancestryFragment = (AncestryFragment) getSupportFragmentManager().findFragmentByTag("AncestryFragment");
        ancestryFragment.models = m;

        ArrayList<Model> model1 = databaseHandle.getChild(id,0);
        ancestryFragment.updateParent(model1);

        ArrayList<Model> model = databaseHandle.getChild(id,2);
        ancestryFragment.updateChild(model);

        ArrayList<Model> siblings = databaseHandle.getChild(id,1);
        siblings.add(0,m.get(i));

        if (model1.size()!=0){
            Model mdl = model1.get(0);
            ArrayList<Model> parentChild = databaseHandle.getChild(mdl.getId(),2);

            for (int n=0;n<parentChild.size();n++){
                boolean check=false;
                for (int o=0;o<siblings.size();o++){
                    if (parentChild.get(n).getId()==siblings.get(o).getId()){
                        check=true;
                    }
                }
                if (!check){
                    siblings.add(parentChild.get(n));
                }
            }
        }

        if (siblings!=null){
            ancestryFragment.updateSibling(siblings);
        }

        //todo 2 : call db to get list wife , call ancestryFragment method to pass arraylist


    }

    @Override
    public void onBack() {
        onBackPressed();
    }


}
