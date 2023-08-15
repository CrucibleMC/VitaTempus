package io.github.cruciblemc.vitatempus.packets;

import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import io.github.cruciblemc.vitatempus.core.MessagePacket;

public class ActionBar extends MessagePacket {

    private int time = 70;
    private final String message;

    private ActionBar(int time, String message){
        this(message);
        this.time = time;
    }

    private ActionBar(String message){
        this.message = message;
    }

    public static ActionBar of(String message){
        return new ActionBar(message);
    }

    public static ActionBar of(int time, String message){
        return new ActionBar(time, message);
    }

    public static ActionBar remove(){
        return new RemoveActionBar();
    }

    @Override
    public byte packetID() {
        return 3;
    }

    @Override
    public ReadWriteNBT writeCompound() {

        ReadWriteNBT nbtCompound = NBT.createNBTObject();

        nbtCompound.setString("packetType", getPacketType());

        nbtCompound.setInteger("time", time);
        nbtCompound.setString("text", message);

        return nbtCompound;
    }

    public static class RemoveActionBar extends ActionBar{

        private RemoveActionBar() {
            super("VITATEMPUS");
        }

        @Override
        public String getPacketType() {
            return "remove";
        }

    }

}
