package com.polyblack.company.ui.speciality;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.polyblack.company.R;
import com.polyblack.company.pojo.Speciality;

import java.util.ArrayList;
import java.util.List;

public class SpecialitiesFragment extends Fragment implements SpecialityAdapter.OnSpecialityClickListener {
    private RecyclerView recyclerView;
    private SpecialityAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private SpecialitiesViewModel viewModel;
    private NavController navController;
    private View viewNoData;
    private View viewLoading;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Toolbar toolbar;

    public static SpecialitiesFragment newInstance() {
        return new SpecialitiesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.specialities_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewSpeciality);
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        viewLoading = view.findViewById(R.id.linearLayoutLoading);
        viewNoData = view.findViewById(R.id.linearLayoutNoData);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        toolbar = view.findViewById(R.id.toolbarSpecialities);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewLoading.setVisibility(View.GONE);
        viewNoData.setVisibility(View.GONE);
        navController = Navigation.findNavController(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        toolbar.setTitle(R.string.specialities);
        adapter = new SpecialityAdapter(this);
        adapter.setSpeciality(new ArrayList<Speciality>());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        viewModel = new ViewModelProvider(this).get(SpecialitiesViewModel.class);
        subscribeObservers();
    }

    public void subscribeObservers() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.loadData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        viewModel.getSpecialities().observe(getViewLifecycleOwner(), new Observer<List<Speciality>>() {
            @Override
            public void onChanged(List<Speciality> specialities) {
                adapter.setSpeciality(specialities);
                if (specialities.size() < 1) {
                    viewNoData.setVisibility(View.VISIBLE);
                } else {
                    viewNoData.setVisibility(View.GONE);
                }
            }
        });
        viewModel.errors.observe(getViewLifecycleOwner(), new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                if (throwable != null) {
                    Toast.makeText(getActivity(), R.string.no_internet, Toast.LENGTH_SHORT).show();
                    viewModel.clearErrors();
                }
            }
        });
        viewModel.isLoading.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    viewLoading.setVisibility(View.VISIBLE);
                } else {
                    viewLoading.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onSpecialityClick(int id) {
        SpecialitiesFragmentDirections.ActionSpecialitiesFragmentToEmployeesFragment action = SpecialitiesFragmentDirections.actionSpecialitiesFragmentToEmployeesFragment(id);
        navController.navigate(action);
    }
}