package com.app.autoinstagram.ui.imagepost;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.app.autoinstagram.Activities.MainActivity2;
import com.app.autoinstagram.databinding.FragmentImagepostBinding;
import com.chaquo.python.PyException;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class ImagePostFragment extends Fragment {

    private FragmentImagepostBinding binding;
    PyObject object;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentImagepostBinding.inflate(inflater, container, false);

        object=null;
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(requireContext()));
            Toast.makeText(requireContext(), "Started", Toast.LENGTH_SHORT).show();
            Python python=Python.getInstance();
            PyObject pyObject=null;
            try {
                pyObject=python.getModule("instaprogram");
//                Toast.makeText(MainActivity2.this, pyObject.toString(), Toast.LENGTH_SHORT).show();
            } catch (PyException exception){
                Toast.makeText(requireContext(), exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(requireContext(), "Started earlier", Toast.LENGTH_SHORT).show();
            Python python=Python.getInstance();
            PyObject pyObject=null;
            try {
                pyObject=python.getModule("instaprogram");
//                Toast.makeText(MainActivity2.this, pyObject.toString(), Toast.LENGTH_SHORT).show();

            } catch (PyException exception){
                Toast.makeText(requireContext(), exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}