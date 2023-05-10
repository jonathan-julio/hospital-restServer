package br.com.imd.pd.hospital.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.imd.pd.hospital.models.Hospital;
import br.com.imd.pd.hospital.models.Location;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping
    @RequestMapping("/resultado")
    public String resultado(@RequestParam("latitude") String latitude, 
                         @RequestParam("longitude") String longitude, 
                         Model model) throws IOException {

        String url = "http://localhost:8080/server/location";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        String jsonBody = "{ \"latitude\": "+latitude+ ", \"longitude\": "+longitude+ " }";

        String response = HttpUtils.post(url, headers, jsonBody);

        JsonElement jsonElement = JsonParser.parseString(response);
        JsonObject object = jsonElement.getAsJsonObject();

        String name = object.get("name").getAsString();
        int vacancies = object.get("vacancies").getAsInt();
        JsonElement locationElement = object.get("location");

        double responseLat = locationElement.getAsJsonObject().get("latitude").getAsDouble();
        double responseLon = locationElement.getAsJsonObject().get("longitude").getAsDouble();
        Location location = new Location(responseLat, responseLon);

        Hospital hospital = new HospitalImpl(name, vacancies, location);

        model.addAttribute("name", hospital.getName() );
        model.addAttribute("vacancies", hospital.getVacancies() );
        model.addAttribute("latitude", hospital.getLocation().getLatitude() );
        model.addAttribute("longitude", hospital.getLocation().getLongitude());

        return "resultado";
    }
    
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }
}
