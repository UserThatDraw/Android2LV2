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
import com.example.android2l1v2.ui.OnClickInterface;
import com.example.android2l1v2.ui.home.HomeAdapter;
import com.example.android2l1v2.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class GalleryFragment extends Fragment implements OnClickInterface {


    private FragmentGalleryBinding binding;
    private FirebaseFirestore firestore;
    HomeAdapter adapter;
    Boolean isList = true;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        firestore = FirebaseFirestore.getInstance();
        initRec();
        setupFirestore();
        getDataFirestore();
        return binding.getRoot();
    }

    private void initRec() {
        adapter = new HomeAdapter(isList, GalleryFragment.this);
        binding.chatRec.setAdapter(adapter);
    }

    private void getDataFirestore() {
        firestore.collection("allmassages")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                            }
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