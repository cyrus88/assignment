package com.assignment.di.module

import com.assignment.di.component.HomeComponent
import dagger.Module

// The "subcomponents" attribute in the @Module annotation tells Dagger what
// Subcomponents are children of the Component this module is included in.
@Module(subcomponents = [HomeComponent::class])
class SubComponentModule