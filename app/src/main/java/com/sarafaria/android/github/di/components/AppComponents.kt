package com.sarafaria.android.github.di.components

import com.sarafaria.android.github.application.AppController

object AppComponents {

    private lateinit var coreComponent: BaseComponent

    @JvmStatic
    fun core(): BaseComponent =
        coreComponent

    @JvmStatic
    fun initComponents(app: AppController) {
        coreComponent = DaggerBaseComponent.factory().create(app) as BaseComponent
        coreComponent.inject(app)

        BaseAppComponents.application = app
    }
}