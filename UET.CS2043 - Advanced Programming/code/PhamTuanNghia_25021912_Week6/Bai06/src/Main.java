import java.util.ArrayList;

interface FileSystemItem{
    void print(String indent);
}

class FileItem implements FileSystemItem{
    //attributes
    private String name;
    private int size;

    //constructor
    public FileItem(String name, int size){
        this.name = name;
        this.size = size;
    }

    @Override
    public void print(String indent){
        System.out.println(indent + "File: " + this.name + " (" + this.size + "KB)");
    }
}

class ShortCut implements FileSystemItem{
    //attributes
    private String name;
    private String targetPath;

    //constructor
    public ShortCut(String name, String targetPath){
        this.name = name;
        this.targetPath = targetPath;
    }

    @Override 
    public void print(String indent){
        System.out.println(indent + "Shortcut: " + this.name + " -> " + this.targetPath);
    }
}

class Folder implements FileSystemItem{
    private String name;
    private ArrayList<FileSystemItem> children = new ArrayList<>();

    //constructor
    public Folder(String name){
        this.name = name;
    }

    //method to add new elements to the folder
    public void add(FileSystemItem child){
        children.add(child);
    }

    @Override
    public void print(String indent){
        //printing the name of the folder
        System.out.println(indent + "Folder: " + this.name);
        
        //printing the children
        for (FileSystemItem child : children){
            child.print(indent.concat("  "));
        }
    }
}

public class Main{
    public static void main(String[] args){
        //creating the files, folders and shortcuts
        Folder root = new Folder("root");
        Folder docs = new Folder("docs");
        FileItem file1 = new FileItem("a.txt", 12);
        FileItem file2 = new FileItem("b.txt", 8);
        ShortCut shortcut = new ShortCut("a-shortcut", "/root/docs/a.txt");
        FileItem readme = new FileItem("readme.md", 4);

        //assembling
        root.add(docs);
        docs.add(file1);
        docs.add(file2);
        docs.add(shortcut);
        root.add(readme);

        root.print("");

    }
}