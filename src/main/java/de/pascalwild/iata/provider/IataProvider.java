package de.pascalwild.iata.provider;

import de.pascalwild.iata.provider.model.IataData;
import de.pascalwild.iata.provider.storage.IataStorage;

/**
 * Created by pwild on 18.06.16.
 */
public class IataProvider {

    private IataStorage iataStorage;

    public IataProvider(IataStorage iataStorage) {
        this.iataStorage = iataStorage;
    }

    public IataData getIataDataByIataCode(String iataCode) {
        return iataStorage.getIataDataByIataCode(iataCode);
    }

    public IataData getNearestByLatLng(float lat, float lng) {
        return iataStorage.getNearestByLatLng(lat, lng);
    }
}
