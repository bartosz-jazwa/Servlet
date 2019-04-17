package com.jazwa;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.Date;
@WebServlet("/birthday")
public class BirthdaySecondsServlet extends HttpServlet {

    LocalDate birthday =LocalDate.of(1982,8,27);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LocalTime bTime = LocalTime.NOON;
        LocalDateTime bDay = LocalDateTime.of(birthday,bTime);
        LocalDateTime now = LocalDateTime.now();
        long yearsNow = now.getYear();
        long yearsBday = bDay.getYear();
        long years = yearsNow - yearsBday;

        long daysNow = now.getDayOfYear();
        long daysBday = bDay.getDayOfYear();
        long days = daysNow-daysBday;


        Date bDate = new Date(82,8,27);
        Date nDate = new Date();

        long diff = (nDate.getTime()-bDate.getTime())/1000;
        resp.getWriter().println(diff);
    }
}
