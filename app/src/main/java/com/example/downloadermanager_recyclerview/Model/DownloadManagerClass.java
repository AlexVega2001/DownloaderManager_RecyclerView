package com.example.downloadermanager_recyclerview.Model;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.downloadermanager_recyclerview.MainActivity;

public class DownloadManagerClass {

    Context ctx;

    public DownloadManagerClass() {
    }

    public DownloadManagerClass(Context context) {
        this.ctx = context;
    }

    public void startDownload(String url, String title) {
        String subTitle = title.substring(12);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("PDF");
        request.setTitle(subTitle + ".pdf");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, subTitle + ".pdf");
        DownloadManager manager = (DownloadManager) ctx.getSystemService(Context.DOWNLOAD_SERVICE);
        try {
            manager.enqueue(request);
        } catch (Exception e) {
            Toast.makeText(ctx, "Error: " + e, Toast.LENGTH_SHORT).show();
        }

    }
}
