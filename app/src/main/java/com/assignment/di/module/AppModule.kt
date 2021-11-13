package com.assignment.di.module

import com.assignment.di.component.HomeComponent
import dagger.Module

// This module tells a Component which are its subcomponents
@Module(subcomponents = [HomeComponent::class])
class AppModule