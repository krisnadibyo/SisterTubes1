/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package protocol;

/**
 *
 * @author DELL
 */
public class MessageBuilder {
    public static final String PSTR = "GunbondGame";
    protected byte[] _msgBytes;

    public MessageBuilder(int size, boolean isEmptyMessage) {
        _msgBytes = new byte[size];

        if (!isEmptyMessage) {
            // 0 -> 10 PSTR
            writeStrToMsgBytes(0, 11, PSTR);
            // 11 -> 18 RESERVED
            fillByteToMsgBytes(11, 8, (byte) 0);
        }
    }

    public MessageBuilder(int size) {
        this(size, false);
    }

    public MessageBuilder(byte[] msgBytes) {
        setMessageBytes(msgBytes);
    }

    public byte[] getMessageBytes() {
        return _msgBytes;
    }

    public void setMessageBytes(byte[] msgBytes) {
        _msgBytes = new byte[msgBytes.length];
        for (int i = 0; i < msgBytes.length; i++) {
            _msgBytes[i] = msgBytes[i];
        }
    }

    public int msgSize() {
        return _msgBytes.length;
    }

    // Message setters
    public void setCode(byte code) {
        writeByteToMsgBytes(19, code);
    }

    public void setPeerId(int peerId) {
        writeIntToMsgBytes(20, peerId);
    }

    //buat message create room
    

    // Message getters
    public String getPstr() {
        return getStringFromMsgBytes(0, 11);
    }

    public byte[] getReserved() {
        return getChunkedByteFromMsgBytes(11, 8);
    }

    public byte getCode() {
        return getOneByteFromMsgBytes(19);
    }

    public int getPeerId() {
        return getIntFromMsgBytes(20);
    }

    // MB Writers
    public void writeStrToMsgBytes(int offset, int size, String content) {
        for (int i = 0; i < size; i++) {
            if (i < content.length()) {
                _msgBytes[i + offset] = (byte) content.charAt(i);
            } else {
                _msgBytes[i + offset] = (byte) 0;
            }
        }
    }

    public void writeBytesToMsgBytes(int offset, int size, byte[] bytes) {
        for (int i = 0; i < size; i++) {
            _msgBytes[i + offset] = bytes[i];
        }
    }

    public void writeByteToMsgBytes(int index, byte byt)
    {
        _msgBytes[index] = byt;
    }

    public void fillByteToMsgBytes(int offset, int size, byte byt) {
        for (int i = 0; i < size; i++) {
            _msgBytes[i + offset] = byt;
        }
    }

    public void writeIntToMsgBytes(int index, int val) {
        // big endian
        for (int i = 0; i < 4; i++) {
            _msgBytes[i + index] = (byte) ((byte) (val >> (8 * i)) & (byte) 0xff);
        }
    }

    // MB Getters
    public byte getOneByteFromMsgBytes(int index) {
        return _msgBytes[index];
    }

    public byte[] getChunkedByteFromMsgBytes(int offset, int size) {
        byte[] chunk = new byte[size];
        for (int i = 0; i < size; i++) {
            chunk[i] = _msgBytes[i + offset];
        }

        return chunk;
    }

    public String getStringFromMsgBytes(int offset, int size) {
        StringBuilder sb = new StringBuilder(size + 1);
        for (int i = 0; i < size; i++) {
            sb.append((char) (_msgBytes[i + offset] & 0xff));
        }
        return sb.toString();
    }

    public int getIntFromMsgBytes(int index) {
        int val = 0;
        // big endian
        for (int i = 0; i < 4; i++) {
            val += (_msgBytes[i + index] & 0xff) << (8 * i);
        }

        return val;
    }
}

