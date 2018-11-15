package xyz.manzodev.demohohang;

import java.util.ArrayList;

import xyz.manzodev.demohohang.Model.Model;

public interface IMainActivity {
    void addPeople();
    void onDataBack(Model model);
    void findPeople();
    void onFindBack(Model model);
    void addRelative(Model m1 ,Model m2,int relative);
    void onAncestryView(Model model);
    void updateView(ArrayList<Model> models,int index);
    void onBack();
}
