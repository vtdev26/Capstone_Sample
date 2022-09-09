package capstone.project.Demo_Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

    @Autowired
    private ServletContext context;

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String welcome(){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<style>\n" +
                "    body, html {\n" +
                "        height: 100%;\n" +
                "        margin: 0;\n" +
                "    }\n" +
                "\n" +
                "    .bgimg {\n" +
                "        height: 100%;\n" +
                "        background-image: url(\'https://i.imgur.com/UCJ3DNN.jpg\');" +
                "        background-position: center;\n" +
                "        background-size: cover;\n" +
                "        position: relative;\n" +
                "        color: white;\n" +
                "        font-family: \"Courier New\", Courier, monospace;\n" +
                "        font-size: 25px;\n" +
                "    }\n" +
                "\n" +
                "    .topleft {\n" +
                "        position: absolute;\n" +
                "        top: 0;\n" +
                "        left: 16px;\n" +
                "    }\n" +
                "\n" +
                "    .bottomleft {\n" +
                "        position: absolute;\n" +
                "        bottom: 0;\n" +
                "        left: 16px;\n" +
                "    }\n" +
                "\n" +
                "    .middle {\n" +
                "        position: absolute;\n" +
                "        top: 50%;\n" +
                "        left: 50%;\n" +
                "        transform: translate(-50%, -50%);\n" +
                "        text-align: center;\n" +
                "    }\n" +
                "\n" +
                "    hr {\n" +
                "        margin: auto;\n" +
                "        width: 40%;\n" +
                "    }\n" +
                "</style>\n" +
                "<body>\n" +
                "\n" +
                "<div class=\"bgimg\">\n" +
                "    <div class=\"topleft\">\n" +
                "        <p>Training Support System</p>\n" +
                "    </div>\n" +
                "    <div class=\"middle\">\n" +
                "        <h1>COMING SOON</h1>\n" +
                "        <hr>\n" +
                "        <p>14 weeks left</p>\n" +
                "    </div>\n" +
                "    <div class=\"bottomleft\">\n" +
                "        <p>Master Learn Service</p>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }
}
