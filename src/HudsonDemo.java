import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
 
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
 
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

 
public class HudsonDemo extends AbstractHandler
{
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) 
        throws IOException, ServletException
    {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        response.getWriter().println("<h1>Hello, World!</h1>");
    }
 
    public static void main(String[] args) throws Exception
    {
        // check if server is already running
        boolean serverLive = true;
        HttpURLConnection client = (HttpURLConnection) (new URL("http://127.0.0.1:8081/").openConnection());
        try {
            client.connect();
        } catch (IOException e) {
            serverLive = false;
        }
        if(serverLive){
            System.err.println("Server already running...exiting");
            System.exit(1);
        }

        Server server = new Server(8081);
        server.setHandler(new HudsonDemo());
 
        server.start();
        server.join();
    }
}
