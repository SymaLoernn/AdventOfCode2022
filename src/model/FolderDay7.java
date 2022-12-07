package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FolderDay7 {

    private Map<String, Long> files = new HashMap<>();
    private Map<String, FolderDay7> folderDay7s = new HashMap<>();
    private FolderDay7 parent;
    long size = -1;

    public FolderDay7() {}

    public FolderDay7(FolderDay7 parent) {
        this.parent = parent;
    }

    public long calcSize() {
        if (this.size >= 0) {
            return this.size;
        }
        long result = files.values().stream().mapToLong(Long::longValue).sum();
        result += folderDay7s.values().stream().mapToLong(FolderDay7::calcSize).sum();
        this.size = result;
        return result;
    }

    public FolderDay7 getParent() {
        return parent;
    }

    public FolderDay7 getSubFolder(String folderName) {
        return folderDay7s.get(folderName);
    }

    public void addSubFolder(String folderName) {
        if (!this.folderDay7s.containsKey(folderName)) {
            this.folderDay7s.put(folderName, new FolderDay7(this));
        }
    }

    public void addFile(String size, String fileName) {
        this.files.putIfAbsent(fileName, Long.parseLong(size));
    }

    public List<FolderDay7> getSubFolders() {
        return new ArrayList<>(folderDay7s.values());
    }
}
