package com.my.pipl;

import com.pipl.api.data.containers.Person;
import com.pipl.api.data.fields.Address;
import com.pipl.api.data.fields.Field;
import com.pipl.api.data.fields.Name;
import com.pipl.api.search.SearchAPIError;
import com.pipl.api.search.SearchAPIRequest;
import com.pipl.api.search.SearchAPIResponse;
import com.pipl.api.search.SearchConfiguration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class PiplClient {
    private String emailId;
    private String firstName;
    private String lastName;
    private String key="enaxzrdgeen3ohotnzse8dvd";//"56oe24984spmzfpnmib4ztuw";
    private String filePath = "./out";

    public PiplClient(String emailId, String firstName, String lastName, String key) {
        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.key = (key!= null && key.length() > 0)?key:this.key;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void searchInformation() {

        SearchConfiguration config = new SearchConfiguration();
        config.apiKey= key;


        SearchAPIRequest request = new SearchAPIRequest.Builder().email(emailId).firstName(firstName)
                .lastName(lastName).configuration(config).build();

        //Code sample for Person Information with ADDRESS .. no EMAIL
//        List<Field> fields = new LinkedList<>();
//        fields.add( new Name.Builder().first("Prafulla").last("Girgaonkar").build());
//        fields.add( new Address.Builder().country("US").state("MN").city("burnsville")
//                .street("143rd Street W").house("1711").apartment("122").build());

//        Person p = new Person(fields);
//        request = new SearchAPIRequest(p,config);
        try {
            SearchAPIResponse  response = request.send();
            System.out.println(response.json);
            writeToFile(filePath, firstName+"_"+lastName+".json", response.json);
        } catch (SearchAPIError searchAPIError) {
            searchAPIError.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void writeToFile(String filePath,String fileName, String text) throws IOException {
        Files.write(Paths.get(filePath+"/"+fileName), text.getBytes());
    }

    public static void runPiplClient(String emailId, String firstName, String lastName){
        PiplClient p = new PiplClient(emailId,firstName,lastName,"");
        p.searchInformation();
    }

    public static void main(String[] args){
//        runPiplClient("alex.radka@gmail.com", "Alex", "Radka");
//        runPiplClient("dvwilker@gmail.com", "DV", "Wilker");
        runPiplClient("edbooth66@gmail.com", "Ed", "Booth");
//        runPiplClient("yogi.singh@gmail.com", "Yogi", "Singh");
//        runPiplClient("", "Jerome", "Radka");


    }
}

