package com.app.autoinstagram.Activities;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.app.autoinstagram.databinding.ActivityLoginBinding;
import com.chaquo.python.PyException;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    PyObject pyObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pyObject=null;
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(LoginActivity.this));
            Toast.makeText(LoginActivity.this, "Started", Toast.LENGTH_SHORT).show();
            Python python=Python.getInstance();
            PyObject pyObject=null;
            try {
                pyObject=python.getModule("res");
            } catch (PyException exception){
                Toast.makeText(LoginActivity.this, "err"+exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(LoginActivity.this, "Started earlier", Toast.LENGTH_SHORT).show();
            Python python=Python.getInstance();
            PyObject pyObject=null;
            try {
                pyObject=python.getModule("res");
                Toast.makeText(LoginActivity.this, pyObject.toString(), Toast.LENGTH_SHORT).show();
            } catch (PyException exception){
                Toast.makeText(LoginActivity.this, "err"+exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }
        });

        binding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login(){
        if (!binding.usernameEditText.getText().toString().isEmpty() && !binding.passwordEditText.getText().toString().isEmpty()){
            final PyObject object=pyObject.callAttr("accountLogin", Objects.requireNonNull(binding.usernameEditText.getText()).toString(), Objects.requireNonNull(binding.passwordEditText.getText()).toString());
            if (object.toString().equals("Login successfully.")){
                startActivity(new Intent(LoginActivity.this, MainActivity2.class));
                finish();
            } else {
                Toast.makeText(this, "Login failed. Try again", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Credentials can't be empty.", Toast.LENGTH_SHORT).show();
        }

    }
}