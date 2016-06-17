package de.pascalwild.iata.provider.storage;

import de.pascalwild.iata.provider.model.IataData;

/**
 * Created by pwild on 18.06.16.
 */
public interface IataStorage {
    IataData getIataDataByIataCode(String iataCode);
}
