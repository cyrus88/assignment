package com.assignment.di.component

import com.assignment.di.module.SubComponentModule
import com.assignment.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton
// Classes annotated with @Singleton will have a unique instance in this Component
@Singleton
// Definition of a Dagger component that adds info from the different modules to the graph
@Component(modules = [SubComponentModule::class, NetworkModule::class])
interface AppComponent {
    fun homeComponent(): HomeComponent.Factory
}