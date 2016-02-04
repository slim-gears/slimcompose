// Copyright 2015 Denis Itskovich
// Refer to LICENSE.txt for license details
package com.slimgears.slimcompose.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;

import com.slimgears.slimcompose.extensibility.Plugin;

/**
 * Created by ditskovi on 1/3/2016.
 *
 */
public interface ServicePlugin extends Plugin<Service, Intent> {
}
