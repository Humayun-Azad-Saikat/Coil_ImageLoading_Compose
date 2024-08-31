package com.example.imageloading_with_coil

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger

class Myapplication:Application(),ImageLoaderFactory {
    override fun newImageLoader(): ImageLoader {
        return ImageLoader(this).newBuilder()
            //memory cache
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.1)
                    .strongReferencesEnabled(true) //it means cache wont save as garbage
                    //.weakReferencesEnabled() //it means cache will save as garbage
                    .build()
            }
            //disk cache
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .maximumMaxSizeBytes(1024*4L) //4MB
                    .directory(cacheDir)
                    .build()
            }
            .logger(DebugLogger()) //it will logcat the behind the scences

            .build()

        //thus we have many mathods/members/functions
    }

}