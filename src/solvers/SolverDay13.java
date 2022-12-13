package solvers;

import model.PacketDay13;
import utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolverDay13 implements Solver {
    @Override
    public String getIntroString() {
        return "Day 13 :";
    }

    @Override
    public String solvePuzzleA() {
        int score = 0;
        String line1;
        String line2;
        PacketDay13 pack1;
        PacketDay13 pack2;
        int round = 1;
        try (BufferedReader lines = Utils.getBufferInput("13")) {
            for (round = 1; round <= 150; round++) {
                line1 = lines.readLine();
                pack1 = new PacketDay13();
                pack1.parsePacket(line1.substring(1, line1.length()-1));
                line2 = lines.readLine();
                pack2 = new PacketDay13();
                pack2.parsePacket(line2.substring(1, line2.length()-1));
                // empty
                lines.readLine();

                if (pack1.compareTo(pack2) < 0) {
                    score += round;
                }


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "" + score;
    }

    @Override
    public String solvePuzzleB() {
        int score = 0;
        List<PacketDay13> allPackets = new ArrayList<>();
        PacketDay13 pack;
        try (BufferedReader lines = Utils.getBufferInput("13")) {
            for (String line; (line = lines.readLine()) != null; ) {
                if(line.length() == 0) continue;
                pack = new PacketDay13();
                pack.parsePacket(line);
                allPackets.add(pack);
            }
            pack = new PacketDay13();
            pack.parsePacket("[[2]]");
            pack.isImportant = true;
            allPackets.add(pack);
            pack = new PacketDay13();
            pack.parsePacket("[[6]]");
            pack.isImportant = true;
            allPackets.add(pack);

            Collections.sort(allPackets);

            score = 1;
            for (int i = 0; i < allPackets.size(); i++) {
                if (allPackets.get(i).isImportant) {
                    score *= (i+1);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "" + score;
    }
}
