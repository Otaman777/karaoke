package servlets;

import HelperClasses.DiskHelper;
import HelperClasses.GroupHelper;
import HelperClasses.HibernateUtil;
import HelperClasses.SongHelper;
import entity.Disk;
import entity.IModel;
import entity.Song;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(urlPatterns = "/ListSongsGrServlet")
public class ListSongsGrServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long idGr = Long.parseLong(request.getParameter("id"));
        List diskList = new DiskHelper().getDiskList();
        List<Long> idsDisk = new ArrayList<Long>();
        System.out.println(idGr);
        for (Object s : diskList) {
            if(idGr == ((Disk) s).getGroup().getId())
                idsDisk.add(((IModel)s).getId());
        }

        List<Song> songList = new SongHelper().getSongList();
        String[] header = songList.get(0).getTableHeaders();
        List<Song> requiredSongList = new ArrayList<Song>();
        for(Song s:songList){
            for(Long r:idsDisk){
                if(s.getDisk().getId() == r){
                    requiredSongList.add(s);
                }
            }
        }

        Object[][] array = new Object[requiredSongList.size()][header.length];
        int i = 0;
        for (Object s : requiredSongList) {
            array[i++] = ((IModel) s).getTableRowData();
        }
        TableModel model = new DefaultTableModel(array, header);

        HttpSession sessionForJSP = request.getSession();
        sessionForJSP.setAttribute("tableName", "Songs");
        sessionForJSP.setAttribute("tableModel", model);

        request.getRequestDispatcher("ListSongs.jsp").forward(request, response);
    }
}
