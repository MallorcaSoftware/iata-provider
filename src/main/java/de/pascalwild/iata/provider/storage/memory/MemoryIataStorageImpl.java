package de.pascalwild.iata.provider.storage.memory;

import de.pascalwild.iata.provider.model.IataData;
import de.pascalwild.iata.provider.storage.IataStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pwild on 18.06.16.
 */
public class MemoryIataStorageImpl implements IataStorage {

    private List<IataData> iataDataList = new ArrayList<>();
    private Map<String, IataData> iataDataByIataCode = new HashMap<>();

    public MemoryIataStorageImpl(List<IataData> iataDataList) {
        this.iataDataList = iataDataList;

        init();
    }

    @Override
    public IataData getIataDataByIataCode(String iataCode) {
        return iataDataByIataCode.get(iataCode);
    }

    private void init() {
        for (IataData iataData : iataDataList) {
            iataDataByIataCode.put(iataData.getIata(), iataData);
        }
    }
}
