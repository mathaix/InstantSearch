package com.itasoftware.instantsearch;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
@EnableAutoConfiguration
public class SearchController {
	
	@Autowired
	PropertyDatabase db;
	
	@RequestMapping(path = "/search", produces = "application/json; charset=UTF-8")
    @ResponseBody
    List<NrhpProperty> home(@RequestParam(value="query", required=true) String query) {
		return db.search(query.toLowerCase());
    }
}
