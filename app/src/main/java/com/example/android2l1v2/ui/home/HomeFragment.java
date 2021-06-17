package com.example.android2l1v2.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.android2l1v2.App;
import com.example.android2l1v2.R;
import com.example.android2l1v2.TaskModel;
import com.example.android2l1v2.databinding.FragmentHomeBinding;
import com.example.android2l1v2.ui.OnClickInterface;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements OnClickInterface {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    HomeAdapter adapter;
    private int position;
    Boolean isList = true;
    private List<TaskModel> modelList = new ArrayList<>();

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (isList) {
                    item.setIcon(R.drawable.ic_baseline_list_24);
                    binding.recView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    isList = false;
                } else {
                    item.setIcon(R.drawable.ic_baseline_dashboard_24);
                    binding.recView.setLayoutManager(new LinearLayoutManager(getContext()));
                    isList = true;
                }
                return false;
            }
        });
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initRec();
        getDataFromRoom();
        getDataForm();
        EditText editText = root.findViewById(R.id.search_et);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
        return root;
    }

    private void filter(String text) {
        ArrayList<TaskModel> filteredList = new ArrayList<>();

        for (TaskModel model : App.getInstance().getTaskDao().getAll()) {
            if (model.getDesc().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(model);
            }
        }
        adapter.filteredList(filteredList);
    }

    private void getDataFromRoom() {
        modelList = App.getInstance().getTaskDao().getAll();
        if (modelList != null) {
            adapter.addListOfModel(modelList);
        }
    }

    private void getDataForm() {
        getParentFragmentManager().setFragmentResultListener("rv_model", getViewLifecycleOwner(),
                new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull @NotNull String requestKey, @NonNull @NotNull Bundle result) {
                        TaskModel model = (TaskModel) result.getSerializable("model");
                        if (model != null) {
                            adapter.addModel(model);
                            App.getInstance().getTaskDao().insertAll(model);
                        }
                    }
                });
    }

    public void initRec() {
        adapter = new HomeAdapter(isList, HomeFragment.this);
        binding.recView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(int position, TaskModel model) {
        this.position = position;
        Bundle bundle = new Bundle();
        bundle.putSerializable("mod", model);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.action_nav_home_to_formFragment, bundle);
    }

    @Override
    public void onLongItemClick(int position) {
        this.position = position;
        adapter.deleteModel(position);
    }
}








