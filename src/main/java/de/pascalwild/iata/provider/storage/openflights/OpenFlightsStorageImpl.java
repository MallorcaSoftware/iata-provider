package de.pascalwild.iata.provider.storage.openflights;

import com.opencsv.CSVReader;
import de.pascalwild.iata.provider.model.IataData;
import de.pascalwild.iata.provider.storage.memory.MemoryIataStorageImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by pwild on 18.06.16.
 */
public class OpenFlightsStorageImpl extends MemoryIataStorageImpl {
    private CSVReader csvReader;

    public OpenFlightsStorageImpl(InputStream inputStream) {
        csvReader = new CSVReader(new InputStreamReader(inputStream));

        init();
    }

    private void init() {
        List<IataData> iataDataList = new ArrayList<>();
        String [] data;
        try {
            while ((data = csvReader.readNext()) != null) {
                String airportName = data[1].replace("\"", "");
                String city = data[2].replace("\"", "");
                String country = data[3].replace("\"", "");
                String iataCode = data[4].replace("\"", "");
                String icao = data[5].replace("\"", "");
                String latitude = data[6];
                String longitude = data[7];

                if (!iataCode.isEmpty()) {
                    IataData iataData = new IataData();
                    iataData.setName(airportName);
                    iataData.setCity(city);
                    iataData.setCountry(country);
                    iataData.setIata(iataCode);
                    iataData.setIcao(icao);
                    iataData.setLatitude(Float.parseFloat(latitude));
                    iataData.setLongitude(Float.parseFloat(longitude));

                    iataDataList.add(iataData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        setIataDataList(iataDataList);
    }
}
