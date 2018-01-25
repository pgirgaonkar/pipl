package com.my.pipl;

import com.pipl.api.search.SearchAPIError;
import com.pipl.api.search.SearchAPIRequest;
import com.pipl.api.search.SearchAPIResponse;
import com.pipl.api.search.SearchConfiguration;

import java.io.IOException;

public class PiplClient {
    private String emailId;
    private String firstName;
    private String lastName;
    private String key="56oe24984spmzfpnmib4ztuw";

    public PiplClient(String emailId, String firstName, String lastName, String key) {
        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.key = (key!= null && key.length() > 0)?key:this.key;
    }

    public void searchInformation() {

        SearchConfiguration config = new SearchConfiguration();
        config.apiKey= key;


        SearchAPIRequest request = new SearchAPIRequest.Builder().email(emailId).firstName(firstName).lastName(lastName).configuration(config).build();

        try {
            SearchAPIResponse  response = request.send();
            System.out.println(response.json);
        } catch (SearchAPIError searchAPIError) {
            searchAPIError.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args){
        PiplClient p = new PiplClient("alex.radka@gmail.com", "Alex", "Radka","");
        
        p.searchInformation();
    }
}
