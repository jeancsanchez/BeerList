package br.com.jeancarlos.beerlist.injection.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author jeancarlos
 * @since 5/10/17
 */


@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PearActivity {
}
