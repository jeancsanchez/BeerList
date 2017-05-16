package br.com.jeancarlos.beerlist.features.beerslist.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

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

public class Beer extends RealmObject implements Parcelable {

    @PrimaryKey
    private int id;

    private String name;

    @SerializedName("tagline")
    private String tagLine;

    @SerializedName("description")
    private String description;

    @SerializedName("image_url")
    private String imageUrl;

    private boolean isFavorite;

    /**
     * Default constructor
     */
    public Beer() {
    }

    /**
     * Beer constructor
     *
     * @param id Beer unique id
     */
    public Beer(int id) {
        this.id = id;
    }

    /**
     * Beer constructor
     *
     * @param id   Beer unique id
     * @param name Beer name
     */
    public Beer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    protected Beer(Parcel in) {
        id = in.readInt();
        name = in.readString();
        tagLine = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        isFavorite = in.readInt() == 1;
    }

    public double getId() {
        return id;
    }

    public void setId(int id) {
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

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }


    // Parcelable implementation - BEGIN
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(tagLine);
        dest.writeString(description);
        dest.writeString(imageUrl);
        dest.writeInt(isFavorite ? 1 : 0);
    }
    // Parcelable implementation - END

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Beer)) return false;
        return this.id == ((Beer) object).getId();
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}
