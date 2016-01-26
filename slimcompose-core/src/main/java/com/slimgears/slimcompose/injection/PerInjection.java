// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by ditskovi on 1/25/2016.
 *
 */
@Scope
@Retention(RetentionPolicy.SOURCE)
public @interface PerInjection {
}
