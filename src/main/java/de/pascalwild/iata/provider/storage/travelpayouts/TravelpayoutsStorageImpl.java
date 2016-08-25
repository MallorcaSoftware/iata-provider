package de.pascalwild.iata.provider.storage.travelpayouts;

import com.google.gson.Gson;
import de.pascalwild.iata.provider.model.IataData;
import de.pascalwild.iata.provider.storage.memory.MemoryIataStorageImpl;
import de.pascalwild.iata.provider.storage.travelpayouts.param.Airport;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pwild on 25.08.16.
 */
public class TravelpayoutsStorageImpl extends MemoryIataStorageImpl {
    List<Airport> airportList;

    public TravelpayoutsStorageImpl(InputStream inputStream) {
        Gson gson = new Gson();

        Airport[] airports = gson.fromJson(new InputStreamReader(inputStream), Airport[].class);
        airportList = new ArrayList<Airport>(Arrays.asList(airports));

        init();
    }

    private void init() {
        List<IataData> iataDataList = new ArrayList<>();

        for (Airport airport : airportList) {
            IataData iataData = new IataData();

            iataData.setName(airport.getName());
            iataData.setIata(airport.getCode());
            iataData.setLatitude(airport.getCoordinates().getLat());
            iataData.setLongitude(airport.getCoordinates().getLon());
            iataData.setCity(airport.getCityCode());
            iataData.setCountry(airport.getCountryCode());

            iataDataList.add(iataData);
        }

        setIataDataList(iataDataList);
    }
}
