package com.webandcrafts.vstream.adapter;

import android.content.Context;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webandcrafts.vstream.R;
import com.webandcrafts.vstream.customviews.SKtetViewBold;
import com.webandcrafts.vstream.interfaces.DaySelectionCallBack;

import java.util.ArrayList;

/**
 * Created by Bino on 11/30/2017.
 */

public class ProgramScheduleDayAdapter extends RecyclerView.Adapter<ProgramScheduleDayAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<String> mWeakDays = new ArrayList<>();
    private DaySelectionCallBack mDaySelectionCallBack;
    private String mDay;

    public ProgramScheduleDayAdapter(String day, Context context, ArrayList<String> weakDays, DaySelectionCallBack daySelectionCallBack) {

        this.mContext = context;
        this.mWeakDays = weakDays;
        this.mDaySelectionCallBack = daySelectionCallBack;
        this.mDay = day;

    }


    @Override
    public ProgramScheduleDayAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_day_item, parent, false);
        return new ProgramScheduleDayAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ProgramScheduleDayAdapter.MyViewHolder holder, final int position) {

//
//
//        holder.mProgramContainer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                mDaySelectionCallBack.daySelected(mWeakDays.get(position).toLowerCase());
//            }
//        });

        switch (position) {
            case 0:

                if (mWeakDays.get(position).equalsIgnoreCase(mDay)) {

                    holder.mDaySelectedButton.setBackgroundResource(R.drawable.gradient_red);
                    holder.mDaySelectedButton.setText(mWeakDays.get(position));
                    holder.mDayButton.setVisibility(View.GONE);
                    holder.mDaySelectedButton.setVisibility(View.VISIBLE);
                    holder.mDaySelectedButton.setTextColor(ContextCompat.getColor(mContext, R.color.selected_color));
                } else {
                    holder.mDayButton.setBackgroundResource(R.drawable.gradient_one);
                    holder.mDayButton.setText(mWeakDays.get(position));
                    holder.mDayButton.setVisibility(View.VISIBLE);
                    holder.mDayButton.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                    holder.mDaySelectedButton.setVisibility(View.GONE);


                }
                break;
            case 1:


                if (mWeakDays.get(position).equalsIgnoreCase(mDay)) {

                    holder.mDaySelectedButton.setBackgroundResource(R.drawable.gradient_red);
                    holder.mDaySelectedButton.setText(mWeakDays.get(position));
                    holder.mDayButton.setVisibility(View.GONE);
                    holder.mDaySelectedButton.setVisibility(View.VISIBLE);
                    holder.mDaySelectedButton.setTextColor(ContextCompat.getColor(mContext, R.color.selected_color));
                } else {
                    holder.mDayButton.setBackgroundResource(R.drawable.gradient_one);
                    holder.mDayButton.setText(mWeakDays.get(position));
                    holder.mDayButton.setVisibility(View.VISIBLE);
                    holder.mDayButton.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                    holder.mDaySelectedButton.setVisibility(View.GONE);


                }
                break;
            case 2:


                if (mWeakDays.get(position).equalsIgnoreCase(mDay)) {

                    holder.mDaySelectedButton.setBackgroundResource(R.drawable.gradient_red);
                    holder.mDaySelectedButton.setText(mWeakDays.get(position));
                    holder.mDayButton.setVisibility(View.GONE);
                    holder.mDaySelectedButton.setVisibility(View.VISIBLE);
                    holder.mDaySelectedButton.setTextColor(ContextCompat.getColor(mContext, R.color.selected_color));
                } else {
                    holder.mDayButton.setBackgroundResource(R.drawable.gradient_one);
                    holder.mDayButton.setText(mWeakDays.get(position));
                    holder.mDayButton.setVisibility(View.VISIBLE);
                    holder.mDayButton.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                    holder.mDaySelectedButton.setVisibility(View.GONE);


                }
                break;
            case 3:


                if (mWeakDays.get(position).equalsIgnoreCase(mDay)) {

                    holder.mDaySelectedButton.setBackgroundResource(R.drawable.gradient_red);
                    holder.mDaySelectedButton.setText(mWeakDays.get(position));
                    holder.mDayButton.setVisibility(View.GONE);
                    holder.mDaySelectedButton.setVisibility(View.VISIBLE);
                    holder.mDaySelectedButton.setTextColor(ContextCompat.getColor(mContext, R.color.selected_color));
                } else {
                    holder.mDayButton.setBackgroundResource(R.drawable.gradient_one);
                    holder.mDayButton.setText(mWeakDays.get(position));
                    holder.mDayButton.setVisibility(View.VISIBLE);
                    holder.mDayButton.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                    holder.mDaySelectedButton.setVisibility(View.GONE);

                }
                break;
            case 4:


                if (mWeakDays.get(position).equalsIgnoreCase(mDay)) {

                    holder.mDaySelectedButton.setBackgroundResource(R.drawable.gradient_red);
                    holder.mDaySelectedButton.setText(mWeakDays.get(position));
                    holder.mDayButton.setVisibility(View.GONE);
                    holder.mDaySelectedButton.setVisibility(View.VISIBLE);
                    holder.mDaySelectedButton.setTextColor(ContextCompat.getColor(mContext, R.color.selected_color));
                } else {
                    holder.mDayButton.setBackgroundResource(R.drawable.gradient_one);
                    holder.mDayButton.setText(mWeakDays.get(position));
                    holder.mDayButton.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                    holder.mDayButton.setVisibility(View.VISIBLE);
                    holder.mDaySelectedButton.setVisibility(View.GONE);

                }
                break;
            case 5:


                if (mWeakDays.get(position).equalsIgnoreCase(mDay)) {

                    holder.mDaySelectedButton.setBackgroundResource(R.drawable.gradient_red);
                    holder.mDaySelectedButton.setText(mWeakDays.get(position));
                    holder.mDayButton.setVisibility(View.GONE);
                    holder.mDaySelectedButton.setVisibility(View.VISIBLE);
                    holder.mDaySelectedButton.setTextColor(ContextCompat.getColor(mContext, R.color.selected_color));
                } else {
                    holder.mDayButton.setBackgroundResource(R.drawable.gradient_one);
                    holder.mDayButton.setText(mWeakDays.get(position));
                    holder.mDayButton.setVisibility(View.VISIBLE);
                    holder.mDayButton.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                    holder.mDaySelectedButton.setVisibility(View.GONE);
                }
                break;
            case 6:

                if (mWeakDays.get(position).equalsIgnoreCase(mDay)) {

                    holder.mDaySelectedButton.setBackgroundResource(R.drawable.gradient_red);
                    holder.mDaySelectedButton.setText(mWeakDays.get(position));
                    holder.mDayButton.setVisibility(View.GONE);
                    holder.mDaySelectedButton.setVisibility(View.VISIBLE);
                    holder.mDaySelectedButton.setTextColor(ContextCompat.getColor(mContext, R.color.selected_color));
                } else {
                    holder.mDayButton.setBackgroundResource(R.drawable.gradient_one);
                    holder.mDayButton.setText(mWeakDays.get(position));
                    holder.mDayButton.setVisibility(View.VISIBLE);
                    holder.mDayButton.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                    holder.mDaySelectedButton.setVisibility(View.GONE);
                }
                break;


        }

        holder.mDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDaySelectionCallBack.daySelected(mWeakDays.get(position).toLowerCase(), position);
            }
        });


        holder.mDaySelectedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDaySelectionCallBack.daySelected(mWeakDays.get(position).toLowerCase(), position);
            }
        });

    }

    @Override
    public int getItemCount() {
//        return mProgramModel.size();

        return mWeakDays.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        private SKtetViewBold mDayButton;
        private SKtetViewBold mDaySelectedButton;


        public MyViewHolder(View view) {
            super(view);


            mDayButton = view.findViewById(R.id.buttonDay);
            mDaySelectedButton = view.findViewById(R.id.buttonDaySelected);


        }
    }


}
