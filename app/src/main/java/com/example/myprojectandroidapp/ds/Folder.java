package com.example.myprojectandroidapp.ds;

import java.io.Serializable;
import java.util.List;

public class Folder implements Serializable {
    private int id;
    private String title;
    //private Course parentCourse;
    //private List<Folder> subFolders;
    //private Folder parentFolder;
    //private List<File> folderFiles;

    public Folder() {
    }

    public Folder(int id, String title/*, Course parentCourse, List<Folder> subFolders, Folder parentFolder, List<File> folderFiles*/) {
        this.id = id;
        this.title = title;
        //this.parentCourse = parentCourse;
        //this.subFolders = subFolders;
        //this.parentFolder = parentFolder;
        //this.folderFiles = folderFiles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /*public Course getParentCourse() {
        return parentCourse;
    }

    public void setParentCourse(Course parentCourse) {
        this.parentCourse = parentCourse;
    }*/

    /*public List<Folder> getSubFolders() {
        return subFolders;
    }

    public void setSubFolders(List<Folder> subFolders) {
        this.subFolders = subFolders;
    }
*/
    /*public Folder getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
    }

    public List<File> getFolderFiles() {
        return folderFiles;
    }

    public void setFolderFiles(List<File> folderFiles) {
        this.folderFiles = folderFiles;
    }*/

    @Override
    public String toString() {
        return title;
    }
}
