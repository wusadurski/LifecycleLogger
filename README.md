## Simple Android lifecycle logger

How many times did you have to debug lifecycle-related issues in your Android app?

I had to a lot of times and every time I did it I dreamed of a toggle to switch on logging of lifecycle events from ALL of my Activities in an instant. But it was never there.. So I did some research and came up with an idea that partially solves the problem.

This project is based on a concept of aspect-oriented programming and uses 
[https://github.com/Archinamon/android-gradle-aspectj]
plugin.

Usage
-----
A few simple steps:

1. Add a maven repository link into your `repositories` block of root build.gradle file:
```groovy
mavenCentral()
```

2. Add the plugin to your buildscript's dependencies section:
```
classpath 'com.archinamon:android-gradle-aspectj:3.2.0'
```
3. Apply the `aspectj` plugin in your `application` module:
```
apply plugin: 'com.archinamon.aspectj'
```
4. Copy the `LifecycleLogger.kt` class into your project.
5. Start the logger with: `LifecycleLogger.start(LifecycleLogger.LogLevel.VERBOSE)`
in your Application's `onCreate()`

And then you can see onCreate() logs from all your Activities in LogCat:

```
2018-11-05 22:59:26.068 27743-27743/wsadurski.com.lifecyclelogger V/LifecycleLogger: onCreate() MainActivity
2018-11-05 22:59:28.339 27743-27743/wsadurski.com.lifecyclelogger V/LifecycleLogger: onCreate() SecondActivity
```

Problems
-----
Unfortunately there are few..

1. The project will not work if exported as a library because it cannot access the host app's classes at build time. 
2. Now it only logs Activity `onCreate()` callbacks because it can only log callbacks overriden by the user and `onCreate()` is always the most probable one to have been overriden. There is no way we can intercept not overriden lifecycle callbacks. We can add other methods with `@After` annotation to the logger class but only those methods they point at which are overriden in application code will be logged.
3. Behaviour in multi-module projects has not been analyzed yet.

License
-------

    Copyright 2018 Wojciech Sadurski.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.