package ua.yandex.shad.tempseries;
import java.util.InputMismatchException;

import static org.junit.Assert.*;
import org.junit.Test;

public class TemperatureSeriesAnalysisTest {
    
    @Test
    public void TemperatureSeriesAnalysisCheckResultLength() {
        double[] temperatureSeries = {1.0, -5.0, -1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis 
        = new TemperatureSeriesAnalysis(temperatureSeries);
        int expResult = 4;
        int actualResult = seriesAnalysis.getLength();
        assertEquals(expResult, actualResult);
    }
    @Test
    public void TemperatureSeriesAnalysisWithEmptyList() {
        double[] temperatureSeries = { };
        TemperatureSeriesAnalysis seriesAnalysis 
        = new TemperatureSeriesAnalysis(temperatureSeries);
        int expResult = 0;
        int actualResult = seriesAnalysis.getLength();
        assertEquals(expResult, actualResult);
    }
    @Test
    public void testAverage() {
        double[] temperatureSeries = {1.0, -5.0, -1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis 
        = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.0;
        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAverageFailOnEmptyList() {
        TemperatureSeriesAnalysis seriesAnalysis 
        = new TemperatureSeriesAnalysis();
        double actualResult = seriesAnalysis.average();
    }
    @Test
    public void testDeviation() {
        double[] temperatureSeries = {0.0, -0.1, -0.0, 0.1};
        TemperatureSeriesAnalysis seriesAnalysis 
        = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = Math.sqrt(0.02 / 4);
        double actualResult = seriesAnalysis.deviation();
        assertEquals(expResult, actualResult, 0.00001);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testDeviationFailOnEmptyList() {
        TemperatureSeriesAnalysis seriesAnalysis 
        = new TemperatureSeriesAnalysis();
        double actualResult = seriesAnalysis.deviation();
    }
    public void testMin() {
        double[] temperatureSeries = {1.0, -270, -1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis 
        = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -270;
        double actualResult = seriesAnalysis.min();
        assertEquals(expResult, actualResult, 0.00001);
        
    }
    @Test(expected = IllegalArgumentException.class)
    public void testMinFailOnEmptyList() {
        TemperatureSeriesAnalysis seriesAnalysis 
        = new TemperatureSeriesAnalysis();
        double actualResult = seriesAnalysis.min();
    }
    @Test
    public void testMax() {
        double[] temperatureSeries = {-2.0, -270, -1.0, 0.0};
        TemperatureSeriesAnalysis seriesAnalysis 
        = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.0;
        double actualResult = seriesAnalysis.max();
        assertEquals(expResult, actualResult, 0.00001);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testMaxFailOnEmptyList() {
        TemperatureSeriesAnalysis seriesAnalysis 
        = new TemperatureSeriesAnalysis();
        double actualResult = seriesAnalysis.max();
    }
    @Test
    public void testFindTempClosestToZeroEqualModul() {
        double[] temperatureSeries = {-5.0, -270, 5.0, 10.0};
        TemperatureSeriesAnalysis seriesAnalysis 
        = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 5.0;
        double actualResult = seriesAnalysis.findTempClosestToZero();
        assertEquals(expResult, actualResult, 0.00001);
    }
    @Test
    public void testFindTempClosestToZero() {
        double[] temperatureSeries = {-270, 6.0, -5.0, 10.0};
        TemperatureSeriesAnalysis seriesAnalysis 
        = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -5.0;
        double actualResult = seriesAnalysis.findTempClosestToZero();
        assertEquals(expResult, actualResult, 0.00001);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToZeroFailOnEmptyList() {
        TemperatureSeriesAnalysis seriesAnalysis 
        = new TemperatureSeriesAnalysis();
        double actualResult = seriesAnalysis.findTempClosestToZero();
    }
    @Test
    public void testFindTempClosestToValueEqualModul() {
        double[] temperatureSeries = {-270, 5.0, 10.0, -5.0};
        TemperatureSeriesAnalysis seriesAnalysis 
        = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 5.0;
        double actualResult = seriesAnalysis.findTempClosestToValue(0);
        assertEquals(expResult, actualResult, 0.00001);
    }
    @Test
    public void testFindTempClosestToValue() {
        double[] temperatureSeries = {-5.0, -270, 6.0, 10.0};
        TemperatureSeriesAnalysis seriesAnalysis 
        = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -5.0;
        double actualResult = seriesAnalysis.findTempClosestToValue(-10);
        assertEquals(expResult, actualResult, 0.00001);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToValueFailOnEmptyList() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        double actualResult = seriesAnalysis.findTempClosestToValue(5);
    }
    @Test
    public void testFindTempsLessThen() {
        double[] temperatureSeries = {-2.0, -270, -1.0, 0.0, 4, 24};
        TemperatureSeriesAnalysis seriesAnalysis 
        = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {-2.0, -270, -1.0, 0.0, 4};
        double[] actualResult = seriesAnalysis.findTempsLessThen(6);
        assertArrayEquals(expResult, actualResult, 0.00001);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFindTempsLessThenFailOnEmptyList() {
        TemperatureSeriesAnalysis seriesAnalysis 
        = new TemperatureSeriesAnalysis();
        seriesAnalysis.findTempsLessThen(5);
    }
    @Test
    public void testFindTempsGreaterThen() {
        double[] temperatureSeries = {-2.0, -270, -1.0, 0.0};
        TemperatureSeriesAnalysis seriesAnalysis 
        = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {-2.0, -1.0, 0.0};
        double[] actualResult = seriesAnalysis.findTempsGreaterThen(-5);
        assertArrayEquals(expResult, actualResult, 0.00001);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFindTempsGreaterThenFailOnEmptyList() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.findTempsGreaterThen(4);
    }
    @Test(expected = IllegalArgumentException.class)
    public void TestSummaryStatistics() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(); 
        TempSummaryStatistics actualResult = seriesAnalysis.summaryStatistics();
    }
    @Test(expected = IllegalArgumentException.class)
    public void TestSummaryStatisticsFailOnEmptyList() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(); 
        TempSummaryStatistics actualResult = seriesAnalysis.summaryStatistics();
    }
    @Test
    public void testAddTemps() {
        double[] temperatureSeries = {87, -46.8};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {87, -46.8, 1.0, 2.0, 3.0, 4.0, 0.0, 0.0};
        seriesAnalysis.addTemps(1, 2, 3, 4);
        double[] actualResult = seriesAnalysis.getValues();
        assertArrayEquals(expResult, actualResult, 0.00001);
    }
    @Test(expected = InputMismatchException.class)
    public void testAddTempsWithMismatch() {
        double[] temperatureSeries = {87, 4};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.addTemps(1, 2, 3, -290);
        double[] actualResult = seriesAnalysis.getValues();
    }
    @Test
    public void testAddTempsToEmptyList() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        double[] expResult = {1.0};
        seriesAnalysis.addTemps(1.0);
        double[] actualResult = seriesAnalysis.getValues();
        assertArrayEquals(expResult, actualResult, 0.00001);
    }
    @Test
    public void testAddTempsWithEmptyListToAdd() {
        double[] temperatureSeries = {1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {1.0};
        seriesAnalysis.addTemps();
        double[] actualResult = seriesAnalysis.getValues();
        assertArrayEquals(expResult, actualResult, 0.00001);
    }
    @Test
    public void testSummaryStatisticsTestValues() {
    	double[] temperatureSeries = {1.0, -85.0, -1.0, 2.0, 7};
    	TemperatureSeriesAnalysis seriesAnalysis = 
    			new TemperatureSeriesAnalysis(temperatureSeries);
    	TempSummaryStatistics statistics = seriesAnalysis.summaryStatistics();
    	double expDev = 34.99942;
    	double[] expResult = {-15.2, expDev, -85.0, 7.0};
    	double actualAvg = statistics.getAvgTemp();
    	double actualDev = statistics.getDevTemp();
    	double actualMin = statistics.getMinTemp();
    	double actualMax = statistics.getMaxTemp();
    	double[] actualResult = {actualAvg, actualDev, actualMin, actualMax};
    	assertArrayEquals(expResult, actualResult, 0.0001);
    	
    }
}