package com.e.gasserviceapp.fragment;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.e.gasserviceapp.R;
import com.e.gasserviceapp.databinding.FragmentEmailConfirmationBinding;
import com.e.gasserviceapp.utility.LoadingDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

//import com.e.gasserviceapp.fragment.databinding.FragmentEmailConfirmationBinding;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class EmailConfirmationFragment extends Fragment {

    private FragmentEmailConfirmationBinding binding;
//    private EmailConfirmationFragmentArgs emailFragmentArgs;
    private boolean isPassword;
    private String email, password;
    private LoadingDialog loadingDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEmailConfirmationBinding.inflate(inflater, container, false);
//        emailFragmentArgs = EmailConfirmationFragmentArgs.fromBundle(requireArguments());
//        isPassword = emailFragmentArgs.getIsPassword();
        firebaseAuth = FirebaseAuth.getInstance();
        loadingDialog = new LoadingDialog(getActivity());


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.edtCEmail.setText(firebaseAuth.getCurrentUser().getEmail());

        binding.btnConfirmAccount.setOnClickListener(confirm -> {

            if (areFieldReady()) {
                loadingDialog.startLoading();

                AuthCredential authCredential = EmailAuthProvider
                        .getCredential(email, password);
                firebaseAuth.getCurrentUser().reauthenticate(authCredential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            loadingDialog.stopLoading();

                            if (isPassword) {
//                                Navigation.findNavController(view).navigate(R.id.action_emailConfirmationFragment_to_passwordChangeFragment);
                            } else {

//                                Navigation.findNavController(view).navigate(R.id.action_emailConfirmationFragment_to_emailChangeFragment);

                            }

                        } else {
                            loadingDialog.stopLoading();
                            Log.d("TAG", "onComplete: " + task.getException());
                            Toast.makeText(requireContext(), "Error : " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }


        });

    }

    private boolean areFieldReady() {

        email = binding.edtCEmail.getText().toString().trim();
        password = binding.edtCPassword.getText().toString().trim();

        boolean flag = false;
        View requestView = null;

        if (email.isEmpty()) {
            binding.edtCEmail.setError("Field is required");
            flag = true;
            requestView = binding.edtCEmail;
        } else if (password.isEmpty()) {
            binding.edtCPassword.setError("Field is required");
            flag = true;
            requestView = binding.edtCPassword;
        } else if (password.length() < 8) {
            binding.edtCPassword.setError("Minimum 8 characters");
            flag = true;
            requestView = binding.edtCPassword;
        }

        if (flag) {
            requestView.requestFocus();
            return false;
        } else {
            return true;
        }

    }


   }