package solvers;

import model.FolderDay7;
import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;

public class SolverDay07 implements Solver {
    @Override
    public String getIntroString() {
        return "Day 7 :";
    }

    @Override
    public String solvePuzzleA() {
        String result = "";
        FolderDay7 baseFolder = new FolderDay7();
        FolderDay7 currentFolder = baseFolder;

        boolean isLs = false;

        try (BufferedReader lines = Utils.getBufferInput("7")) {
            for (String line; (line = lines.readLine()) != null; ) {
                if (line.startsWith("$")) isLs = false;

                if (isLs) {
                    // LS mode
                    if (line.startsWith("dir")) {
                        currentFolder.addSubFolder(line.split(" ")[1]);
                    } else {
                        currentFolder.addFile(line.split(" ")[0], line.split(" ")[1]);
                    }
                } else {
                    // Command mode
                    if (line.startsWith("$ cd")) {
                        if (line.equals("$ cd /")) {
                            currentFolder = baseFolder;
                        } else if (line.equals("$ cd ..")) {
                            currentFolder = currentFolder.getParent();
                        } else {
                            currentFolder = currentFolder.getSubFolder(line.split(" ")[2]);
                        }
                    } else if (line.startsWith("$ ls")) {
                        isLs = true;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return "" + recursiveSumSmallFolders(baseFolder);
    }

    private long recursiveSumSmallFolders(FolderDay7 folder) {
        long total = 0;
        if (folder.calcSize() < 100000) {
            total += folder.calcSize();
        }
        for (FolderDay7 subFolder : folder.getSubFolders()) {
            total += recursiveSumSmallFolders(subFolder);
        }

        return total;
    }

    @Override
    public String solvePuzzleB() {

        String result = "";
        FolderDay7 baseFolder = new FolderDay7();
        FolderDay7 currentFolder = baseFolder;

        boolean isLs = false;

        try (BufferedReader lines = Utils.getBufferInput("7")) {
            for (String line; (line = lines.readLine()) != null; ) {
                if (line.startsWith("$")) isLs = false;

                if (isLs) {
                    // LS mode
                    if (line.startsWith("dir")) {
                        currentFolder.addSubFolder(line.split(" ")[1]);
                    } else {
                        currentFolder.addFile(line.split(" ")[0], line.split(" ")[1]);
                    }
                } else {
                    // Command mode
                    if (line.startsWith("$ cd")) {
                        if (line.equals("$ cd /")) {
                            currentFolder = baseFolder;
                        } else if (line.equals("$ cd ..")) {
                            currentFolder = currentFolder.getParent();
                        } else {
                            currentFolder = currentFolder.getSubFolder(line.split(" ")[2]);
                        }
                    } else if (line.startsWith("$ ls")) {
                        isLs = true;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Calc size
        long maxSize = 70000000;
        long neededSize = 30000000;
        long currentSize = baseFolder.calcSize();



        toLiberate = neededSize - (maxSize - currentSize);
        recursiveFindFolderToDelete(baseFolder, toLiberate);
        return "" + theFolderToDelete.calcSize();
    }

    private static FolderDay7 theFolderToDelete = null;
    private static long toLiberate = 0;
    private void recursiveFindFolderToDelete(FolderDay7 folder, long toLiberate ) {
        if (folder.calcSize() >= toLiberate) {
            if (theFolderToDelete == null || folder.calcSize() <= theFolderToDelete.calcSize()) {
                theFolderToDelete = folder;
            }
        }
        for (FolderDay7 subFolder : folder.getSubFolders()) {
            recursiveFindFolderToDelete(subFolder, toLiberate);
        }
    }
}
