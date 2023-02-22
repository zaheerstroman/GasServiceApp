package com.e.gasserviceapp.fragment;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.e.gasserviceapp.databinding.FragmentMyResponsesBinding;

//import com.e.gasserviceapp.fragment.databinding.FragmentMyResponsesBinding;


public class MyResponsesFragment extends Fragment {

    RecyclerView rv_my_responses;
    LinearLayout ll_no_data;
    int SELECTED_POS;

    public static Fragment getInstance(int position) {
        MyResponsesFragment myResponsesFragment = new MyResponsesFragment();
        Bundle args = new Bundle();
        args.putInt("SELECTED_POS", position);
        myResponsesFragment.setArguments(args);
        return myResponsesFragment;
    }



    private FragmentMyResponsesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentMyResponsesBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }










}