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
                "        <p id=\"demo\" style=\"font-size:30px\"></p>\n" +
                "    </div>\n" +
                "    <div class=\"bottomleft\">\n" +
                "        <p>Master Learn Service</p>\n" +
                "    </div>\n" +
                "</div>\n" +
                "<script>\n" +
                "// Set the date we're counting down to\n" +
                "var countDownDate = new Date(\"Jan 1, 2023 15:37:25\").getTime();\n" +
                "\n" +
                "// Update the count down every 1 second\n" +
                "var countdownfunction = setInterval(function() {\n" +
                "\n" +
                "  // Get todays date and time\n" +
                "  var now = new Date().getTime();\n" +
                "  \n" +
                "  // Find the distance between now an the count down date\n" +
                "  var distance = countDownDate - now;\n" +
                "  \n" +
                "  // Time calculations for days, hours, minutes and seconds\n" +
                "  var days = Math.floor(distance / (1000 * 60 * 60 * 24));\n" +
                "  var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));\n" +
                "  var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));\n" +
                "  var seconds = Math.floor((distance % (1000 * 60)) / 1000);\n" +
                "  \n" +
                "  // Output the result in an element with id=\"demo\"\n" +
                "  document.getElementById(\"demo\").innerHTML = days + \"d \" + hours + \"h \"\n" +
                "  + minutes + \"m \" + seconds + \"s \";\n" +
                "  \n" +
                "  // If the count down is over, write some text \n" +
                "  if (distance < 0) {\n" +
                "    clearInterval(countdownfunction);\n" +
                "    document.getElementById(\"demo\").innerHTML = \"EXPIRED\";\n" +
                "  }\n" +
                "}, 1000);\n" +
                "</script>" +
                "\n" +
                "</body>\n" +
                "</html>";
    }
}
