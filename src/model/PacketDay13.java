package model;

import java.util.ArrayList;
import java.util.List;

public class PacketDay13 implements Comparable<PacketDay13> {
    List<PacketDay13> subPackets = new ArrayList<>();
    Integer value = null;
    public boolean isImportant = false;

    public void parsePacket (String packet) {
        if (packet.length() == 0) {
            //empty
            return;
        } else if (constrFirstSubpacket(packet).length() == packet.length() && packet.charAt(0) != '[') {
            // Simple int
            this.value = Integer.parseInt(packet);
            return;
        }
        String packetCopy = packet;
        while (packetCopy.length() > 0) {
            String firstSubpacket = constrFirstSubpacket(packetCopy);
            PacketDay13 subpac = new PacketDay13();
            this.subPackets.add(subpac);
            if (firstSubpacket.charAt(0) == '[') {
                subpac.parsePacket(firstSubpacket.substring(1, firstSubpacket.length()-1));
            } else {
                subpac.parsePacket(firstSubpacket);
            }
            if (packetCopy.length() != firstSubpacket.length()) {
                packetCopy = packetCopy.substring(firstSubpacket.length() + 1);
            } else {
                return;
            }

        }
    }

    private static String constrFirstSubpacket(String packet) {
        String firstSubpacket;
        if (packet.charAt(0) == '[') {
            // Subpacket complexe
            int i = 0;
            int score = 1;
            while (score > 0) {
                i++;
                if (packet.charAt(i) == '[') {
                    score++;
                } else if (packet.charAt(i) == ']') {
                    score--;
                }
            }
            firstSubpacket = packet.substring(0, i+1);
        } else {
            // Nombre
            firstSubpacket = packet.split(",")[0];
        }
        return firstSubpacket;
    }

    @Override
    public int compareTo(PacketDay13 other) {
        // Two ints
        int result = 0;
        if (this.value != null && other.value != null) {
            if (this.value.equals(other.value)) {
                return 0;
            } else {
                return this.value - other.value;
            }
        }

        // First is an int but not second
        if (this.value != null) {
            if (other.subPackets.isEmpty()) {
                return 1;
            }
            result = this.compareTo(other.subPackets.get(0));
            if (result != 0) {
                return result;
            } else {
                if (other.subPackets.size() > 1) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }

        // Second is an int but not first
        if (other.value != null) {
            if (this.subPackets.isEmpty()) {
                return -1;
            }
            result = this.subPackets.get(0).compareTo(other);
            if (result != 0) {
                return result;
            } else {
                if (this.subPackets.size() > 1) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }

        // Both are lists
        for (int i = 0; i < Math.max(this.subPackets.size(), other.subPackets.size()); i++) {
            // no more subpacket in either
            if (i >= this.subPackets.size()) {
                return -1;
            } else if (i >= other.subPackets.size()) {
                return 1;
            }

            result = this.subPackets.get(i).compareTo(other.subPackets.get(i));
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }

}
