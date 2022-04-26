package com.example.caferegister;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * This is an Adapter class to be used to instantiate an adapter for the RecyclerView.
 * @author Aadit Singh, Shivan Suratia.
 */
class DonutsAdapter extends RecyclerView.Adapter<DonutsAdapter.donutsHolder> {
    private Context context;
    private ArrayList<DonutKind> donuts;

    /**
     * The constructor for the donuts adapter.
     * @param context the parent context.
     * @param donuts the list of donuts.
     */
    public DonutsAdapter(Context context, ArrayList<DonutKind> donuts) {
        this.context = context;
        this.donuts = donuts;
    }

    /**
     * This method will inflate the row layout for the donuts in the RecyclerView.
     * @param parent the parent view group object.
     * @param viewType the view type id.
     * @return the required donutsHolder object.
     */
    @NonNull
    @Override
    public donutsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_row, parent, false);
        return new DonutsAdapter.donutsHolder(view);
    }

    /**
     * Assign data values for each row according to their "position" (index) when the donut becomes
     * visible on the screen.
     * @param holder the instance of donutsHolder.
     * @param position the index of the donut in the list of donuts.
     */
    @Override
    public void onBindViewHolder(@NonNull donutsHolder holder, int position) {
        holder.flavor.setText(donuts.get(position).toString());
        holder.type.setText(donuts.get(position).getType().toString());
    }

    /**
     * Get the number of donuts in the ArrayList.
     * @return the number of donuts in the list.
     */
    @Override
    public int getItemCount() {
        return donuts.size();
    }

    /**
     * The inner Donuts Holder class, that shows each Donut kind in a CardView.
     */
    public static class donutsHolder extends RecyclerView.ViewHolder {
        private TextView flavor, type;
        private ConstraintLayout parentLayout;

        /**
         * The constructor for the inner donutsHolder class.
         * @param donutView the recycler view that displays the donut kinds.
         */
        public donutsHolder(@NonNull View donutView) {
            super(donutView);
            flavor = donutView.findViewById(R.id.donutFlavor);
            type = donutView.findViewById(R.id.donutType);
            parentLayout = donutView.findViewById(R.id.rowLayout);
            parentLayout.setOnClickListener(new View.OnClickListener() {
                /**
                 * The event that a donut kind is selected from the recycler view.
                 * @param view the implicit view object.
                 */
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(donutView.getContext(), DonutOrderActivity.class);
                    intent.putExtra("DONUT",
                            DonutKind.getDonutKind((String) flavor.getText()));
                    donutView.getContext().startActivity(intent);
                }
            });
        }
    }
}

