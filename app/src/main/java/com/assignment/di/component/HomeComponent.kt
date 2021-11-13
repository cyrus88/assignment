package com.assignment.di.component

import com.assignment.features.home.views.HomeActivity
import dagger.Subcomponent

// Definition of a Dagger subcomponent
@Subcomponent
interface HomeComponent {
    // Factory to create instances of AnimeComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    // Classes that can be injected by this Component
    fun inject(homeActivity: HomeActivity)
}