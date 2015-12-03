package xyz.devyang.sw.web;

import xyz.devyang.sw.core.Node;
import xyz.devyang.sw.core.SearchEngine;
import xyz.devyang.sw.storage.Serialization;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by YangYu on 12/2/15.
 */
@WebServlet(name = "BFSServlet")
public class BFSServlet extends HttpServlet {

    private SearchEngine searchEngine = new SearchEngine();

    private String times;
    private String start;

    @Override
    public void init() throws ServletException {
        super.init();
//        RuntimeCache.GRAPH = (Graph) Serialization.readObject("63392-2015-11-30-17-34-43.dat");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Node n = new Node(1);
        Serialization.writeObject(1, "xxx");
        times = request.getParameter("bfsNoOfTimes");
        start = request.getParameter("startVertex");
        PrintWriter writer = response.getWriter();

        if (times==null||start==null||!times.matches("^\\d+$")||!start.matches("^\\d+$")) {
            request.getRequestDispatcher("second.jsp?path=wrong_input").forward(request, response);
        } else {
//            int t = Integer.parseInt(times);
//            int s = Integer.parseInt(start);
//            Node node = RuntimeCache.GRAPH.getNodes().get(s);
//            Map<String, Object> map = null;
//            for (int i=0;i<t;i++) {
//    //            map.put("start", node);
//    //            map.put("end", last);
//    //            map.put("path", path);
//                map = searchEngine.oneTimeBFS(node);
//                node = (Node) map.get("end");
//            }
//            List path = (List) map.get("path");
//            System.out.println(Arrays.toString(path.toArray()));
            request.setAttribute("path",15);
            request.getRequestDispatcher("second.jsp").forward(request, response);
//            writer.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
