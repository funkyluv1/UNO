package Data_access;

import use_case.initiation.InitiationDataAccessInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileUserDataAccessObject implements InitiationDataAccessInterface {
    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();


    public FileUserDataAccessObject(String csvPath) throws IOException {

        csvFile = new File(csvPath);
        headers.put("playername", 0);
        headers.put("userID", 1);
        headers.put("numcards", 2);
        headers.put("funccards", 3);


    }

    @Override
    public void create() {
        ;
    }
}
