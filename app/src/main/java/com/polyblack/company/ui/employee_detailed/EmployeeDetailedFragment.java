package com.polyblack.company.ui.employee_detailed;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.polyblack.company.R;
import com.polyblack.company.data.crossreference.EmployeesWithSpecialities;
import com.polyblack.company.util.DataUtils;

public class EmployeeDetailedFragment extends Fragment {
    private EmployeeDetailedViewModel viewModel;
    private NavController navController;
    private int employeeId;
    private TextView txtFName;
    private TextView txtLName;
    private TextView txtAge;
    private TextView txtBirthday;
    private TextView txtSpecialities;
    private ImageView imageView;
    private Toolbar toolbar;

    public static EmployeeDetailedFragment newInstance() {
        return new EmployeeDetailedFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EmployeeDetailedFragmentArgs employeeDetailedFragmentArgs = EmployeeDetailedFragmentArgs.fromBundle(getArguments());
        employeeId = employeeDetailedFragmentArgs.getEmployeeId();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.employee_detailed_fragment, container, false);
        txtFName = view.findViewById(R.id.txtDetailedFName);
        txtLName = view.findViewById(R.id.txtDetailedLName);
        txtAge = view.findViewById(R.id.txtDetailedAge);
        txtBirthday = view.findViewById(R.id.txtDetailedBirthday);
        txtSpecialities = view.findViewById(R.id.txtDetailedSpecialities);
        imageView = view.findViewById(R.id.imgDetailedAvatar);
        toolbar = view.findViewById(R.id.toolbarDetailedEmployee);
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
        viewModel = new ViewModelProvider(this).get(EmployeeDetailedViewModel.class);
        viewModel.getEmployeeWithSpecialities(employeeId).observe(getViewLifecycleOwner(), new Observer<EmployeesWithSpecialities>() {
            @Override
            public void onChanged(EmployeesWithSpecialities employeesWithSpecialities) {
                txtFName.setText(employeesWithSpecialities.getEmployees().getFName());
                txtLName.setText(employeesWithSpecialities.getEmployees().getLName());
                txtBirthday.setText(employeesWithSpecialities.getEmployees().getBirthday());
                txtAge.setText(DataUtils.getAge(employeesWithSpecialities.getEmployees().getBirthday()));
                txtSpecialities.setText(DataUtils.specialitiesToString(employeesWithSpecialities.getSpecialities()));
                Glide.with(getActivity())
                        .load(employeesWithSpecialities.getEmployees().getAvatarUrl()).
                        placeholder(R.drawable.placeholder_image)
                        .centerCrop()
                        .into(imageView);
            }
        });
    }

}