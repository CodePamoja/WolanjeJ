package com.wolanjeAfrica.wolanjej.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wolanjeAfrica.wolanjej.BusInfo04;
import com.wolanjeAfrica.wolanjej.Home;
import com.wolanjeAfrica.wolanjej.LearningHub3;
import com.wolanjeAfrica.wolanjej.R;
import com.wolanjeAfrica.wolanjej.models.LearningHubFeatured;
import com.wolanjeAfrica.wolanjej.recyclerAdapters.LearningHubFeaturedRecyclerAdapter;
import com.wolanjeAfrica.wolanjej.recyclerAdapters.RecyclerViewHomeAdapter;

import java.util.ArrayList;
import java.util.List;


public class FeaturedFragment extends Fragment implements LearningHubFeaturedRecyclerAdapter.FeaturedItemListener {
    private View view;
    private RecyclerView recyclerView;
    private List<LearningHubFeatured> learningHubFeaturedList = new ArrayList<>();



    public FeaturedFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_featured, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.featured_item_recyclerview_history);
        RetrieveLearningHubFeaturedList();
        return view;
    }

    private void RetrieveLearningHubFeaturedList() {


        learningHubFeaturedList.add(new LearningHubFeatured("https://images.pexels.com/photos/4328961/pexels-photo-4328961.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500","Innovation","Get an Innovation Toolset With a user-centered guide"));
        learningHubFeaturedList.add(new LearningHubFeatured("https://images.pexels.com/photos/4328961/pexels-photo-4328961.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500","Finance","Learn Finance Skills with our Finance Guide"));
        learningHubFeaturedList.add(new LearningHubFeatured("https://images.pexels.com/photos/4328961/pexels-photo-4328961.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500","Risk Management","Which risk management certification is best?"));


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        LearningHubFeaturedRecyclerAdapter adapter = new LearningHubFeaturedRecyclerAdapter(learningHubFeaturedList, getContext(),this::OnFeaturedItemClickListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnFeaturedItemClickListener(int position) {
        Intent intent = new Intent(getContext(), LearningHub3.class);
        intent.putExtra("featured",  learningHubFeaturedList.get(position));
        startActivity(intent);
    }
}