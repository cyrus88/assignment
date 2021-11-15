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

    // This tells Dagger that LoginActivity requests injection so the graph needs to
    // satisfy all the dependencies of the fields that LoginActivity is injecting.
   fun inject(homeActivity: HomeActivity)
}