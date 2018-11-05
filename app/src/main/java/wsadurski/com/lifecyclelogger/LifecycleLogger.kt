package wsadurski.com.lifecyclelogger

import android.app.Activity
import android.util.Log
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect

@Aspect
object LifecycleLogger {

    private var logLevel: LogLevel =
        LogLevel.NONE

    @After("execution(* android.app.Activity+.onCreate(*))")
    fun onCreate(joinPoint: JoinPoint) {
        try {
            val activity = joinPoint.target as Activity
            log("onCreate() " + activity::class.java.simpleName)
        } catch (e: Throwable) {
            log("Caught throwable: $e")
        }
    }

    fun start(logLevel: LogLevel) {
        LifecycleLogger.logLevel = logLevel
    }

    fun stop() {
        logLevel =
            LogLevel.NONE
    }

    enum class LogLevel {
        VERBOSE, DEBUG, INFO, WARN, ERROR, NONE
    }

    fun log(message: String) {
        when (logLevel) {
            LogLevel.VERBOSE -> Log.v(this.javaClass.simpleName, message)
            LogLevel.DEBUG -> Log.d(this.javaClass.simpleName, message)
            LogLevel.INFO -> Log.i(this.javaClass.simpleName, message)
            LogLevel.WARN -> Log.w(this.javaClass.simpleName, message)
            LogLevel.ERROR -> Log.e(this.javaClass.simpleName, message)
            LogLevel.NONE -> {
                //do not log
            }
        }
    }
}