package com.webandcrafts.vstream.adapter;

import android.content.Context;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.webandcrafts.vstream.R;
import com.webandcrafts.vstream.customviews.SKtetViewRobotoBold;
import com.webandcrafts.vstream.customviews.SKtextViewRobotoRegular;
import com.webandcrafts.vstream.model.ProgramListModel;
import com.webandcrafts.vstream.model.ScheduleListEventModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bino on 12/1/2017.
 */

public class CurrentDayprogramScheduleAdapter extends RecyclerView.Adapter<CurrentDayprogramScheduleAdapter.MyViewHolder> {


    private Context mContext;
    private ArrayList<ProgramListModel> mProgramModel = new ArrayList<>();
    public static List<ScheduleListEventModel> mEventModel = new ArrayList<>();


    public CurrentDayprogramScheduleAdapter(Context context, List<ScheduleListEventModel> eventModel) {

        this.mContext = context;
        mEventModel = eventModel;


    }


    @Override
    public CurrentDayprogramScheduleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.current_day_program_schedule_item, parent, false);
        int height = parent.getMeasuredHeight() / 4;
        itemView.setMinimumHeight(height);
        return new CurrentDayprogramScheduleAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(CurrentDayprogramScheduleAdapter.MyViewHolder holder, final int position) {


        holder.mDescriptiontextView.setTextColor(ContextCompat.getColor(mContext, R.color.destxtcolor));
        holder.mProgramTime.setTextColor(ContextCompat.getColor(mContext, R.color.timetxtcolor));

        if (mEventModel != null && mEventModel.size() > 0) {


            if (mEventModel.get(position).getShowTitle() != null && !mEventModel.get(position).getShowTitle().equals("")) {
                holder.textViewProgramName.setText(mEventModel.get(position).getShowTitle());
            }
            if (mEventModel.get(position).getShowDescription() != null && !mEventModel.get(position).getShowDescription().equals("")) {
                holder.mDescriptiontextView.setText(mEventModel.get(position).getShowDescription());
            } else {
                holder.mDescriptiontextView.setText("Description");
            }
            if (mEventModel.get(position).getShowTimeStart() != null && !mEventModel.get(position).getShowTimeStart().equals("")) {

                holder.mProgramTime.setText(mEventModel.get(position).getShowTimeStart());
            }

            if (mEventModel.get(position).isSelected()) {
                holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.bg_color_transparent));
            }
        }

    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mEventModel.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public SKtetViewRobotoBold textViewProgramName;
        private SKtextViewRobotoRegular mDescriptiontextView;
        private SKtetViewRobotoBold mProgramTime;
        private LinearLayout mProgramContainer;
//        private ImageView mImageViewMoon;
//        private ImageView mImageViewSun;


        public MyViewHolder(View view) {
            super(view);

            textViewProgramName = view.findViewById(R.id.programName);
            mDescriptiontextView = view.findViewById(R.id.programDescription);
            mProgramTime = view.findViewById(R.id.ProgramTime);
            mProgramContainer = view.findViewById(R.id.containerBackGround);

//            mImageViewMoon = (ImageView) view.findViewById(R.id.moonImageView);
//            mImageViewSun = (ImageView) view.findViewById(R.id.sunImageView);


        }
    }


}
