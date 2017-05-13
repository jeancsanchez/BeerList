package br.com.jeancarlos.beerlist.beerslist.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * This class represents a model for Beers
 * <p>
 * This class implements the Parcelable interface, since it is much faster than serializable
 * with Binder, because serializable use reflection and cause many GC. Parcelable is design to
 * optimize to pass object.
 *
 * @author Jean Carlos
 * @since 5/10/17
 */

public class Beer implements Parcelable {
    public static final Creator<Beer> CREATOR = new Creator<Beer>() {
        @Override
        public Beer createFromParcel(Parcel in) {
            return new Beer(in);
        }


        @Override
        public Beer[] newArray(int size) {
            return new Beer[size];
        }
    };
    private double id;
    private String name;
    @SerializedName("tagline")
    private String tagLine;
    @SerializedName("description")
    private String description;
    @SerializedName("image_url")
    private String imageUrl;

    /* Parcelable implementation - BEGIN */
    protected Beer(Parcel in) {
        id = in.readDouble();
        name = in.readString();
        tagLine = in.readString();
        description = in.readString();
        imageUrl = in.readString();
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getTagLine() {
        return tagLine;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(id);
        dest.writeString(name);
        dest.writeString(tagLine);
        dest.writeString(description);
        dest.writeString(imageUrl);
    }
    /* Parcelable implementation - END */


    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Beer)) return false;
        return this.id == ((Beer) object).getId();
    }
}
