package com.popularapi.helper;

import com.popularapi.model.view.ViewResult;
import com.popularapi.model.email.EmailResult;
import com.popularapi.model.share.ShareResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Converter {

    private static List<EmailResult> mEmailDataset = new ArrayList<>();
    private static List<ShareResult> mShareDataset = new ArrayList<>();
    private static List<ViewResult> mViewDataset = new ArrayList<>();

    private static Map<String, String> allUrl = new HashMap<>();
    private static Map<String, String> allImageUrl = new HashMap<>();

    public static void convertResToMap() {
        allUrl.clear();
        if (!mEmailDataset.isEmpty()) {
            for (EmailResult result : mEmailDataset) {
                allUrl.put(result.getTitle(), result.getUrl());
                allImageUrl.put(result.getTitle(), result.getMedia().get(0).getMediaMetadata().get(0).getUrl());
            }
        }
        if (!mShareDataset.isEmpty()) {
            for (ShareResult result : mShareDataset) {
                allUrl.put(result.getTitle(), result.getUrl());
                allImageUrl.put(result.getTitle(), result.getMedia().get(0).getMediaMetadata().get(0).getUrl());
            }
        }
        if (!mViewDataset.isEmpty()) {
            for (ViewResult result : mViewDataset) {
                allUrl.put(result.getTitle(), result.getUrl());
                allImageUrl.put(result.getTitle(), result.getMedia().get(0).getMediaMetadata().get(0).getUrl());
            }
        }
    }

//    public static byte[] convertImageToArr(ImlageView image) {
//        BitmapDrawable bitmapDrawable = (BitmapDrawable) image.getDrawable();
//        Bitmap bitmap = bitmapDrawable.getBitmap();
//
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//        byte[] imageInByte = stream.toByteArray();
//        return imageInByte;
//
//        // ByteArrayInputStream bis = new ByteArrayInputStream(imageInByte);
//
////        Bitmap bmp = BitmapFactory.decodeResource(image.getResources(), image.getDrawable());
////        ByteArrayOutputStream stream = new ByteArrayOutputStream();
////        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
////        byte[] byteArray = stream.toByteArray();
//    }

    public static void setmEmailDataset(List<EmailResult> mEmailDataset) {
        Converter.mEmailDataset = mEmailDataset;
        convertResToMap();
    }

    public static void setmShareDataset(List<ShareResult> mShareDataset) {
        Converter.mShareDataset = mShareDataset;
        convertResToMap();
    }

    public static void setmViewDataset(List<ViewResult> mViewDataset) {
        Converter.mViewDataset = mViewDataset;
        convertResToMap();
    }

    public static Map<String, String> getAllUrl() {
        return allUrl;
    }
}
