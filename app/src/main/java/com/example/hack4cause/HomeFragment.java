package com.example.hack4cause;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.hack4cause.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_home, container, false);
            ImageSlider imageSlider= view.findViewById(R.id.slider);
            List<SlideModel> slideModels = new ArrayList<>();
            slideModels.add(new SlideModel(R.drawable.tech_w, ScaleTypes.FIT));
            slideModels.add(new SlideModel(R.drawable.sher, ScaleTypes.FIT));
            imageSlider.setImageList(slideModels,ScaleTypes.FIT);
            ImageView imageView = view.findViewById(R.id.imageView);
            ImageView imageView1 = view.findViewById(R.id.imageView2);
            //imageView.setClipToOutline(true);
            //imageView1.setClipToOutline(true);
            return view;

    }
}