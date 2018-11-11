package com.jzbrooks.missingclassnamereflection

interface DummyContract {
    interface View : LifecycleAwareContract.View
    abstract class Presenter : LifecycleAwareContract.Presenter<View>()
}