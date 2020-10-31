package br.com.jardiano.retrofitapp;
import com.google.gson.annotations.SerializedName;

public class DataModel {

    private int userId;
    private int id;
    private String title;

    @SerializedName("completed")
    private boolean status;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isStatus() {
        return status;
    }
}
