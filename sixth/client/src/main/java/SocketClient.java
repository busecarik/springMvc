import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;

public class SocketClient {
    int counter = 0;
    private static final int port = 8080;
    private static final String hostName = "localhost";
    private ByteBuffer bb = ByteBuffer.allocate(1000);

    public static void main(String[] args) throws IOException {
        SocketClient client = new SocketClient();
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);

        InetSocketAddress addr = new InetSocketAddress(hostName, port);
        sc.connect(addr);

        while (!sc.finishConnect()) {
            System.out.println("conneting to server");
        }
        client.send(sc);
        client.send(sc);
        client.send(sc);
        sc.close();
    }

    private void send(SocketChannel sc) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int num = 0;
        if (scanner.hasNext()) {
            num = scanner.nextInt();
        }
        bb.flip();
        bb.clear();
        bb.put(String.valueOf(num).getBytes());
        bb.flip();
        sc.write(bb);

        Selector selector = Selector.open();
        sc.register(selector, SelectionKey.OP_READ);
        while (true) {
            if (selector.select() > 0) {
                if (receive(selector)) {
                    return;
                }
            }
        }

    }

    private boolean receive(Selector s) throws IOException {
        counter++;
        Iterator<SelectionKey> i = s.selectedKeys().iterator();
        while (i.hasNext()) {

            SelectionKey sk = i.next();
            if (sk.isReadable()) {
                SocketChannel schannel = (SocketChannel) sk.channel();
                bb.flip();
                bb.clear();

                int count = schannel.read(bb);
                if (count > 0) {
                    bb.rewind();
                    String response = Charset.forName("UTF-8").decode(bb).toString();
                    System.out.println("response: " + response);
                    if(counter == 3) {
                        schannel.close();
                    }
                    return true;
                }
            }
            i.remove();
        }
        return false;
    }
}
