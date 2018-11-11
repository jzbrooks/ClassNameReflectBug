package com.jzbrooks.missingclassnamereflection

interface BaseContract {
    /**
     * A simple interface for view interactions
     */
    interface View

    /**
     * A simple interface for presenter interactions
     */
    interface Presenter<T : View> {
        fun takeView(view: T) {
            this.view = view
        }

        fun releaseView() {
            view = null
        }

        var view: T?
    }
}
