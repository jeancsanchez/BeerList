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
    private double id;

    private String name;

    @SerializedName("tagline")
    private String tagLine;

    @SerializedName("first_brewed")
    private String firstBrewed;

    @SerializedName("description")
    private String description;

    @SerializedName("image_url")
    private String imageUrl;

    private double abv;

    private double ibu;

    @SerializedName("target_fg")
    private double targetFg;

    @SerializedName("target_og")
    private double targetOg;

    private double ebc;

    private double srm;

    private double ph;

    @SerializedName("attenuation_level")
    private double attenuationLevel;

    private Volume volume;

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public String getFirstBrewed() {
        return firstBrewed;
    }

    public void setFirstBrewed(String firstBrewed) {
        this.firstBrewed = firstBrewed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public double getIbu() {
        return ibu;
    }

    public void setIbu(double ibu) {
        this.ibu = ibu;
    }

    public double getTargetFg() {
        return targetFg;
    }

    public void setTargetFg(double targetFg) {
        this.targetFg = targetFg;
    }

    public double getTargetOg() {
        return targetOg;
    }

    public void setTargetOg(double targetOg) {
        this.targetOg = targetOg;
    }

    public double getEbc() {
        return ebc;
    }

    public void setEbc(double ebc) {
        this.ebc = ebc;
    }

    public double getSrm() {
        return srm;
    }

    public void setSrm(double srm) {
        this.srm = srm;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }

    public double getAttenuationLevel() {
        return attenuationLevel;
    }

    public void setAttenuationLevel(double attenuationLevel) {
        this.attenuationLevel = attenuationLevel;
    }

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    /* Parcelable implementation - BEGIN */
    protected Beer(Parcel in) {
        id = in.readDouble();
        name = in.readString();
        tagLine = in.readString();
        firstBrewed = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        abv = in.readDouble();
        ibu = in.readDouble();
        targetFg = in.readDouble();
        targetOg = in.readDouble();
        ebc = in.readDouble();
        srm = in.readDouble();
        ph = in.readDouble();
        attenuationLevel = in.readDouble();
        volume = in.readParcelable(Volume.class.getClassLoader());
    }

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
        dest.writeDouble(id);
        dest.writeString(name);
        dest.writeString(tagLine);
        dest.writeString(firstBrewed);
        dest.writeString(description);
        dest.writeString(imageUrl);
        dest.writeDouble(abv);
        dest.writeDouble(ibu);
        dest.writeDouble(targetFg);
        dest.writeDouble(targetOg);
        dest.writeDouble(ebc);
        dest.writeDouble(srm);
        dest.writeDouble(ph);
        dest.writeDouble(attenuationLevel);
        dest.writeParcelable(volume, flags);
    }
    /* Parcelable implementation - END */


    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Beer)) return false;
        return this.id == ((Beer) object).getId();
    }
}
