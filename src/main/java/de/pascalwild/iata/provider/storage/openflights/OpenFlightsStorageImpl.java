package de.pascalwild.iata.provider.storage.openflights;

import de.pascalwild.iata.provider.model.IataData;
import de.pascalwild.iata.provider.storage.memory.MemoryIataStorageImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by pwild on 18.06.16.
 */
public class OpenFlightsStorageImpl extends MemoryIataStorageImpl {

    public OpenFlightsStorageImpl(String airportDatFilePath) {
        setIataDataList(processFile(airportDatFilePath));
    }

    private List<IataData> processFile(String fileName) {
        List<IataData> iataDataList = new ArrayList<>();
        String cvsSplitBy = ",";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                try {
                    String line = scanner.nextLine();
                    String[] data = line.split(cvsSplitBy);

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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return iataDataList;

    }
}
