package com.jazwa;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@WebServlet("/names")
public class TableServlet extends HttpServlet {

    Map<String, String> nameLastname = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final StringBuilder names = new StringBuilder();
        StringBuilder verses = new StringBuilder();

        String s = req.getParameter("sort");
        String sort = "";
        if (s != null) {
            sort = s;
        }

        String b = req.getParameter("by");
        String by = "";
        if (b!=null){
            by=b;
        }
        Comparator<String> r = Comparator.naturalOrder();
        Comparator<String> m = Comparator.reverseOrder();
        Comparator<String> c;
        switch (sort) {
            case "r":
                c=r;
                break;
            case "m":
                c=m;
                break;
            default:
                c=r;
        }
        switch (by){
            case "name":
                nameLastname.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey(c))
                        .forEach(entry -> verses.append(fillVerse(entry.getKey(), entry.getValue())));

                names.append(fillTable(verses.toString()));
                break;
            case "last":
                nameLastname.entrySet().stream()
                        .sorted(Comparator.comparing(Map.Entry::getValue, c))
                        //.sorted(Map.Entry.comparingByValue(c))
                        .forEach(entry -> verses.append(fillVerse(entry.getKey(), entry.getValue())));
                names.append(fillTable(verses.toString()));
                break;

                default:
                    nameLastname.entrySet().stream()
                            .sorted(Comparator.comparing(Map.Entry::getKey, c))
                            .forEach(entry -> verses.append(fillVerse(entry.getKey(), entry.getValue())));
                    names.append(fillTable(verses.toString()));
        }

        resp.getWriter().println(names);

    }

    @Override
    public void init() throws ServletException {
        nameLastname.put("john", "deere");
        nameLastname.put("jane", "deere");
        nameLastname.put("jon", "smith");
        nameLastname.put("bob", "geldof");
    }

    private String fillVerse(String name, String lastName) {

        String verse = "<tr>" +
                "    <td>" +
                name +
                "</td>" +
                "    <td>" +
                lastName +
                "</td>" +
                "  </tr>";

        return verse;
    }

    private String fillTable(String... verses) {
        String tableHead = "<table style=\"width:100%\"><tr>\n" +
                "    <th>Firstname</th>\n" +
                "    <th>Lastname</th> \n" +
                "  </tr>";
        StringBuilder tableBuild = new StringBuilder();
        for (String v : verses) {
            tableBuild.append(v);
        }
        tableHead = tableHead + tableBuild.toString() + "</table>";

        return tableHead;
    }
}
