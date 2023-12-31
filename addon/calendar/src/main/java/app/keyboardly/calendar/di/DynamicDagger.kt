/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package app.keyboardly.calendar.di

import app.keyboardly.calendar.CalendarDefaultClass
import app.keyboardly.calendar.DynamicFeatureImpl
import app.keyboardly.calendar.http.GoogleApi
import app.keyboardly.calendar.http.RetrofitClient
import app.keyboardly.calendar.services.LogoutService
import app.keyboardly.lib.KeyboardActionDependency
import app.keyboardly.lib.reflector.DynamicFeature
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton


/**
 * This component for build the default class
 */
@Singleton
@Component(
    modules = [DynamicModule::class],
    dependencies = [DynamicFeature.Dependencies::class] // needs dependencies passed in to create component
)
interface DynamicComponent {
    fun dynamicFeature(): DynamicFeature
}

/**
 * You have access to objects provided by the DynamicFeature.Dependencies interface from the base component.
 */
@Module
class DynamicModule {
    /**
     * fit with name default class
     * @param dependency
     * @return the default class
     */
    @Provides
    internal fun provideDefaultClass(dependency: KeyboardActionDependency) = CalendarDefaultClass(dependency)

    /**
     * bind the DynamicFeatureImpl
     * @param featureImpl: DynamicFeatureImpl
     */
    @Provides
    internal fun bindDynamicImpl(featureImpl: DynamicFeatureImpl): DynamicFeature = featureImpl

    @Provides
    @Named("getGoogleApi")
    fun provideGoogleApi(): GoogleApi? {
        return RetrofitClient.getGoogleApi()
    }

    @Provides
    fun provideRetrofitClient(): RetrofitClient {
        return RetrofitClient()
    }
    @Provides
    fun provideLogOutService(): LogoutService {
        return LogoutService()
    }

    @Provides
    @Named("getGoogleEvents")
    fun provideGoogleEvents(): GoogleApi? {
        return RetrofitClient.getGoogleEvents()
    }
}
