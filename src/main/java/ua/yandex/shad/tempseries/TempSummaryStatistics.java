package ua.yandex.shad.tempseries;

public class TempSummaryStatistics {
    private final double avgTemp;
    private final double devTemp;
    private final double minTemp;
    private final double maxTemp;
    
    public TempSummaryStatistics(TemperatureSeriesAnalysis tempSeries) {
        avgTemp = tempSeries.average();
        devTemp = tempSeries.deviation();
        minTemp = tempSeries.min();
        maxTemp = tempSeries.max();
    }
    public double getAvgTemp() {
        return avgTemp;
    }
    public double getDevTemp() {
        return devTemp;
    }
    public double getMinTemp() {
        return minTemp;
    }
    public double getMaxTemp() {
        return maxTemp;
    }
}
