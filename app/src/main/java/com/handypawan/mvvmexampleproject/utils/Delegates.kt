package com.handypawan.mvvmexampleproject.utils

import kotlinx.coroutines.*

/**
 * Created by pawan on 08,June,2020
 */


//We used when we dont need call function continuously we call when we need
fun <T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}