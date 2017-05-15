package br.com.jeancarlos.beerlist.injection.scopes;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * This is a Dagger scope to identify Local repository
 *
 * @author Jean Carlos
 * @since 5/10/17
 */

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Local {
}
