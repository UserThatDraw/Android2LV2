package com.example.android2l1v2.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.android2l1v2.R;
import com.example.android2l1v2.TaskModel;
import com.example.android2l1v2.databinding.FragmentGalleryBinding;
import com.example.android2l1v2.ui.ChatModel;
import com.example.android2l1v2.ui.OnClickInterface;
import com.example.android2l1v2.ui.home.HomeAdapter;
import com.example.android2l1v2.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GalleryFragment extends Fragment implements OnClickInterface {


    private FragmentGalleryBinding binding;
    private FirebaseFirestore firestore;
    HomeAdapter adapter;
    Boolean isList = true;
    List<TaskModel> list = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        firestore = FirebaseFirestore.getInstance();
        adapter = new HomeAdapter(isList, GalleryFragment.this);
        initRec();
        setupFirestore();
        getDataFirestore();
        return binding.getRoot();
    }

/*
    public void uploadFiles(){
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReference();

// Create a reference to "mountains.jpg"
        StorageReference mountainsRef = storageRef.child("mountains.jpg");

// Create a reference to 'images/mountains.jpg'
        StorageReference mountainImagesRef = storageRef.child("images/mountains.jpg");

// While the file names are the same, the references point to different files
        mountainsRef.getName().equals(mountainImagesRef.getName());    // true
        mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false
    }*/

    private void initRec() {
        binding.chatRec.setAdapter(adapter);
    }

    private void getDataFirestore() {
        firestore.collection("allmassages")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                TaskModel taskModel = new TaskModel((""),document.getString("msg"), "");
                                list.add(taskModel);
                            }
                            adapter.addListOfModel(list);
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void setupFirestore() {

        binding.btnSend.setOnClickListener(v -> {
            Map<String, Object> massage = new HashMap<>();
            massage.put("msg", binding.inputEdittext.getText().toString());

            firestore.collection("allmassages").add(massage).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Log.e("TAG", "setupFirestore: success");
                    Toast.makeText(requireActivity(), "Message has been delivered to Firestore", Toast.LENGTH_SHORT).show();
                    TaskModel taskModel = new TaskModel((""),binding.inputEdittext.getText().toString(), "");
                    binding.inputEdittext.setText("");
                    list.add(taskModel);
                } else {
                    Log.e("TAG", "setupFirestore: error " + task.toString());
                    Toast.makeText(requireActivity(), "Message hasn`t been delivered to Firestore", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(int position, TaskModel model) {

    }

    @Override
    public void onLongItemClick(int position) {

    }
}