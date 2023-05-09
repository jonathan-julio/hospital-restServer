package br.com.imd.pd.hospital.controllers;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.imd.pd.hospital.models.Hospital;

@Controller
@RequestMapping("/")
public class HomeController {

    private final RestTemplate restTemplate = new RestTemplate();
    @GetMapping
    public String home(){
        return "home";
    }

    @GetMapping
    @RequestMapping("/resultado")
    public String resultado(@RequestParam("latitude") String latitude, 
                         @RequestParam("longitude") String longitude, 
                         Model model) throws JsonMappingException, JsonProcessingException, RemoteException {

         RestTemplate restTemplate = new RestTemplate();
         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_JSON);
     
         JSONObject locationJson = new JSONObject();
         locationJson.put("latitude", latitude);
         locationJson.put("longitude", longitude);
     
         JSONObject requestJson = new JSONObject();
         requestJson.put("name", "Hospital Bahia");
         requestJson.put("vacancies", 1);
         requestJson.put("location", locationJson);
         requestJson.put("avaliable", true);
     
         HttpEntity<String> request = new HttpEntity<>(requestJson.toString(), headers);
     
         String url = "http://localhost:8080/server/location";
         ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
         ObjectMapper mapper = new ObjectMapper();
            mapper.registerSubtypes(HospitalImpl.class);
            Hospital hospital = mapper.readValue(response.getBody(), HospitalImpl.class);

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


