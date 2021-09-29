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
import com.webandcrafts.vstream.model.ScheduleListScheduleModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bino on 11/30/2017.
 */

public class ProgramListAdapter extends RecyclerView.Adapter<ProgramListAdapter.MyViewHolder> {


    private Context mContext;
    private ArrayList<ProgramListModel> mProgramModel = new ArrayList<>();

    private static ArrayList<ScheduleListScheduleModel> mSchedule = new ArrayList<>();
    public static List<ScheduleListEventModel> mEventModel = new ArrayList<>();


    public ProgramListAdapter(Context context, List<ScheduleListEventModel> programListModels) {

        this.mContext = context;
        mEventModel = programListModels;

    }


    @Override
    public ProgramListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.program_list_item, parent, false);
        return new ProgramListAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.textViewProgramName.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        holder.mDescription.setTextColor(ContextCompat.getColor(mContext, R.color.destxtcolor));
        holder.mShowTime.setTextColor(ContextCompat.getColor(mContext, R.color.timetxtcolor));

        if (mEventModel != null && mEventModel.size() > 0) {

            if (mEventModel.get(position).getShowTitle() != null && !mEventModel.get(position).getShowTitle().equals("")) {
                holder.textViewProgramName.setText(mEventModel.get(position).getShowTitle());
            }
            if (mEventModel.get(position).getShowDescription() != null && !mEventModel.get(position).getShowDescription().equals("")) {
                holder.mDescription.setText(mEventModel.get(position).getShowDescription());
            } else {
                holder.mDescription.setText("Description");
            }
            if (mEventModel.get(position).getShowTimeStart() != null && !mEventModel.get(position).getShowTimeStart().equals("")) {
                holder.mShowTime.setText(mEventModel.get(position).getShowTimeStart());
            }
        }

    }

    @Override
    public int getItemCount() {
        return mEventModel.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public SKtetViewRobotoBold textViewProgramName;
        private SKtextViewRobotoRegular mDescription;
        private SKtetViewRobotoBold mShowTime;
        //        private ImageView mImageViewMoon;
//        private ImageView mImageViewSun;
        private LinearLayout mProgramContainer;


        public MyViewHolder(View view) {
            super(view);

            textViewProgramName = view.findViewById(R.id.programName);
            mDescription = view.findViewById(R.id.programDescription);
            mShowTime = view.findViewById(R.id.ProgramTime);

//            mImageViewMoon = (ImageView) view.findViewById(R.id.moonImageView);
//            mImageViewSun = (ImageView) view.findViewById(R.id.sunImageView);
            mProgramContainer = view.findViewById(R.id.containerBackGround);


        }
    }


}
