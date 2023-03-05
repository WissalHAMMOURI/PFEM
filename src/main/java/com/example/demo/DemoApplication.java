package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class DemoApplication  {

    public static void main(String[] args)
    {
        String Url="https://mar2-dev-1cf3502d.cloud.maxxing.com/MPCM/Web/api/rest/5.6/CustomerInformationServer/getCustomerByCardKey";
        String Username= "Wissal_ELHAMMOURI";
        String Password= "Stage_12345";
        String payload = "{ \"storeChain\": \"0\", \"card_key\": \"2000000000005\"}"; // corps de la requête au format JSON





        try{
            String encodedCredentials = Base64.getEncoder().encodeToString((Username + ":" + Password).getBytes());

            URL apiUrl= new URL(Url);
            HttpURLConnection connexion= (HttpURLConnection) apiUrl.openConnection();
            connexion.setRequestProperty("Authorization", "Basic " + encodedCredentials);
            connexion.setRequestMethod("POST");
            connexion.setDoOutput(true);



            byte[] postData = payload.getBytes(StandardCharsets.UTF_8);
            connexion.setRequestProperty("Content-Type", "application/json");
            connexion.setRequestProperty("Content-Length", String.valueOf(postData.length));
            connexion.getOutputStream().write(postData);

            // Envoyez la requête HTTP et lisez la réponse
            BufferedReader in = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            // Fermez la connexion


            // Affichez la réponse de l'API
            System.out.println(response.toString());

        }catch (Exception E){
            E.printStackTrace();

        }


    }

}
