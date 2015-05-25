package com.meuuh.dev.chatapp2.navigation;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Edouard on 25/05/2015.
 */
@Singleton
@Component(modules = {NavigatorModule.class})
public interface NavigatorComponent {
    Navigator provideNavigator();
}
