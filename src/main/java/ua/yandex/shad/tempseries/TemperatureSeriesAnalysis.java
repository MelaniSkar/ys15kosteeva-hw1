package ua.yandex.shad.tempseries;
import java.util.InputMismatchException;
public class TemperatureSeriesAnalysis { 
  
    public static final int MIN_TEMPERATURE = -273;
    public static final double EPSILON = 0.00001;
    private double[] temperatureSeries;
    private int length;

    public TemperatureSeriesAnalysis() { 
        this.temperatureSeries = new double[0];
        this.length = 0;
    }
    public TemperatureSeriesAnalysis(double[] temperatureSeries) { 
        double[] copyTemperatureSeries = new double[temperatureSeries.length];
        for (int i = 0; i < temperatureSeries.length; i++) {
            copyTemperatureSeries[i] = temperatureSeries[i];
        }
        this.temperatureSeries = copyTemperatureSeries;
        this.length = copyTemperatureSeries.length;
    }
    public double[] getValues() {
        double[] res = new double[temperatureSeries.length];
        for (int i = 0; i < temperatureSeries.length; i++) {
            res[i] = temperatureSeries[i];
        }
        return res;
    }
    public int getLength() {
        return this.length;
    }
    public double average() {
        if (length == 0) {
            throw new IllegalArgumentException();
        }
        double avg = 0;
        for (int i = 0; i < length; i++) {
            avg += temperatureSeries[i];
        }
        avg = avg / length;
        
        return avg;
    }    
    public double deviation() {
        if (length == 0) { 
            throw new IllegalArgumentException();
        }
        double res = 0;
        double avg = this.average();
        for (int i = 0; i < length; i++) {
            res += (temperatureSeries[i] - avg) * (temperatureSeries[i] - avg);
        }
        res = Math.sqrt(res / this.length);
        return res;
    }
    public double min() {
        if (length == 0) {
            throw new IllegalArgumentException();
        }
        double result = temperatureSeries[0];
        for (int i = 0; i < length; i++) {
            if (temperatureSeries[i] < result) {
                result = temperatureSeries[i];
            }
        }
        return result;
    }
    public double max() {
        if (length == 0) {
            throw new IllegalArgumentException();
        }
        double res = temperatureSeries[0];
        for (int i = 0; i < length; i++) {
            if (temperatureSeries[i] > res) {
                res = temperatureSeries[i];
            }
        }
        return res;
    }
    public double findTempClosestToZero() {
        if (length == 0) {
            throw new IllegalArgumentException();
        }
        double result = Math.abs(temperatureSeries[0]);
        for (int i = 0; i < length; i++) {
            if (Math.abs(temperatureSeries[i]) < result) {
                result = Math.abs(temperatureSeries[i]);
            }
        }
        for (int i = 0; i < length; i++) {
            if (Math.abs(temperatureSeries[i] - result) < EPSILON) {
                return temperatureSeries[i];
            }
        }
        return -result;
    }
    public double findTempClosestToValue(double tempValue) {
        if (length == 0) {
            throw new IllegalArgumentException();
        }
        double res = Math.abs(temperatureSeries[0]);
        double delta = Math.abs(res - tempValue);
        for (int i = 0; i < length; i++) {
            if (Math.abs(temperatureSeries[i] - tempValue) < delta) {
                res = Math.abs(temperatureSeries[i]);
                delta = Math.abs(temperatureSeries[i] - tempValue);
            }
        }
        for (int i = 0; i < length; i++) {
            if (Math.abs(temperatureSeries[i] - res) < EPSILON) {
                return temperatureSeries[i];
            }
        }
        return -res;
    }
    public double[] findTempsLessThen(double temp) {
        if (length == 0) {
            throw new IllegalArgumentException();
        }
        int counter = 0;
        for (int i = 0; i < length; i++) {
            if (temperatureSeries[i] < temp) {
                counter++;
            }
        }
        double[] result = new double[counter];
        int k = 0;
        for (int i = 0; i < length; i++) {
            if (temperatureSeries[i] < temp) {
                result[k] = temperatureSeries[i];
                k++;
            }
        }
            return result;
    }
    public double[] findTempsGreaterThen(double tempValue) {
        if (length == 0) {
            throw new IllegalArgumentException();
        }
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (temperatureSeries[i] > tempValue) {
                count++;
            }
        }
        double[] res = new double[count];
        int k = 0;
        for (int i = 0; i < length; i++) {
            if (temperatureSeries[i] > tempValue) {
                res[k] = temperatureSeries[i];
                k++;
            }
        }
            return res;
    }
    public TempSummaryStatistics summaryStatistics() {
        if (length == 0) {
            throw new IllegalArgumentException();
        }
        TempSummaryStatistics tempSummaryStatistics
        = new TempSummaryStatistics(this);
        return tempSummaryStatistics;
    }
    public int addTemps(double ... temps) {
        for (double val : temps) {
            if (val < MIN_TEMPERATURE) {
                throw new InputMismatchException();
            }
        }
        
        int lengthAfterAdd = temperatureSeries.length;
        int tempsLength = temps.length;
        if (lengthAfterAdd == 0) {
            this.temperatureSeries = new double[tempsLength];
            lengthAfterAdd = tempsLength;
        }
        
        while (lengthAfterAdd < length + tempsLength) {
            lengthAfterAdd *= 2;
        }
        int numberOfElements = length + tempsLength;
        double[] resultMas = new double[lengthAfterAdd];
        for (int i = 0; i < length; i++) {
            resultMas[i] = temperatureSeries[i];
        }
      
        for (int i = length; i < length + temps.length; i++) {
            resultMas[i] = temps[i-length];
        }
        
        this.temperatureSeries = resultMas;
        this.length = numberOfElements;
        return numberOfElements;
    }
}
