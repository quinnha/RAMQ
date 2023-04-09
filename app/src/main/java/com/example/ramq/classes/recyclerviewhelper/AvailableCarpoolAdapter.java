/**
 * Sources:
 * https://github.com/mitchtabian/SQLite-for-Beginners-2019
 * https://developer.android.com/develop/ui/views/layout/recyclerview
 */
package com.example.ramq.classes.recyclerviewhelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ramq.R;
import com.example.ramq.classes.AvailableCarpoolInformation;

public class AvailableCarpoolAdapter extends RecyclerView.Adapter<AvailableCarpoolAdapter.ViewHolder> {
    private OnCarpoolSelectListener mOnCarpoolSelectListener;
    private AvailableCarpoolInformation[] localDataSet;
    private TextView cabName;
    private TextView numPassengers;
    private TextView numSeatsAvailable;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView textView;
        OnCarpoolSelectListener onCarpoolSelectListener;
        public ViewHolder(View view, OnCarpoolSelectListener onCarpoolSelectListener) {
            super(view);
            // Define click listener for the ViewHolder's View
            this.onCarpoolSelectListener = onCarpoolSelectListener;
            textView = (TextView) view.findViewById(R.id.textViewCabName);
            view.setOnClickListener(this);
        }

        public TextView getTextView() {
            return textView;
        }

        @Override
        public void onClick(View view) {
            onCarpoolSelectListener.onCarpoolSelect(getAdapterPosition());
        }
    }

    public AvailableCarpoolAdapter(AvailableCarpoolInformation[] dataSet, OnCarpoolSelectListener onCarpoolSelectListener) {
        this.localDataSet = dataSet;
        this.mOnCarpoolSelectListener = onCarpoolSelectListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);
        cabName = view.findViewById(R.id.textViewCabName);
        numPassengers = view.findViewById(R.id.textViewNumPassengers);
        numSeatsAvailable = view.findViewById(R.id.textViewNumSeatsAvailable);

        return new ViewHolder(view, mOnCarpoolSelectListener);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        cabName.setText(localDataSet[position].getCabName());
        numPassengers.setText("Number of Passengers: " + localDataSet[position].getNumPassengers());
        numSeatsAvailable.setText("Number of Seats Available: " + localDataSet[position].getSeatsAvaiable());
    }


    @Override
    public int getItemCount() {
        return localDataSet.length;
    }


    public interface OnCarpoolSelectListener {
        void onCarpoolSelect(int position);
    }
}
