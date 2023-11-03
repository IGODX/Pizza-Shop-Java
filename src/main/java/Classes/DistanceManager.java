package Classes;

public class DistanceManager {
    private static final double EARTH_RADIUS = 6371.0;

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = toRad(lat2 - lat1);
        double dLon = toRad(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = EARTH_RADIUS * c;
        return distance;
    }

    private double toRad(double degrees) {
        return degrees * (Math.PI / 180);
    }

    public boolean isInRange(double shopLon, double shopLat,double lon, double lat) {
        double distance = calculateDistance(shopLat, shopLon, lat, lon);
        return distance <= 10;
    }
}
