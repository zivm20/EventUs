package com.example.eventus.ui.events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.eventus.R;

public class ItemEventFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.item_event, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*
        // Handle item click events or perform any necessary initialization here
        view.findViewById(R.id.showMoreDetails).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Navigate to EventDetailsFragment directly
                    NavHostFragment.findNavController(ItemEventFragment.this)
                            .navigate(R.id.eventDetailsFragment);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });*/
    }
}