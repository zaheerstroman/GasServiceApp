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

import com.e.gasserviceapp.databinding.FragmentPasswordChangeBinding;
import com.e.gasserviceapp.utility.LoadingDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//import com.e.gasserviceapp.fragment.databinding.FragmentPasswordChange2Binding;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class PasswordChangeFragment extends Fragment {
    private FragmentPasswordChangeBinding binding;
    private LoadingDialog loadingDialog;
    private String password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPasswordChangeBinding.inflate(inflater, container, false);
        loadingDialog = new LoadingDialog(getActivity());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnUpdatePassword.setOnClickListener(update -> {
            if (areFieldReady()) {
                loadingDialog.startLoading();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                user.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            loadingDialog.stopLoading();
                            Toast.makeText(requireContext(), "Password is updated", Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(update).popBackStack();
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


        password = binding.edtUPassword.getText().toString().trim();

        boolean flag = false;
        View requestView = null;

        if (password.isEmpty()) {
            binding.edtUPassword.setError("Field is required");
            flag = true;
            requestView = binding.edtUPassword;
        } else if (password.length() < 8) {
            binding.edtUPassword.setError("Minimum 8 characters");
            flag = true;
            requestView = binding.edtUPassword;
        }

        if (flag) {
            requestView.requestFocus();
            return false;
        } else {
            return true;
        }

    }
}