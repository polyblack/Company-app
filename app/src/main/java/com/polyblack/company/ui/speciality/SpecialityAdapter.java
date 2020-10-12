package com.polyblack.company.ui.speciality;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polyblack.company.R;
import com.polyblack.company.pojo.Speciality;

import java.util.List;

public class SpecialityAdapter extends RecyclerView.Adapter<SpecialityAdapter.SpecialityViewHolder> {
    private List<Speciality> specialities;
    private OnSpecialityClickListener onSpecialityClickListener;

    public SpecialityAdapter(OnSpecialityClickListener onSpecialityClickListener) {
        this.onSpecialityClickListener = onSpecialityClickListener;
    }

    public interface OnSpecialityClickListener {
        void onSpecialityClick(int id);
    }


    public void setSpeciality(List<Speciality> specialities) {
        this.specialities = specialities;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return specialities.get(position).getSpecialityId();
    }

    @NonNull
    @Override
    public SpecialityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_specialities_item, parent, false);
        return new SpecialityViewHolder(view, onSpecialityClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialityViewHolder holder, int position) {
        Speciality speciality = specialities.get(position);
        holder.textViewName.setText(speciality.getName());
    }

    @Override
    public int getItemCount() {
        if (specialities != null) {
            return specialities.size();
        }
        return 0;
    }

    class SpecialityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewName;
        private OnSpecialityClickListener onSpecialityClickListener;

        public SpecialityViewHolder(@NonNull View itemView, OnSpecialityClickListener onSpecialityClickListener) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.txtSpecialityNameItem);
            this.onSpecialityClickListener = onSpecialityClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onSpecialityClickListener.onSpecialityClick((int) SpecialityAdapter.this.getItemId(getAdapterPosition()));
        }
    }

}
