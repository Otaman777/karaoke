package servlets;

import HelperClasses.DiskHelper;
import HelperClasses.SongHelper;
import entity.Disk;
import entity.IModel;
import entity.Song;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/ListSongsDskServlet")
public class ListSongsDskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long idDisk = Long.parseLong(request.getParameter("id"));

        List<Song> songList = new SongHelper().getSongList();
        String[] header = songList.get(0).getTableHeaders();
        List<Song> requiredSongList = new ArrayList<Song>();
        for(Song s:songList){
            if(s.getDisk().getId() == idDisk){
                requiredSongList.add(s);
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
