package com.webandcrafts.vstream.bettervideoplayer.subtitle;

import java.io.File;

public interface DownloadCallback {
    public void onDownload(File file);
    public void onFail(Exception e);
}
