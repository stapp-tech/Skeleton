# Skeleton

[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)
[![Maven Central](https://jitpack.io/v/stapp-tech/Skeleton.svg)](https://jitpack.io/#stapp-tech/Skeleton/Tag)

The library provides an easy way to show skeleton loading view like Facebook. 
It now uses a memory optimised version of shimmer animation so it is even faster and you can animate bigger layouts as well.

# Preview

![img](screenshots/01.gif)
![img](screenshots/02.gif)
![img](screenshots/03.gif)
![img](screenshots/04.gif)

# Getting started

In your build.gradle:
```groovy
dependencies {
    implementation 'com.github.stapp-tech:Skeleton:1.4.0'
}
```

# Usage

For RecyclerView:
```kotlin
Skeleton.bind(recyclerView)
    .adapter(adapter)
    .load(R.layout.item_skeleton_news)
    .show()
```

For View:
```kotlin
Skeleton.bind(rootView)
    .load(R.layout.layout_img_skeleton)
    .show()
```

More Config:
```kotlin
.shimmer(true)      // whether show shimmer animation.                      default is true
.count(10)          // the recycler view item count.                        default is 10
.color(color)       // the shimmer color.                                   default is #a2878787
.angle(20)          // the shimmer angle.                                   default is 20;
.duration(1000)     // the shimmer animation duration.                      default is 1000;
.frozen(false)      // whether frozen recyclerView during skeleton showing  default is true; 
```
                            
when data return you can call the method to hide skeleton loading view 
```kotlin
skeletonScreen.hide()
```

# License

`ShimmerLayout` is opensource, contribution and feedback are welcome!
[Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)
```
Copyright 2017 Supercharge

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
