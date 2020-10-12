package com.polyblack.company.ui.employee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.polyblack.company.R;
import com.polyblack.company.pojo.Employee;
import com.polyblack.company.util.DataUtils;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {
    private List<Employee> employees;
    private OnEmployeeClickListener onEmployeeClickListener;
    private final RequestManager glide;

    public EmployeeAdapter(OnEmployeeClickListener onEmployeeClickListener, RequestManager glide) {
        this.onEmployeeClickListener = onEmployeeClickListener;
        this.glide = glide;
    }

    public interface OnEmployeeClickListener {
        void onEmployeeClick(int id);
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return employees.get(position).getEmployeeId();
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_employees_item, parent, false);
        return new EmployeeAdapter.EmployeeViewHolder(view, onEmployeeClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = employees.get(position);
        holder.textViewFName.setText(employee.getFName());
        holder.textViewLName.setText(employee.getLName());
        holder.textViewAge.setText(DataUtils.getAge(employee.getBirthday()));
        glide.load(employee.getAvatarUrl()).placeholder(R.drawable.placeholder_image).centerCrop().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (employees != null) {
            return employees.size();
        }
        return 0;
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewFName;
        private TextView textViewLName;
        private TextView textViewAge;
        private ImageView imageView;
        private OnEmployeeClickListener onEmployeeClickListener;

        public EmployeeViewHolder(@NonNull View itemView, OnEmployeeClickListener onEmployeeClickListener) {
            super(itemView);
            textViewFName = itemView.findViewById(R.id.txtEmployeeFNameItem);
            textViewLName = itemView.findViewById(R.id.txtEmployeeLNameItem);
            textViewAge = itemView.findViewById(R.id.txtEmployeeAgeItem);
            imageView = itemView.findViewById(R.id.imgViewEmployeeRecyclerView);
            this.onEmployeeClickListener = onEmployeeClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onEmployeeClickListener.onEmployeeClick((int) EmployeeAdapter.this.getItemId(getAdapterPosition()));
        }
    }
}
