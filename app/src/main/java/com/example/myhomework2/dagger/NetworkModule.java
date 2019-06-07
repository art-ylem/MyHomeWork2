package com.example.myhomework2.dagger;

import com.example.myhomework2.useCase.EventsUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public EventsUseCase provideEventsUseCase(){
        return new EventsUseCase();
    }
}
