import java.io.FileOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
 
 
public class Receiver {
	public static void main(String[] args) {
		String outputFile = "4CM3_Resiliencia2B_Equipo2.pdf", host = "127.0.0.1";		
		int port = 8001, bufferSize = 10240;
		try {
			ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
			Selector selector = Selector.open();
			ServerSocketChannel server = ServerSocketChannel.open();
			server.configureBlocking(false);
			server.socket().bind(new InetSocketAddress(host, port));
			server.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("Servidor iniciado..."); 
			while(true) {
				selector.select();
				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
				while ( iterator.hasNext() ) {
					SelectionKey key = (SelectionKey) iterator.next();
					iterator.remove();			
					if (key.isAcceptable()) {						
						SocketChannel client = server.accept();
						client.configureBlocking(false);
						client.register(selector, SelectionKey.OP_READ);						
						continue;
					}					
					SocketChannel channel = (SocketChannel) key.channel();
					int counter = 1; 
					if ( key.isReadable() ) {
						FileOutputStream os = new FileOutputStream(outputFile);
                        FileChannel destination = os.getChannel();
						int res;                                                 
						while( ( res = channel.read(buffer) ) != -1){
							counter += res; 
							System.out.println("Leyendo bloque de "+res+" Bytes");
							buffer.flip();
                            while( buffer.hasRemaining() ){
                                destination.write(buffer);
                            }
                            buffer.clear();
                        }
						channel.close();
                        destination.close();
						os.close();		
						System.out.println("Recibidos: " + counter+ " Bytes");
					}
				}			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}
}
