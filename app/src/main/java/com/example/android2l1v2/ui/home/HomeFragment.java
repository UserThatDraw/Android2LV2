package com.example.android2l1v2.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.android2l1v2.R;
import com.example.android2l1v2.TaskModel;
import com.example.android2l1v2.databinding.FragmentHomeBinding;
import com.example.android2l1v2.ui.OnClickInterface;

import org.jetbrains.annotations.NotNull;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment implements OnClickInterface {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    HomeAdapter adapter;
    Boolean isList = false;
   /* ImageView imageV;
    private int PICK_IMAGE = 100;
    Uri imageUri; */

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        if (item.getItemId() == R.id.action_dashboard){
            if (isList){
                item.setIcon(R.drawable.ic_baseline_list_24);
                binding.recView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                isList = false;
            }else {
                item.setIcon(R.drawable.ic_baseline_dashboard_24);
                binding.recView.setLayoutManager(new LinearLayoutManager(getContext()));
                isList = true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
       /* imageV = root.findViewById(R.id.imageView);
        imageV.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE);
            }
        }); */

        initRec();
        getDataForm();
        return root;
    }
    /*
    @SuppressWarnings("deprecation")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imageV.setImageURI(imageUri);
        }
    }*/

    private void getDataForm() {
        getParentFragmentManager().setFragmentResultListener("rv_model", getViewLifecycleOwner(),
                new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull @NotNull String requestKey, @NonNull @NotNull Bundle result) {
                        TaskModel model = (TaskModel) result.getSerializable("model");
                        if (model != null) {
                            adapter.addModel(model, HomeFragment.this);
                        }
                    }
                });
    }

    public void initRec() {
        adapter = new HomeAdapter();
        binding.recView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(int position, TaskModel model) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("mod", model);

        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_controller_view_tag);
        navController.navigate(R.id.action_nav_home_to_formFragment, bundle);
    }

    @Override
    public void onLongItemClick(int position) {

    }
}