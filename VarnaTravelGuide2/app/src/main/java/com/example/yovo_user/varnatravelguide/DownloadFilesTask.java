package com.example.yovo_user.varnatravelguide;

import android.app.DownloadManager;
import android.os.AsyncTask;

import java.net.URI;
import java.net.URL;

public class DownloadFilesTask extends AsyncTask<URL, Integer, Long>{

    @Override
    protected Long doInBackground(URL... urls)
    {
        int count = urls.length;
        long totalSize = 0;
        for (int i = 0; i < count; i++)
        {
            //totalSize += DownloadManager.
                   // downloadFile(urls[i]);
            publishProgress((int) ((i / (float) count) * 100));
            // Escape early if cancel() is called
            if (isCancelled()) break;
        }
        return totalSize;
    }
}
