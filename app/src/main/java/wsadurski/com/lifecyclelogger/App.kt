package wsadurski.com.lifecyclelogger

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        LifecycleLogger.start(LifecycleLogger.LogLevel.VERBOSE)
    }
}