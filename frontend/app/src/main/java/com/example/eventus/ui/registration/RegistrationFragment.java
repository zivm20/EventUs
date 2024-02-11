package com.example.eventus.ui.registration;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import com.example.eventus.R;

public class RegistrationFragment extends Fragment {

    private RegistrationViewModel registrationViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registrationViewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);

        final EditText emailEditText = view.findViewById(R.id.email);
        final EditText passwordEditText = view.findViewById(R.id.password);
        final EditText passwordValidationEditText = view.findViewById(R.id.passwordValidation);
        final EditText usernameEditText = view.findViewById(R.id.username);
        final RadioButton radioOrganizer = view.findViewById(R.id.radioOrganizer);
        final Button registerButton = view.findViewById(R.id.register);

        registerButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String passwordValidation = passwordValidationEditText.getText().toString();
            String username = usernameEditText.getText().toString();
            String userType = radioOrganizer.isChecked() ? "Organizer" : "Participant";

            if (passwordValidation.equals(password)) {
                registrationViewModel.register(email, password, username, userType);
                NavHostFragment.findNavController(RegistrationFragment.this)
                        .navigate(R.id.action_registrationFragment_to_loginFragment);
            } else {
                // Inform the user that passwords do not match
                Toast.makeText(requireContext(), "Passwords do not match. Please try again.", Toast.LENGTH_SHORT).show();
            }


        });

        TextView loginLink = view.findViewById(R.id.loginLink);
        loginLink.setOnClickListener(view1 -> NavHostFragment.findNavController(RegistrationFragment.this)
                .navigate(R.id.action_registrationFragment_to_loginFragment));
    }
}
