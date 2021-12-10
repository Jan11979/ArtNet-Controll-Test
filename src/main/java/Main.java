import ch.bildspur.artnet.ArtNetClient;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hallo Schöne Welt");

        byte[] dmxData = new byte[512];
        ArtNetClient artnet = new ArtNetClient();
        artnet.start();

        // set data
        dmxData[0] = (byte) 0;
        dmxData[1] = (byte) 0;
        dmxData[2] = (byte) 0;
        dmxData[3] = (byte) 0;

//        // send data to localhost
//        artnet.unicastDmx("127.0.0.1", 0, 0, dmxData);

        // to broad cast data
        int color = 2;
        int value = 10;
        while (true) {
            value +=  1;
            if (value >= 255) {
                value = 0;
                dmxData[color] = (byte) value;
                color += 1;
                if (color >= 4)
                    color = 1;
            }
            dmxData[color] = (byte) value;
            artnet.broadcastDmx(0, 1, dmxData);
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                System.out.println("Error");
            }
//            System.out.println("color:" + color + " value:" + value );
        }

        //artnet.stop();

    }
}
