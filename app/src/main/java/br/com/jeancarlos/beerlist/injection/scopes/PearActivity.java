package br.com.jeancarlos.beerlist.injection.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * This is a Dagger scope for identify each activity scope
 *
 * @author Jean Carlos
 * @since 5/10/17
 */


@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PearActivity {
}
