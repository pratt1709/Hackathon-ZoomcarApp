package com.prateek.zoomcar.rest;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by prateek.kesarwani on 13/09/2015.
 */

public class ListCarsResponseDto {

    private enum CarType {
        @SerializedName("Hatchback")HATCHBACK,
        @SerializedName("Sedan")SEDAN,
        @SerializedName("Executive")EXECUTIVE,
        SUV
    }

    private enum AcAvailability {
        @SerializedName("0")
        NON_AC,
        @SerializedName("1")
        AC
    }

    public static class Cars {
        String name;
        String image;
        CarType type;
        String hourly_rate;
        String rating;
        @SerializedName("ac")
        AcAvailability acAvailability;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public CarType getType() {
            return type;
        }

        public void setType(CarType type) {
            this.type = type;
        }

        public String getHourly_rate() {
            return hourly_rate;
        }

        public void setHourly_rate(String hourly_rate) {
            this.hourly_rate = hourly_rate;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public AcAvailability getAcAvailability() {
            return acAvailability;
        }

        public void setAcAvailability(AcAvailability acAvailability) {
            this.acAvailability = acAvailability;
        }
    }

    ArrayList<Cars> cars;

    public ArrayList<Cars> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Cars> cars) {
        this.cars = cars;
    }

    public static class SortByPrice implements Comparator<Cars> {

        @Override
        public int compare(Cars lhs, Cars rhs) {
            if (Integer.parseInt(lhs.getHourly_rate()) < Integer.parseInt(rhs.getHourly_rate())) {
                return -1;
            } else if (Integer.parseInt(lhs.getHourly_rate()) == Integer.parseInt(rhs.getHourly_rate())) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    public static class SortByRating implements Comparator<Cars> {

        @Override
        public int compare(Cars lhs, Cars rhs) {
            if (Float.parseFloat(lhs.getRating()) > Float.parseFloat(rhs.getRating())) {
                return -1;
            } else if (Float.parseFloat(lhs.getRating()) == Float.parseFloat(rhs.getRating())) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}