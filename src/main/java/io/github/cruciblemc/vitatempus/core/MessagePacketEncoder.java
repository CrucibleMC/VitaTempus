package io.github.cruciblemc.vitatempus.core;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class MessagePacketEncoder {

    public static EncodedMessage encode(MessagePacket messagePacket){

        byte[] data = transform(messagePacket);

        ByteBuffer byteBuffer = ByteBuffer.allocate(3 + data.length);

        byteBuffer.put(messagePacket.packetID());
        byteBuffer.putShort((short) data.length);
        byteBuffer.put(data);

        return new EncodedMessage(byteBuffer.array());
    }

    private static byte[] transform(MessagePacket messagePacket){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        messagePacket.writePacket(byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }

}
