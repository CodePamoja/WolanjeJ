package com.wolanjeAfrica.wolanjej.recyclerAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wolanjeAfrica.wolanjej.R;
import com.wolanjeAfrica.wolanjej.models.AvailableFlights;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookFlight03Adapter extends RecyclerView.Adapter<BookFlight03Adapter.viewHolder> {

    private Context context;
    private List<AvailableFlights> mData;
    private OnFlightListener mOnFlightListener;

    public BookFlight03Adapter(Context context, List<AvailableFlights> mData, OnFlightListener onFlightListener) {
        this.context = context;
        this.mData = mData;
        this.mOnFlightListener = onFlightListener;
    }

    @NonNull
    @Override
    public BookFlight03Adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_flight_03_items, parent, false);
        return new BookFlight03Adapter.viewHolder(view, mOnFlightListener);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        Picasso.get().load(String.valueOf(mData.get(position).getmAirwayImg())).into(holder.mImageView);
        holder.mAirWaysId.setText(mData.get(position).getmAirWaysId());
        holder.mFlightHour.setText(mData.get(position).getmFlightHour());
        holder.mFlightMin.setText(mData.get(position).getmFlightMin());
        holder.mFlightStops.setText(mData.get(position).getmFlightStops());
        holder.mFlightCosts.setText(mData.get(position).getmFlightCosts());
        holder.mTicketsLeft.setText(mData.get(position).getmTicketsLeft());
        holder.mFlightFrom.setText(mData.get(position).getmFlightFrom());
        holder.mFlightDepTime.setText(mData.get(position).getmFlightDepTime());
        holder.mFlightTo.setText(mData.get(position).getmFlightTo());
        holder.mFlightArrivalTime.setText(mData.get(position).getmFlightArrivalTime());
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mImageView;
        private TextView mAirWaysId;
        private TextView mFlightHour;
        private TextView mFlightMin;
        private TextView mFlightStops;
        private TextView mFlightCosts;
        private TextView mTicketsLeft;
        private TextView mFlightFrom;
        private TextView mFlightDepTime;
        private TextView mFlightTo;
        private TextView mFlightArrivalTime;

        OnFlightListener onFlightListener;

        viewHolder(@NonNull View itemView, OnFlightListener onFlightListener) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.imageItemBf03);
            mAirWaysId = itemView.findViewById(R.id.AirWaysId);
            mFlightHour = itemView.findViewById(R.id.flightHrItem);
            mFlightMin = itemView.findViewById(R.id.flightMinItem);
            mFlightStops = itemView.findViewById(R.id.flightStopsItem);
            mFlightCosts = itemView.findViewById(R.id.flightCostItem);
            mTicketsLeft = itemView.findViewById(R.id.TicketLeftItem);
            mFlightFrom = itemView.findViewById(R.id.flightFormItem);
            mFlightDepTime = itemView.findViewById(R.id.depTimeItem);
            mFlightTo = itemView.findViewById(R.id.flightToItem);
            mFlightArrivalTime = itemView.findViewById(R.id.arrivalTimeItems);

            this.onFlightListener = onFlightListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onFlightListener.OnFlightClick(getAdapterPosition());
        }
    }
    public interface OnFlightListener{
        void OnFlightClick(int position);
    }
}
