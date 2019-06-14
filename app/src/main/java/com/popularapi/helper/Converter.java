package com.popularapi.helper;

import com.bumptech.glide.Glide;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Converter {

    public static byte[] convertImageToArr(ImageView image) {
        return null;
//        Log.i("CONVERT URI", allImageUrl.toString());
//
//        byte[] byteArray = null;
//
//        try {
//            Uri imageUri = Uri.parse(ActiveTab.getImageUrl());
//            Bitmap bitmap = MediaStore.Images.Media.getBitmap(image.getContext().getContentResolver(), imageUri);
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            byteArray = stream.toByteArray();
//
//            Log.i("CONVERT", byteArray.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return byteArray;

//        BitmapDrawable bitmapDrawable = (BitmapDrawable) image.getDrawable();
//        Bitmap bitmap = bitmapDrawable.getBitmap();

//        image.setDrawingCacheEnabled(true);
//        Bitmap bitmap = image.getDrawingCache();
//
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, stream);
//        byte[] imageInByte = stream.toByteArray();
//        return imageInByte;

        // ByteArrayInputStream bis = new ByteArrayInputStream(imageInByte);

//        Bitmap bmp = BitmapFactory.decodeResource(image.getResources(), image.getDrawable());
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
//        byte[] byteArray = stream.toByteArray();
    }

}
