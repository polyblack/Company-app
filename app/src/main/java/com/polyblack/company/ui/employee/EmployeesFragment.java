package com.polyblack.company.ui.employee;

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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.polyblack.company.R;
import com.polyblack.company.pojo.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeesFragment extends Fragment implements EmployeeAdapter.OnEmployeeClickListener {
    private RecyclerView recyclerView;
    private EmployeeAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private EmployeesViewModel viewModel;
    private int specialityId;
    private NavController navController;
    private Toolbar toolbar;

    public static EmployeesFragment newInstance() {
        return new EmployeesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EmployeesFragmentArgs employeesFragmentArgs = EmployeesFragmentArgs.fromBundle(getArguments());
        specialityId = employeesFragmentArgs.getSpecialityId();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.employees_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewEmployee);
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        toolbar = view.findViewById(R.id.toolbarEmployees);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigateUp();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new EmployeeAdapter(this, Glide.with(this));
        adapter.setEmployees(new ArrayList<Employee>());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        viewModel = new ViewModelProvider(this).get(EmployeesViewModel.class);
        viewModel.getEmployeesBySpecialityId(specialityId).observe(getViewLifecycleOwner(), new Observer<List<Employee>>() {
            @Override
            public void onChanged(@Nullable List<Employee> employees) {
                adapter.setEmployees(employees);
            }
        });
    }

    @Override
    public void onEmployeeClick(int id) {
        EmployeesFragmentDirections.ActionEmployeesFragmentToEmployeeDetailedFragment action = EmployeesFragmentDirections.actionEmployeesFragmentToEmployeeDetailedFragment(id);
        navController.navigate(action);
    }
}