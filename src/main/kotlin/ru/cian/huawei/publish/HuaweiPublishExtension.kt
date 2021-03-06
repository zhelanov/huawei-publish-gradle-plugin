package ru.cian.huawei.publish

import org.gradle.api.Project
import ru.cian.huawei.publish.utils.GradleProperty

open class HuaweiPublishExtension(
        project: Project
) {

    val instances = project.container(HuaweiPublishConfig::class.java) { name ->
        HuaweiPublishConfig(name, project)
    }

    companion object {
        const val NAME = "huaweiPublish"
    }
}

class HuaweiPublishConfig(
    val name: String,
    project: Project
) {

    init {
        if (name.isBlank()) {
            throw IllegalArgumentException("Name must not be blank nor empty")
        }
    }

    var credentialsPath by GradleProperty(project, String::class.java)
    var publish: Boolean = true
    var buildFormat: BuildFormat = BuildFormat.APK
    var buildFile: String? = null

    override fun toString(): String {
        return "HuaweiPublishCredential(" +
                "name='$name', " +
                "credentialsPath='$credentialsPath', " +
                "publish='$publish', " +
                "buildFormat='$buildFormat', " +
                "buildFile='$buildFile'" +
                ")"
    }
}

enum class BuildFormat(val fileExtension: String) {
    APK("apk"),
    AAB("aab")
}
