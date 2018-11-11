package com.jzbrooks.missingclassnamereflection

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.annotation.CallSuper

interface LifecycleAwareContract {
    interface View : BaseContract.View, LifecycleOwner

    abstract class Presenter<T : View> : BaseContract.Presenter<T>, LifecycleObserver {

        /**
         * Sets up a contract view as a lifecycle owner.
         *
         * This method must be called when a lifecycle owner is
         * initializing so that it may have a reference to a view.
         *
         * Presenters should not assume that a view instance is available
         * as orientation changes will tear down most lifecycle owners.
         */
        @CallSuper
        override fun takeView(view: T) {
            this.view = view
            this.view?.lifecycle?.addObserver(this)
        }

        /**
         * Cleans up references to the contract view.
         *
         * This method is safe to call manually, but will at
         * least be executed when the lifecycle owner is destroyed.
         */
        @CallSuper
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        override fun releaseView() {
            view?.lifecycle?.removeObserver(this)
            view = null
        }

        final override var view: T? = null
    }
}
