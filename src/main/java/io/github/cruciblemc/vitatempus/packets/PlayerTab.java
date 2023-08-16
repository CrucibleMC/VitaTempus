package io.github.cruciblemc.vitatempus.packets;

import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import io.github.cruciblemc.vitatempus.core.MessagePacket;

public class PlayerTab extends MessagePacket {

    private final String header;
    private final String footer;

    private PlayerTab(String header, String footer){
        this.header = header;
        this.footer = footer;
    }

    public static PlayerTab of(String header, String footer){
        return new PlayerTab(header, footer);
    }

    @Override
    public byte packetID() {
        return 2;
    }

    @Override
    public ReadWriteNBT writeCompound() {

        ReadWriteNBT nbtCompound = NBT.createNBTObject();

        nbtCompound.setString("packetType", "set");

        if(header != null)
            nbtCompound.setString("header", header);

        if(footer != null)
            nbtCompound.setString("footer", footer);

        nbtCompound.setBoolean("drawPlayerHeads", true);

        return nbtCompound;
    }
}
