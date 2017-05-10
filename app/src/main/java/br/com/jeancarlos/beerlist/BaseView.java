package br.com.jeancarlos.beerlist;

/**
 * This interface represents a base of methods for all Views.
 *
 * @author Jean Carlos
 * @since 5/10/17.
 */

public interface BaseView<T> {

    void setPresenter(T presenter);
}

