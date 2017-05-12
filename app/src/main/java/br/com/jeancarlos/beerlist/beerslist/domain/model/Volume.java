package br.com.jeancarlos.beerlist.beerslist.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class represents a model for Volumes of the Beers
 * <p>
 * This class implements the Parcelable interface, since it is much faster than serializable
 * with Binder, because serializable use reflection and cause many GC. Parcelable is design to
 * optimize to pass object.
 *
 * @author Jean Carlos
 * @since 5/12/17
 */

public class Volume implements Parcelable {
    private double value;
    private String unit;

    protected Volume(Parcel in) {
        value = in.readDouble();
        unit = in.readString();
    }

    public static final Creator<Volume> CREATOR = new Creator<Volume>() {
        @Override
        public Volume createFromParcel(Parcel in) {
            return new Volume(in);
        }

        @Override
        public Volume[] newArray(int size) {
            return new Volume[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(value);
        dest.writeString(unit);
    }
}
