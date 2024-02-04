package com.example.eventus.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventus.R;
import com.example.eventus.data.Database;
import com.example.eventus.data.ServerSideException;
import com.example.eventus.ui.events.EventAdapter;
import com.example.eventus.ui.events.UserEventDisplay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserEventsFragment extends Fragment {

    private RecyclerView upcomingEventsRecyclerView;
    private List<UserEventDisplay> upcomingEventsList = new ArrayList<>();
    private String userId;
    private String userName;

    public UserEventsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_events, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            userId = getArguments().getString("userId", "");
            userName = getArguments().getString("userName", "");
        }

        // Set up click listeners for buttons
        view.findViewById(R.id.discover).setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_userEventsFragment_to_userDiscoverFragment, createNavigationBundle()));

        view.findViewById(R.id.profile).setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_userEventsFragment_to_profileFragment, createNavigationBundle()));

        view.findViewById(R.id.messages).setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_userEventsFragment_to_userMessagesFragment, createNavigationBundle()));

        // Fetch user events using the database function
        try {
            UserEventDisplay[] userEvents = Database.getEventList(userId);

            // Clear the existing list and add the fetched events
            upcomingEventsList.clear();
            upcomingEventsList.addAll(Arrays.asList(userEvents));
        } catch (ServerSideException e) {
            // Handle the exception (e.g., show an error message)
            e.printStackTrace();
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
        }

        // Set up RecyclerView for Events
        upcomingEventsRecyclerView = view.findViewById(R.id.eventsList);
        EventAdapter eventAdapter = new EventAdapter(upcomingEventsList);
        upcomingEventsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        upcomingEventsRecyclerView.setAdapter(eventAdapter);
    }

    // Method to create a common bundle for navigation
    private Bundle createNavigationBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("userId", userId);
        bundle.putString("userName", userName);
        return bundle;
    }
}
