package com.meuuh.dev.chatapp2.navigation;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Edouard on 25/05/2015.
 */
@Module
public class NavigatorModule {
    Navigator navigator = null;

    @Provides
    @Singleton
    public Navigator provideNavigator() {
        if (this.navigator == null) this.navigator = new Navigator();
        return this.navigator;
    }
}
