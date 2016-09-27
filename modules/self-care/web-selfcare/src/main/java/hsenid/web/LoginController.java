package hsenid.web;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;


@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(HttpServletRequest request) throws JSONException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String string = "{" + "username" + ":" + username + "," + "password" + ":" + password + "}";
        JSONObject jsonObject = new JSONObject(string);
//        System.out.println(jsonObject);

        try {
            URL url = new URL("http://localhost:2222/pizza-shefu/api/v1.0/customers/login/");
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jsonObject.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String ttt = null;
            StringBuilder sb = new StringBuilder();

            while ((ttt = in.readLine()) != null) {
                sb.append(ttt);
            }

            in.close();

            JSONObject reply = new JSONObject(sb.toString());
            int status = reply.getInt("code");

            if (status == 200) {
                return "self-care-home";
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "menu";
    }
}
