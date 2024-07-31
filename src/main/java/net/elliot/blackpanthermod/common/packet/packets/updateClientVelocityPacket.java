package net.elliot.blackpanthermod.common.packet.packets;

public class updateClientVelocityPacket {

    private float x;
    private float y;
    private float z;

    
    public updateClientVelocityPacket x(Float x) {
        this.x = x;

        return this;
    }

    public updateClientVelocityPacket y(Float y) {
        this.y = y;

        return this;
    }

    public updateClientVelocityPacket z(Float z) {
        this.z = z;

        return this;
    }

}
