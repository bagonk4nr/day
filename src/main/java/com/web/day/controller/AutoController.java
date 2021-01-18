package com.web.day.controller;

import com.web.day.services.FindURL;
import com.web.day.services.LoadWeb;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
public class AutoController extends AbstractController {

    private Map<String, Object> model;
    private Boolean vals;

    @Override
    @RequestMapping(value = {"/","/*"}, method = RequestMethod.GET)
    public ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {
        return getURLValue(request);
    }

    public AutoController(Map<String, Object> model) {
        this.model = model;
        this.vals = false;
    }

    private ModelAndView getURLValue(HttpServletRequest req) throws IOException, JSONException {
        final String urlVal = req.getServletPath();
        System.out.println(urlVal);
        if (!urlVal.equals("/")) {
            String urlVals = urlVal
                    .replace("pages/", "")
                    .replace("/", "")
                    .replace("/index", "");
            System.out.println(urlVals + " urlakhir");
            FindURL findURL = new FindURL();
            this.vals = Boolean.parseBoolean(findURL.findUrls(urlVals));
            System.out.println(this.vals);
            if(!this.vals) {
                System.out.println(this.vals + " equal");
                return displayWeb("error/404");
            } else {
                System.out.println(this.vals + " not equal");
                return displayWeb("pages/" + urlVals + "/index");
            }
        } else {
           return displayIndex();
        }
    }

    private ModelAndView displayIndex() throws IOException, JSONException {
        LoadWeb menu = new LoadWeb();
        this.model = menu.loadMenu();
        String TITLE = "Web Uhuyy";
        this.model.put("title", TITLE);
        return new ModelAndView("index", this.model);
    }

    private ModelAndView displayWeb(String addr) throws IOException {
//        LoadWeb menu = new LoadWeb();
//        menu.loadMenu();
        String TITLE = "Web Uhuyy";
        this.model.put("title", TITLE);
        return new ModelAndView(addr, this.model);
    }

}
