package ru.clevertec.gordievich.plugin

import ru.clevertec.gordievich.task.FileDownloader
import org.gradle.api.Plugin
import org.gradle.api.Project

class CustomPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        downloadFromGitHub(project)
    }

    void downloadFromGitHub(Project project) {
        def map = [description: "download task", group: "download", type: FileDownloader]
        project.task(map, "downloadFromGitHub") {
            sourceUrl = 'https://raw.githubusercontent.com/Natashanihoho/Clevertec/master/receipt.txt'
            target = new File('src/main/resources/receiptFromGit.txt')
        }
    }
}
