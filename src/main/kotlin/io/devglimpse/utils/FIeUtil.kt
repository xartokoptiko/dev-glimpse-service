package io.devglimpse.utils

import io.quarkus.logging.Log
import java.io.File
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class FileUtil {

    fun createMissingDirectories(path: String) {
        val file = File(path)
        if (!file.exists()) {
            file.mkdirs()
            Log.info("Directory created at path: $path")
        } else {
            Log.info("Directory already exists at path: $path")
        }
    }

}