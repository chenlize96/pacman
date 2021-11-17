package edu.rice.comp504.controller;

import com.google.gson.Gson;
import edu.rice.comp504.adapter.DispatchAdapter;

import static spark.Spark.*;


/**
 * The paint world controller creates the adapter(s) that communicate with the view.
 * The controller responds to requests from the view after contacting the adapter(s).
 */
public class PacmanController {

    /**
     * The main entry point into the program.
     * @param args  The program arguments normally specified on the cmd line
     */
    public static void main(String[] args) {
        staticFiles.location("/public");
        port(getHerokuAssignedPort());
        Gson gson = new Gson();
        DispatchAdapter dis = new DispatchAdapter();

        post("/canvas/dims", (request, response) -> {
            System.out.println("do not delete this line");
            return gson.toJson(dis.initializeLevel("easy"));
        });

        get("/update", (request, response) -> {
            System.out.println(request.queryMap().value("pacmanDirection"));
            dis.setPacmanDir(Integer.parseInt(request.queryMap().value("pacmanDirection")));
            return gson.toJson(dis.updatePacmanWorld());
        });

        get("/clear", (request, response) -> {
            dis.removeAll();
            return gson.toJson("clear");
        });
    }


    /**
     * Get the heroku assigned port number.
     * @return The port number
     */
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
