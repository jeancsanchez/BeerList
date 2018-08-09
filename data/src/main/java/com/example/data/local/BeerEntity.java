package com.example.data.local;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.domain.models.Beer;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents...
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 09/08/18.
 * Jesus is alive!
 */
@Entity
public class BeerEntity {

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

    public static BeerEntity mapIn(Beer beer) {
        BeerEntity beerEntity = new BeerEntity();
        beerEntity.id = beer.getId();
        beerEntity.name = beer.getName();
        beerEntity.tagLine = beer.getTagLine();
        beerEntity.description = beer.getDescription();
        beerEntity.imageUrl = beer.getImageUrl();
        beerEntity.isFavorite = beer.isFavorite();
        return beerEntity;
    }

    public static List<BeerEntity> mapIn(List<Beer> beers) {
        List<BeerEntity> beerEntities = new ArrayList<>();

        for (Beer beer : beers) {
            BeerEntity beerEntity = new BeerEntity();
            beerEntity.id = beer.getId();
            beerEntity.name = beer.getName();
            beerEntity.tagLine = beer.getTagLine();
            beerEntity.description = beer.getDescription();
            beerEntity.imageUrl = beer.getImageUrl();
            beerEntity.isFavorite = beer.isFavorite();

            beerEntities.add(beerEntity);
        }

        return beerEntities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public Beer mapOut() {
        Beer beer = new Beer();

        beer.setId(id);
        beer.setName(name);
        beer.setTagLine(tagLine);
        beer.setDescription(description);
        beer.setImageUrl(imageUrl);
        beer.setFavorite(isFavorite);
        return beer;
    }
}
