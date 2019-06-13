package com.popularapi.helper;

public class ActiveTab {

    private static String title;
    private static String activeUrl;
    private static String imageUrl;

    private static byte[] image;

    public static String getTitle() {
        return title;
    }

    public static void setTitle(String title) {
        ActiveTab.title = title;
    }

    public static String getActiveUrl() {
        return activeUrl;
    }

    public static void setActiveUrl(String activeUrl) {
        ActiveTab.activeUrl = activeUrl;
    }

    public static byte[] getImage() {
        return image;
    }

    public static void setImage(byte[] image) {
        ActiveTab.image = image;
    }

    public static String getImageUrl() {
        return imageUrl;
    }

    public static void setImageUrl(String imageUrl) {
        ActiveTab.imageUrl = imageUrl;
    }
}
