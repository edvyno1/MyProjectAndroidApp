package com.example.myprojectandroidapp.ds;

public class File {
    private int id;
    private String name;
    private Folder folder;

    public File(int id, String name, Folder folder) {
        this.id = id;
        this.name = name;
        this.folder = folder;
    }

    public File() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }
}
