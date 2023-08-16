package io.github.cruciblemc.vitatempus.packets;

import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import io.github.cruciblemc.vitatempus.core.MessagePacket;

public class Title extends MessagePacket {

    private int fadeIn = 500;
    private int stay = 3500;
    private int fadeOut = 1000;

    private final String[] titles = new String[2];

    private Title(int fadeIn, int stay, int fadeOut, String ... messages){

        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;

        if(messages.length == 1){
            titles[0] = messages[0];
        }else if(messages.length >= 2){
            titles[0] = messages[0];
            titles[1] = messages[1];
        }

    }

    private Title(String ... messages){

        if(messages.length == 1){
            titles[0] = messages[0];
        }else if(messages.length >= 2){
            titles[0] = messages[0];
            titles[1] = messages[1];
        }

    }

    public static Title of(String ... messages){
        return new Title(messages);
    }

    public static Title of(int fadeIn, int stay, int fadeOut, String ... messages){
        return new Title(fadeIn, stay, fadeOut, messages);
    }

    public MessagePacket remove(){
        return new RemoveTitle();
    }

    @Override
    public byte packetID() {
        return 3;
    }

    @Override
    public ReadWriteNBT writeCompound() {

        ReadWriteNBT nbtCompound = NBT.createNBTObject();

        nbtCompound.setString("packetType", getPacketType());

        nbtCompound.setInteger("fadeIn", fadeIn);
        nbtCompound.setInteger("stay", stay);
        nbtCompound.setInteger("fadeOut", fadeOut);

        if(titles[0] != null){
            ReadWriteNBT title = nbtCompound.getOrCreateCompound("title");
            title.setString("type", "TITLE");
            title.setString("text", titles[0]);
        }

        if(titles[1] != null){
            ReadWriteNBT title = nbtCompound.getOrCreateCompound("subtitle");
            title.setString("type", "SUBTITLE");
            title.setString("text", titles[1]);
        }

        return nbtCompound;
    }

    public static class RemoveTitle extends Title{

        public RemoveTitle(){
            super("VITA","TEMPUS");
        }

        @Override
        public String getPacketType() {
            return "remove";
        }

    }

}
