package com.app.androidtvapp.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.leanback.media.PlaybackGlue;
import androidx.leanback.media.PlaybackTransportControlGlue;

import java.io.File;

public class PlaybackSeekDiskDataProvider extends PlaybackSeekAsyncDataProvider {

    final Paint mPaint;
    final String mPathPattern;
    PlaybackSeekDiskDataProvider(long duration, long interval, String pathPattern) {
        mPathPattern = pathPattern;
        int size = (int) (duration / interval) + 1;
        long[] pos = new long[size];
        for (int i = 0; i < pos.length; i++) {
            pos[i] = i * duration / pos.length;
        }
        setSeekPositions(pos);
        mPaint = new Paint();
        mPaint.setTextSize(16);
        mPaint.setColor(Color.BLUE);
    }

    protected Bitmap doInBackground(Object task, int index, long position) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            // Thread might be interrupted by cancel() call.
        }
        if (isCancelled(task)) {
            return null;
        }
        String path = String.format(mPathPattern, (index + 1));
        if (new File(path).exists()) {
            return BitmapFactory.decodeFile(path);
        } else {
            Bitmap bmp = Bitmap.createBitmap(160, 160, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bmp);
            canvas.drawColor(Color.YELLOW);
            canvas.drawText(path, 10, 80, mPaint);
            canvas.drawText(Integer.toString(index), 10, 150, mPaint);
            return bmp;
        }
    }

    /**
     * Helper function to set a demo seek provider on PlaybackTransportControlGlue based on
     * duration.
     */
    public static void setDemoSeekProvider(final PlaybackTransportControlGlue glue) {
        if (glue.isPrepared()) {
            glue.setSeekProvider(new PlaybackSeekDiskDataProvider(
                    glue.getDuration(),
                    glue.getDuration() / 100,
                    "/sdcard/seek/frame_%04d.jpg"));
        } else {
            glue.addPlayerCallback(new PlaybackGlue.PlayerCallback() {
                @Override
                public void onPreparedStateChanged(PlaybackGlue glue) {
                    if (glue.isPrepared()) {
                        glue.removePlayerCallback(this);
                        PlaybackTransportControlGlue transportControlGlue =
                                (PlaybackTransportControlGlue) glue;
                        transportControlGlue.setSeekProvider(new PlaybackSeekDiskDataProvider(
                                transportControlGlue.getDuration(),
                                transportControlGlue.getDuration() / 100,
                                "/sdcard/seek/frame_%04d.jpg"));
                    }
                }
            });
        }
    }

}