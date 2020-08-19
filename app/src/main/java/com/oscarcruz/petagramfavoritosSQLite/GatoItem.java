package com.oscarcruz.petagramfavoritosSQLite;

public class GatoItem {

    private int imageResource;
    private String title;
    private String key_id;
    private String favStatus;

    public GatoItem() {
    }

    public GatoItem(int imageResource, String title, String key_id, String favStatus) {
        this.imageResource = imageResource;
        this.title = title;
        this.key_id = key_id;
        this.favStatus = favStatus;
    }

    public int getImageResourse() {
        return imageResource;
    }

    public void setImageResourse(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(String favStatus) {
        this.favStatus = favStatus;
    }
}
