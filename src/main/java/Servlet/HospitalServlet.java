package Servlet;

import Controller.detailController;
import Model.Hospital;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet("/details")
public class HospitalServlet extends HttpServlet {

    detailController detailController = new detailController();

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Parse JSON body
            BufferedReader reader = req.getReader();
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String json = sb.toString();

            // Use Gson to parse JSON to Hospital object
            Gson gson = new Gson();
            Hospital hospital = gson.fromJson(json, Hospital.class);

            // Validate fields
            if (hospital.getName() == null || hospital.getName().isEmpty() ||
                    hospital.getAddress() == null || hospital.getAddress().isEmpty() ||
                    hospital.getDate() == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input parameters.");
                return;
            }

            // Update details
            detailController.updateDetails(hospital);

            resp.getWriter().println("Hospital details updated successfully.");
        } catch (DateTimeParseException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Date format is invalid. Use yyyy-MM-dd.");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the request.");
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Hospital movie = detailController.getDetailById(id);
        resp.getWriter().println(movie);
//        resp.getWriter().println("<body background-color:'white'></body>");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
       int id= Integer.parseInt(req.getParameter("id"));
       detailController.deleteDetails(id);
       resp.getWriter().println("Deleted Record .");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("_method");
        if ("DELETE".equalsIgnoreCase(method)) {
//            doDelete(req, resp);
            doDelete(req,resp);
        }
    }





}
