package com.trst01.locationtracker.di.module;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.trst01.locationtracker.di.key.ViewModelKey;
import com.trst01.locationtracker.view_models.AppViewModel;
import com.trst01.locationtracker.view_models.FactoryViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AppViewModel.class)
    abstract ViewModel bindDynamicUIViewModel(AppViewModel repoViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}
