package com.example.wolanjej.recyclerAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wolanjej.R;
import com.example.wolanjej.models.AvailableBuses;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookBus03Adapter extends RecyclerView.Adapter<BookBus03Adapter.viewHolder> {
    private Context context;
    private List<AvailableBuses> mData;
    private OnBusFlightListener onBusFlightListener;

    public BookBus03Adapter(Context context, List<AvailableBuses> mData, OnBusFlightListener onBusFlightListener) {
        this.context = context;
        this.mData = mData;
        this.onBusFlightListener = onBusFlightListener;
    }

    @NonNull
    @Override
    public BookBus03Adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_flight_03_items, parent, false);
        return new BookBus03Adapter.viewHolder(view, onBusFlightListener);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Picasso.get().load(String.valueOf(mData.get(position).getmBusCompanyImg())).into(holder.mImageView);
        holder.mBusCompanyId.setText(mData.get(position).getmBusCompanyId());
        holder.mBusFlightHour.setText(mData.get(position).getmBusFlightHour());
        holder.mBusFlightMin.setText(mData.get(position).getmBusFlightMin());
        holder.mBusFlightStops.setText(mData.get(position).getmBusFlightStops());
        holder.mBusFlightCosts.setText(mData.get(position).getmBusFlightCosts());
        holder.mBusTicketsLeft.setText(mData.get(position).getmBusTicketsLeft());
        holder.mBusFlightFrom.setText(mData.get(position).getmBusFlightFrom());
        holder.mBusFlightDepTime.setText(mData.get(position).getmBusFlightDepTime());
        holder.mBusFlightTo.setText(mData.get(position).getmBusFlightTo());
        holder.mBusFlightArrivalTime.setText(mData.get(position).getmBusFlightArrivalTime());

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageView;
        private TextView mBusCompanyId;
        private TextView mBusFlightHour;
        private TextView mBusFlightMin;
        private TextView mBusFlightStops;
        private TextView mBusFlightCosts;
        private TextView mBusTicketsLeft;
        private TextView mBusFlightFrom;
        private TextView mBusFlightDepTime;
        private TextView mBusFlightTo;
        private TextView mBusFlightArrivalTime;
        private OnBusFlightListener onBusFlightClick;

        viewHolder(@NonNull View itemView, BookBus03Adapter.OnBusFlightListener onBusFlightListener) {
            super(itemView);


            mImageView = itemView.findViewById(R.id.imageItemBf03);
            mBusCompanyId = itemView.findViewById(R.id.AirWaysId);
            mBusFlightHour = itemView.findViewById(R.id.flightHrItem);
            mBusFlightMin = itemView.findViewById(R.id.flightMinItem);
            mBusFlightStops = itemView.findViewById(R.id.flightStopsItem);
            mBusFlightCosts = itemView.findViewById(R.id.flightCostItem);
            mBusTicketsLeft = itemView.findViewById(R.id.TicketLeftItem);
            mBusFlightFrom = itemView.findViewById(R.id.flightFormItem);
            mBusFlightDepTime = itemView.findViewById(R.id.depTimeItem);
            mBusFlightTo = itemView.findViewById(R.id.flightToItem);
            mBusFlightArrivalTime = itemView.findViewById(R.id.arrivalTimeItems);
            this.onBusFlightClick = onBusFlightListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onBusFlightClick.OnBusFlightClick(getAdapterPosition());
        }
    }

    public interface OnBusFlightListener {
        void OnBusFlightClick(int position);
    }
}
