package com.webandcrafts.vstream.bettervideoplayer;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import androidx.appcompat.widget.Toolbar;
import android.view.Window;


import com.webandcrafts.vstream.bettervideoplayer.subtitle.CaptionsView;

import java.util.Map;

/**
 * @author Aidan Follestad (halilibo)
 * This interface defines which methods should be available to
 * library users. The methods found here constitutes the actual
 * interface of the player.
 */
interface IUserMethods {

    void setSource(@NonNull Uri source);

    /**
     * This method also passes given headers when source is behind HTTP.
     * e.g. Authentication header.
     */
    void setSource(@NonNull Uri source, @NonNull Map<String, String> headers);

    void setCallback(@NonNull BetterVideoCallback callback);

    void setProgressCallback(@NonNull BetterVideoProgressCallback callback);

    /**
     * There are 3 default buttons in BetterVideoPlayer: play, pause, restart.
     * You can set their drawable by using this method.
     * @param   type    Which button is being targeted.
     * @param   drawable    Drawable object to use for styling.
     */
    void setButtonDrawable(@BetterVideoPlayer.LoadingStyle int type, @NonNull Drawable drawable);

    void setHideControlsOnPlay(boolean hide);

    void setAutoPlay(boolean autoPlay);

    void setVolume(@FloatRange(from = 0f, to = 1f) float leftVolume,
                   @FloatRange(from = 0f, to = 1f) float rightVolume);

    void setLoop(boolean loop);

    /**
     * BetterVideoPlayer utilizes @see com.github.ybq.android.spinkit.SpinKitView
     * to have 'better' loading animations.
     * @param style A style from SpinKit family.
     */
    void setLoadingStyle(@BetterVideoPlayer.LoadingStyle int style);

    void setHideControlsDuration(int hideControlsDuration);

    void setInitialPosition(@IntRange(from = 0, to = Integer.MAX_VALUE) int pos);

    void setBottomProgressBarVisibility(boolean isShowing);

    void enableSwipeGestures();

    /**
     * This method enables swipe gestures with brightness feature.
     * Reference to Current window is necessary to adjust brightness
     * @param window  Current windows e.g. getActivity().getWindow()
     */
    void enableSwipeGestures(@NonNull Window window);

    void disableSwipeGestures();

    void showToolbar();

    void hideToolbar();

    void showControls();

    void hideControls();

    void toggleControls();

    void enableControls();

    void disableControls();

    void start();

    void seekTo(@IntRange(from = 0, to = Integer.MAX_VALUE) int pos);

    void pause();

    void stop();

    void reset();

    void release();

    // TODO: move captions outside of BetterVideoPlayer.
    void setCaptions(Uri source, CaptionsView.CMime subMime);

    void setCaptions(@RawRes int resId, CaptionsView.CMime subMime);

    void setCaptionLoadListener(@Nullable CaptionsView.CaptionsViewLoadListener listener);

    void removeCaptions();

    /**
     * This enables users to have access to inner Toolbar that is
     * located on top bar.
     * @return current toolbar inside BVP
     */
    Toolbar getToolbar();

    int getCurrentPosition();

    int getDuration();

    int getHideControlsDuration();

    boolean isPrepared();

    boolean isPlaying();

    boolean isControlsShown();
}