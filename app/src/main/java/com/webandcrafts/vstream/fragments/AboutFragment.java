package com.webandcrafts.vstream.fragments;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.webandcrafts.vstream.AppController;
import com.webandcrafts.vstream.R;
import com.webandcrafts.vstream.customviews.SKtextViewRobotoThin;


public class AboutFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private View mRootView;
    private ImageView mFaceBook;
    private ImageView mWebSite;
    private ImageView mYouTube;
    private ImageView mInstagram;
    private ImageView mTwitter;
    private SKtextViewRobotoThin mEmail;


    public AboutFragment() {
        // Required empty public constructor
    }

    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_about, container, false);
        initUi();
        Log.e("called", "aboutfragment");
        return mRootView;
    }

    @SuppressLint("SetTextI18n")
    private void initUi() {


        mFaceBook = mRootView.findViewById(R.id.facebook);
        mWebSite = mRootView.findViewById(R.id.website);
        mYouTube = mRootView.findViewById(R.id.youtube);
        mInstagram = mRootView.findViewById(R.id.instagram);
        mTwitter = mRootView.findViewById(R.id.twitter);
        mEmail = mRootView.findViewById(R.id.mEmail);


        //TODO : Social Button Visibility

        if (!AppController.FACEBOOK_PAGE_ID.equals(""))
            mFaceBook.setVisibility(View.VISIBLE);
        else
            mFaceBook.setVisibility(View.GONE);

        if (!AppController.WEBSITE_URL.equals(""))
            mWebSite.setVisibility(View.VISIBLE);
        else
            mWebSite.setVisibility(View.GONE);

        if (!AppController.YOUTUBE_CHANNEL_ID.equals(""))
            mYouTube.setVisibility(View.VISIBLE);
        else
            mYouTube.setVisibility(View.GONE);

        if (!AppController.INSTAGRAM_PAGE_URL.equals(""))
            mInstagram.setVisibility(View.VISIBLE);
        else
            mInstagram.setVisibility(View.GONE);

        if (!AppController.TWITTER_ID.equals(""))
            mTwitter.setVisibility(View.VISIBLE);
        else
            mTwitter.setVisibility(View.GONE);

        if (!AppController.E_MAIL.equals("")) {
            mEmail.setVisibility(View.VISIBLE);
            mEmail.setText("E-mail : " + AppController.E_MAIL);
        } else
            mEmail.setVisibility(View.GONE);


        mFaceBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!AppController.FACEBOOK_PAGE_ID.equals("") && !AppController.FACEBOOK_PAGE_ID.equalsIgnoreCase("<...your Facebook page id..>")) {
                    openFaceBookPage();
                } else {
                    Toast.makeText(getActivity(), "Url not specified", Toast.LENGTH_SHORT).show();
                }

            }
        });

        mWebSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!AppController.WEBSITE_URL.equals("") && !AppController.WEBSITE_URL.equalsIgnoreCase("<...your website...>")) {
                    openWebSite();
                } else {
                    Toast.makeText(getActivity(), "Url not specified", Toast.LENGTH_SHORT).show();
                }


            }
        });


        mYouTube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!AppController.YOUTUBE_CHANNEL_ID.equals("") && !AppController.YOUTUBE_CHANNEL_ID.equalsIgnoreCase("<...your Youtube channel id..>")) {
                    openYotube();
                } else {
                    Toast.makeText(getActivity(), "Url not specified", Toast.LENGTH_SHORT).show();
                }


            }
        });

        mInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!AppController.INSTAGRAM_PAGE_URL.equals("") && !AppController.INSTAGRAM_PAGE_URL.equalsIgnoreCase("<...your Instagram page url..>")) {
                    openInstragram();
                } else {
                    Toast.makeText(getActivity(), "Url not specified", Toast.LENGTH_SHORT).show();
                }


            }
        });


        mTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!AppController.TWITTER_ID.equals("") && !AppController.TWITTER_ID.equalsIgnoreCase("<...your Twitter_id ...>")) {
                    openTwitter();
                } else {
                    Toast.makeText(getActivity(), "Url not specified", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void openInstragram() {


        Uri uri = Uri.parse(AppController.INSTAGRAM_PAGE_URL);
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(AppController.INSTAGRAM_PAGE_URL)));
        }
    }

    private void openTwitter() {

        Intent intent;
        try {
            // get the Twitter app if possible
            getActivity().getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + AppController.TWITTER_ID));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e) {
            // no Twitter app, revert to browser
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + AppController.TWITTER_ID + "?lang=en"));
        }
        getActivity().startActivity(intent);


    }


    private void openWebSite() {

        Intent intent = null;
        try {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(AppController.WEBSITE_URL));
            getActivity().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void openYotube() {


        Intent intent = null;

        try {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setPackage("com.google.android.youtube");
            intent.setData(Uri.parse(AppController.YOUTUBE_CHANNEL_ID));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(AppController.YOUTUBE_CHANNEL_ID));
            startActivity(intent);
        }
    }


    private void openFaceBookPage() {

        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
        String facebookUrl = getFacebookPageURL(getContext());
        facebookIntent.setData(Uri.parse(facebookUrl));
        startActivity(facebookIntent);
    }


    ////
    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + AppController.FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + AppController.FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return AppController.FACEBOOK_URL; //normal web url
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Activity a = getActivity();
            if (a != null) a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }


}
