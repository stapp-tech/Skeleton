# Skeleton
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)  
The library provides an easy way to show skeleton loading view like Facebook. 
It now uses a memory optimised version of shimmer animation so it is even faster and you can animate bigger layouts as well.

# Preview
![img](screenshots/01.gif)
![img](screenshots/02.gif)
![img](screenshots/03.gif)
![img](screenshots/04.gif)

# Getting started
In your build.gradle:
```
dependencies {
    implementation 'com.github.stapp-tech:Skeleton:1.3.1'
}
```

# Usage
For RecyclerView:
```java
Skeleton.bind(recyclerView)
    .adapter(adapter)
    .load(R.layout.item_skeleton_news)
    .show();
```

For View:
```java
Skeleton.bind(rootView)
    .load(R.layout.layout_img_skeleton)
    .show();
```

More Config:
```java
.shimmer(Shimmer)   // shimmer.                                             default is null
.count(10)          // the recycler view item count.                        default is 10
.frozen(false)      // whether frozen recyclerView during skeleton showing  default is true; 
```
                            
when data return you can call the method to hide skeleton loading view 
```java
skeletonScreen.hide()
```
