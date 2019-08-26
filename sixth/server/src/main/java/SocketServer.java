import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.*;

public class SocketServer {

    private static final int port = 8080;
    private ByteBuffer bb = ByteBuffer.allocate(1000);
    private int num = 0;
    int counter = 0;

    public static void main(String[] args) throws IOException{
        SocketServer server = new SocketServer();
        server.startServer();
    }

    private void startServer() throws IOException{

        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ServerSocket ss = ssc.socket();
        ss.bind(new InetSocketAddress(port));
        ssc.configureBlocking(false);
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> i = selectedKeys.iterator();

            while (i.hasNext()) {

                SelectionKey sk = i.next();
                if (sk.isAcceptable()) {
                    System.out.println("accept client connection");
                    acceptConnection(sk, selector);
                } else if (sk.isReadable()) {
                    System.out.println("read from client");
                    readClient(sk);
                }
            }
            i.remove();
        }
    }

    private void acceptConnection(SelectionKey sk, Selector s) throws IOException{
        ServerSocketChannel server = (ServerSocketChannel) sk.channel();
        SocketChannel sChannel = server.accept();

        sChannel.configureBlocking(false);
        sChannel.register(s, SelectionKey.OP_READ);
        num = randomNumberGenerator();
        System.out.println(num);
    }

    private void readClient(SelectionKey sk) throws IOException {
        counter++;
        SocketChannel schannel = (SocketChannel) sk.channel();
        bb.flip();
        bb.clear();

        int count = schannel.read(bb);
        if (count > 0) {
            bb.flip();
            String input = Charset.forName("UTF-8").decode(bb).toString();
            System.out.println(input);
            String msg = isCorrect(Integer.parseInt(input));
            bb.flip();
            bb.clear();
            bb.put(msg.getBytes());
            bb.flip();
            schannel.write(bb);
            bb.clear();
        }
        if(counter == 3) {
            schannel.close();
        }
    }

    private int randomNumberGenerator() {
        Random random = new Random();
        return random.nextInt(9);
    }

    private String isCorrect(int guess) {
        if(num == guess) {
            num = randomNumberGenerator();
            return "Congratulations!";
        } else if(num > guess) {
            return "Try bigger than " + guess;
        }
        return "Try less than " + guess;
    }
}
