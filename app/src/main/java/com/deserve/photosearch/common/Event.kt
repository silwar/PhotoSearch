package com.deserve.photosearch.common

class Event<T> constructor(private var content: T) {
    private var hasBeenHandled = false

    fun getContent(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun isAlreadyHandled(): Boolean {
        return hasBeenHandled
    }

    fun isNotHandled(): Boolean {
        return !hasBeenHandled
    }

    fun peekContent(): T {
        return content
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is Event<*>)
            return false
        return if (other === this) true else this.peekContent() === other.peekContent()
                && this.hasBeenHandled == other.hasBeenHandled
    }

    override fun hashCode(): Int {
        return arrayOf(content, hasBeenHandled).contentHashCode()
    }
}