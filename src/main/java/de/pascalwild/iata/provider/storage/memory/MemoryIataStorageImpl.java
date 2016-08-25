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

    public MemoryIataStorageImpl() {

    }

    public MemoryIataStorageImpl(List<IataData> iataDataList) {
        setIataDataList(iataDataList);
    }

    public List<IataData> getIataDataList() {
        return iataDataList;
    }

    public void setIataDataList(List<IataData> iataDataList) {
        this.iataDataList = iataDataList;

        init();
    }

    @Override
    public IataData getIataDataByIataCode(String iataCode) {
        return iataDataByIataCode.get(iataCode);
    }

    @Override
    public IataData getNearestByLatLng(float lat, float lng) {
        IataData nearest = null;

        for (IataData iataData : getIataDataList()) {
            if (nearest == null) {
                nearest = iataData;
            } else {
                if (distance(lat, lng, nearest.getLatitude(), nearest.getLongitude()) > distance(lat, lng, iataData.getLatitude(), iataData.getLongitude())) {
                    nearest = iataData;
                }
            }
        }

        return nearest;
    }

    private double distance(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 3958.75; // in miles, change to 6371 for kilometer output

        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);

        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);

        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }

    private void init() {
        iataDataByIataCode.clear();
        for (IataData iataData : iataDataList) {
            iataDataByIataCode.put(iataData.getIata(), iataData);
        }
    }
}
