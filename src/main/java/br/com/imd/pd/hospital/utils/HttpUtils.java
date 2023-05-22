package br.com.imd.pd.hospital.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpUtils {
    public static String post(String url, Map<String, String> headers, String body) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Define o método HTTP para POST
        con.setRequestMethod("POST");

        // Adiciona os headers da requisição
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                con.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }

        // Habilita o envio do corpo da requisição
        con.setDoOutput(true);

        // Define o corpo da requisição
        con.getOutputStream().write(body.getBytes("UTF-8"));

        // Realiza a requisição HTTP e recebe a resposta
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}
