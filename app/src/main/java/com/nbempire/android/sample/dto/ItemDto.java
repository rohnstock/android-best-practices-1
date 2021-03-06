package com.nbempire.android.sample.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by nbarrios on 08/10/14.
 */
public class ItemDto {

    private String id;
    private int initialQuantity;
    private int availableQuantity;
    private String title;
    private float price;
    private Date stopTime;
    private String subtitle;
    private List<PictureDto> pictures;

    public String getId() {
        return id;
    }

    public int getInitialQuantity() {
        return initialQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public List<PictureDto> getPictures() {
        return pictures;
    }
}
