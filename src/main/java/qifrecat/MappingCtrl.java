package qifrecat;

import lombok.NonNull;
import lombok.extern.java.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by zskucsak on 14/07/2017.
 * Controller class - doing the heavy lifting
 */

@Log
class MappingCtrl {

    @NonNull
    private Map<MEKey, MEValue> MappedEntries;  // Store mapping data

    @NonNull
    private  String FileNameQIF;                // QIF file (to be converted)

    @NonNull
    private  String FileNameMapper;             // Input map file (json)


    MappingCtrl(String[] args) {
        // We need two file names as input
        if (args.length != 2) {
            log.severe("Wrong number of arguments: define both QIF and Mapper files");
            System.exit(1);
        }
        // Save the input parameters
        FileNameQIF = args[0];
        FileNameMapper = args[1];
        // To Store Mapper rules
        MappedEntries = new HashMap<>();
    }


    void run() throws IOException {

        log.info("Start mapping");

        populateMap();


    }



    private  void populateMap()  throws IOException {
        log.info("Read mapping info");

        // Reading file
        Path pathMap = Paths.get(FileNameMapper);
        List<String> txtContent = Files.readAllLines(pathMap, Charset.defaultCharset());

        // Parsing as JSON
        JSONObject json = new JSONObject( String.join("", txtContent) );
        JSONArray jsonContent = json.getJSONArray("QIFMap");

        // Read and store each items
        for (int i = 0; i < jsonContent.length(); i++) {

            MEKey key = new MEKey();
            key.setKey1(jsonContent.getJSONObject(i).getString("Mem1"));
            key.setKey2(jsonContent.getJSONObject(i).getString("Mem2"));

            MEValue value = new MEValue();
            value.setPayee(jsonContent.getJSONObject(i).getString("Payee"));
            value.setCategory(jsonContent.getJSONObject(i).getString("Category"));
            value.setArea(jsonContent.getJSONObject(i).getString("Area"));

            // Save it into a map
            MappedEntries.put(key, value);
            log.info("Processed map entry: " + key + " --> " +value);
        }
    }

}
