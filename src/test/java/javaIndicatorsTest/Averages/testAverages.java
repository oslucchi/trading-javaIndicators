package javaIndicatorsTest.Averages;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import Tick.Tick;
import Tick.TickLogger;
import javaIndicators.Averages.ExponentialMovingAverage;
import javaIndicators.Averages.SimpleMovingAverage;
import javaIndicators.Averages.SmoothedMovingAverage;
import javaIndicators.Averages.WeightedMovingAverage;
import utils.DataLoader;

public class testAverages {
	double avgCalc = 0;
	List<Tick> tickList = new ArrayList<Tick>();

	public testAverages()
	{
		tickList = DataLoader.populateTestData("docs/averageMarketData.ser");
	}
	
	@Test
	public void testObjectCreation() 
	{
		WeightedMovingAverage wma = new WeightedMovingAverage(3, TickLogger.CONTINUOUS);
		SimpleMovingAverage sma = new SimpleMovingAverage(3, TickLogger.CONTINUOUS);
		ExponentialMovingAverage ema = new ExponentialMovingAverage(3, TickLogger.CONTINUOUS);
		assertNotNull(wma);
		assertNotNull(ema);
		assertNotNull(sma);
	}

	@Test
	public void testMovingAverages() {
		int[] weights = {1, 2, 3, 4};
		double[] resultsSMA = {
				47.980000000,47.925000000,47.950000000,48.140000000,48.426666667,
				48.710000000,48.926666667,48.733333333,48.563333333,48.196666667,
				48.116666667,48.070000000,48.213333333,48.590000000,48.733333333,
				48.473333333,47.876666667,47.493333333,47.333333333,47.386666667,
				47.190000000,46.803333333,46.423333333,46.140000000,46.543333333,
				46.990000000,47.476666667,47.906666667,48.163333333,48.480000000,
				48.546666667,48.890000000,49.183333333,49.533333333,49.666666667,
				49.980000000,50.250000000,50.453333333,50.500000000,50.480000000,
				50.006666667,49.650000000,49.053333333,48.983333333,48.680000000,
				48.553333333,48.363333333,48.290000000,48.006666667,47.830000000,
				47.646666667,47.636666667,47.513333333,47.133333333,46.783333333,
				46.576666667,46.740000000,46.893333333,47.183333333,47.636666667,
				48.013333333,48.303333333,48.326666667,48.400000000,48.403333333,
				48.293333333,48.173333333,48.283333333,48.340000000,48.170000000,
				47.756666667,47.586666667,47.900000000,48.346666667,48.766666667,
				48.983333333,48.960000000,49.080000000,49.306666667,49.383333333,
				49.683333333,49.983333333,50.436666667,50.630000000,50.693333333,
				50.933333333,50.983333333,50.973333333,50.786666667,50.680000000,
				50.590000000,50.546666667,50.613333333,50.876666667,51.186666667,
				51.190000000,51.246666667,51.196666667,51.260000000,51.003333333,
				50.823333333,50.673333333,50.590000000,50.560000000,50.516666667,
				50.390000000,50.343333333,50.440000000,50.696666667,50.866666667,
				50.883333333,51.050000000,51.293333333,51.586666667,51.686666667,
				52.016666667,52.326666667,52.706666667,52.840000000,52.896666667,
				52.843333333,52.736666667,52.656666667,52.700000000
		};

		double resultsWMA[] = {
				47.980000000,47.906666667,47.953333333,48.253333333,48.548333333,
				48.760000000,49.005000000,48.616666667,48.420000000,48.188333333,
				48.045000000,48.086666667,48.316666667,48.730000000,48.750000000,
				48.258333333,47.646666667,47.448333333,47.336666667,47.375000000,
				47.126666667,46.586666667,46.320000000,46.128333333,46.718333333,
				47.251666667,47.506666667,48.073333333,48.310000000,48.453333333,
				48.618333333,49.050000000,49.270000000,49.608333333,49.746666667,
				50.048333333,50.393333333,50.478333333,50.456666667,50.511666667,
				49.771666667,49.438333333,49.023333333,48.891666667,48.615000000,
				48.495000000,48.328333333,48.251666667,47.901666667,47.743333333,
				47.658333333,47.615000000,47.456666667,46.960000000,46.648333333,
				46.606666667,46.823333333,46.938333333,47.276666667,47.870000000,
				48.101666667,48.315000000,48.383333333,48.380000000,48.405000000,
				48.258333333,48.091666667,48.395000000,48.393333333,47.948333333,
				47.633333333,47.640000000,48.041666667,48.531666667,48.873333333,
				49.010000000,48.923333333,49.138333333,49.458333333,49.325000000,
				49.778333333,50.246666667,50.455000000,50.671666667,50.761666667,
				50.975000000,51.018333333,50.916666667,50.710000000,50.666666667,
				50.581666667,50.501666667,50.678333333,51.021666667,51.263333333,
				51.125000000,51.265000000,51.246666667,51.198333333,50.918333333,
				50.751666667,50.665000000,50.553333333,50.548333333,50.528333333,
				50.305000000,50.330000000,50.563333333,50.763333333,50.890000000,
				50.886666667,51.115000000,51.430000000,51.653333333,51.680000000,
				52.171666667,52.498333333,52.725000000,52.906666667,52.906666667,
				52.768333333,52.721666667,52.653333333,52.700000000
		};
		
		double[] resultsEMA = {
				47.980000000,47.925000000,47.962500000,48.256250000,48.493125000,
				48.671562500,48.935781250,48.542890625,48.441445313,48.270722656,
				48.090361328,48.145180664,48.337590332,48.688795166,48.659397583,
				48.204698792,47.727349396,47.603674698,47.436837349,47.423418674,
				47.156709337,46.633354669,46.451677334,46.245838667,46.782919334,
				47.196459667,47.348229833,47.979114917,48.179557458,48.314778729,
				48.562389365,48.986194682,49.158097341,49.509048671,49.659524335,
				49.964762168,50.317381084,50.368690542,50.389345271,50.499672635,
				49.749836318,49.544918159,49.182459079,48.986229540,48.708114770,
				48.574057385,48.397028692,48.303514346,47.946757173,47.818378587,
				47.739189293,47.649594647,47.484797323,47.002398662,46.756199331,
				46.728099665,46.869049833,46.919524916,47.244762458,47.807381229,
				47.953690615,48.196845307,48.318422654,48.319211327,48.384605663,
				48.247302832,48.103651416,48.441825708,48.360912854,47.905456427,
				47.722728213,47.746364107,48.068182053,48.474091027,48.752045513,
				48.896022757,48.853011378,49.121505689,49.420752845,49.230376422,
				49.760188211,50.190094106,50.295047053,50.582523526,50.696261763,
				50.908130882,50.964065441,50.872032720,50.716016360,50.708008180,
				50.609004090,50.519502045,50.709751023,51.004875511,51.182437756,
				51.046218878,51.258109439,51.234054719,51.167027360,50.933513680,
				50.801756840,50.725878420,50.587939210,50.583969605,50.551984802,
				50.310992401,50.375496201,50.592748100,50.716374050,50.833187025,
				50.846593513,51.093296756,51.386648378,51.563324189,51.601662095,
				52.135831047,52.402915524,52.591457762,52.830728881,52.835364440,
				52.727682220,52.738841110,52.669420555,52.709710278
		};
		
		WeightedMovingAverage wma = new WeightedMovingAverage(3, TickLogger.CONTINUOUS);
		SimpleMovingAverage sma = new SimpleMovingAverage(3, TickLogger.CONTINUOUS);
		ExponentialMovingAverage ema = new ExponentialMovingAverage(3, TickLogger.CONTINUOUS);
		
		wma.setWeights(weights);
		TickLogger tl = TickLogger.getInstance();
		for(int newItem = 0; newItem < tickList.size(); newItem++)
		{
			tl.addTick(tickList.get(newItem));
			try 
			{
				wma.addItem();
				ema.addItem();
				sma.addItem();
			} 
			catch (NoSuchFieldException | SecurityException |
				   IllegalArgumentException | IllegalAccessException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			assertEquals(resultsSMA[newItem], sma.getAverage().close, 0.0000001);
			assertEquals(resultsWMA[newItem], wma.getAverage().close, 0.0000001);
			assertEquals(resultsEMA[newItem], ema.getAverage().close, 0.0000001);
		}
	}
	
	@Test
	public void testSmoothedMovingAverages() {
		double[] resultsSMMA = {
				  0.0000, 0.0000, 0.0000, 0.0000, 0.0000, 0.0000, 0.0000, 0.0000,
				  0.0000, 0.0000, 0.0000, 0.0000, 0.0000, 0.0000, 0.0000, 0.0000, 0.0000, 0.0000, 
				  0.0000, 0.0000,48.3392,48.3931,48.4114,48.3605,48.2751,48.2139,48.1413,48.0850,
				 47.9931,47.8483,47.7269,47.5971,47.5758,47.5784,47.5724,47.6522,47.7082,47.7652,
				 47.8456,47.9659,48.0709,48.2085,48.3317,48.4808,48.6492,48.7854,48.9104,49.0411,
				 49.0380,49.0612,49.0426,49.0232,48.9776,48.9362,48.8811,48.8295,48.7342,48.6538,
				 48.5774,48.4991,48.4084,48.2632,48.1283,48.0184,47.9409,47.8662,47.8434,47.8839,
				 47.9005,47.9420,47.9803,48.0065,48.0406,48.0459,48.0393,48.0963,48.1104,48.0596,
				 48.0196,48.0004,48.0304,48.0958,48.1676,48.2347,48.2790,48.3644,48.4687,48.5127,
				 48.6494,48.8010,48.9240,49.0737,49.2072,49.3544,49.4825,49.5823,49.6575,49.7377,
				 49.7971,49.8458,49.9269,50.0325,50.1346,50.1943,50.2924,50.3630,50.4197,50.4412,
				 50.4588,50.4735,50.4717,50.4801,50.4831,50.4514,50.4505,50.4781,50.5060,50.5401,
				 50.5647,50.6244,50.7056,50.7851,50.8509,50.9908,51.1200,51.2477,51.3879,51.4996,
				 51.5858,51.6753,51.7464,51.82361 
		};
		TickLogger tl = TickLogger.getInstance();
		SmoothedMovingAverage smma = new SmoothedMovingAverage(13, 8, TickLogger.CONTINUOUS);
		for(int newItem = 0; newItem < tickList.size(); newItem++)
		{
			tl.addTick(tickList.get(newItem));
			try 
			{
				smma.addItem();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
//			System.out.format("%7.4f, %7.4f\n", tl.getClosureOfDay(TickLogger.CONTINUOUS, 0).close, smma.getValue());
			assertEquals(resultsSMMA[newItem], smma.getValue(), 0.001);
		}
	}
}