import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClient {

    /**
     * Allows one time socket call to a server, gets reply, and then closes connection.
     */
    public String connectForOneMessage(String sIP, int iPort, String sMessage) {

        try (Socket oSocket = new Socket()) {
        oSocket.connect(new InetSocketAddress(sIP, iPort), 5000);

            //setup writer
            OutputStream output = oSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            //send message to server.
            writer.println(sMessage);
            writer.flush();

            //setup a reader
            InputStream input = oSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String sReply = reader.readLine();

            return sReply;

        }
        catch (IOException ex) {
            System.out.println("[client] Client exception: " + ex.getMessage());
            ex.printStackTrace();

            return "client error";

        }
    }
}
