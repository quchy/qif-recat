package qifrecat;

import lombok.Cleanup;
import lombok.NonNull;
import lombok.extern.java.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


/**
 * Created by zskucsak on 14/07/2017.
 * Controller class - doing the heavy lifting
 */

@Log
class MappingCtrl {

    @NonNull
    QIFMap MappedEntries;  // Store mapping data

    @NonNull
    protected   String FileNameQIF;                // QIF file (to be converted)

    @NonNull
    String FileNameMapper;             // Input map file (json)


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
        MappedEntries = new QIFMap();
    }


    void run() throws IOException {
        log.info("Start mapping");

        // Load Mapping info
        populateMap();

        // Read QIF and save updated info
        log.info("Opening input file of '" + FileNameQIF + "'");
        @Cleanup BufferedReader inputStream = new BufferedReader(new FileReader(FileNameQIF));

        String FileNameQIFOut = FileNameQIF.replaceFirst(".qif", "_RC.qif");
        log.info("Output file name: '" + FileNameQIFOut + "'");
        @Cleanup PrintWriter outputStream = new PrintWriter(new FileWriter(FileNameQIFOut));


        String QIFLine = null;
        String origPayee = null;
        while ((QIFLine = inputStream.readLine()) != null) {

            if (QIFLine.isEmpty()) continue;
            // Payee input field is useless
            if (QIFLine.charAt(0) == 'P') {
                if (QIFLine.length() == 1)  throw new IOException("Paye not defined. Maybe wrong export. Pending transactions are not allowed here.");
                origPayee = QIFLine.substring(1, QIFLine.length());
                continue;
            }

            // Memo field holds lots of valuable info. Including our keys.
            if (QIFLine.charAt(0) == 'M'){

                QIFMapEntry.MEValue result= MappedEntries.findValue(QIFLine);
                if ( result == null ) {
                    result = new QIFMapEntry().getValue();
                    result.setPayee(origPayee);
                    result.setArea("");
                    result.setCategory("");
                }

                outputStream.println("P"+result.getPayee());

                String Label = "L"+result.getCategory() + "/" + result.getArea();
                if (Label.length() > 2) outputStream.println(Label);
                if (QIFLine.trim().length() == 1) QIFLine = "M"+result.getPayee();

            }
            outputStream.println(QIFLine);

            if (QIFLine.startsWith("^") == true) {
                outputStream.println();
                origPayee = null;
            }
        }

        log.info("Mission completed");
    }





    protected   void populateMap()  throws IOException {
        log.info("Read mapping info");

        // Reading file
        Path pathMap = Paths.get(FileNameMapper);
        List<String> txtContent = Files.readAllLines(pathMap, Charset.defaultCharset());

        // Parsing as JSON
        JSONObject json = new JSONObject( String.join("", txtContent) );
        JSONArray jsonContent = json.getJSONArray("QIFMap");

        // Read and store each items
        for (int i = 0; i < jsonContent.length(); i++) {

            QIFMapEntry entry = new QIFMapEntry();

            entry.getKey().setKey1(jsonContent.getJSONObject(i).getString("Mem1"));
            entry.getKey().setKey2(jsonContent.getJSONObject(i).getString("Mem2"));

            entry.getValue().setPayee(jsonContent.getJSONObject(i).getString("Payee"));
            entry.getValue().setCategory(jsonContent.getJSONObject(i).getString("Category"));
            entry.getValue().setArea(jsonContent.getJSONObject(i).getString("Area"));

            // Save it into a map
            MappedEntries.put(entry);
            log.info("Processed map entry: " + entry.getKey() + " --> " + entry.getValue());
        }
    }

}
