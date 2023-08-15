package io.github.cruciblemc.vitatempus.core;


import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;

import java.io.OutputStream;

public abstract class MessagePacket {

    public abstract byte packetID();
    public abstract ReadWriteNBT writeCompound();

    public String getPacketType(){
        return "set";
    }

    public final void writePacket(OutputStream outputStream) {
        writeCompound().writeCompound(outputStream);
    }

}
