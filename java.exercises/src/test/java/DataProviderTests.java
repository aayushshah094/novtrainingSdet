import org.testng.Assert;
import org.testng.annotations.Test;

public class DataProviderTests {
  @Test
  public void cangGetStateNameTexas() {
	  var expectedState = "Texas";
	  
	  var actualState = States.Texas.Name;
	  
	  Assert.assertEquals(actualState,expectedState,"State name should be returned.");
 }
  
  @Test
  public void cangGetStateNameNewyork() {
	  var expectedState = "Newyork";
	  
	  var actualState = States.Newyork.Name;
	  
	  Assert.assertEquals(actualState,expectedState,"State name should be returned.");
 }
  
  @Test
  public void cangGetStateAbbreviationTexas() {
	  var expectedAbbreviation = "TX";
	  
	  var actualAbbreviation = States.Texas.Abbreviation;
	  
	  Assert.assertEquals(actualAbbreviation,expectedAbbreviation,"State Abbreviation should be returned.");
 }
  
  @Test
  public void cangGetStateAbbreviationNewyork() {
	  var expectedAbbreviation = "NY";
	  
	  var actualAbbreviation = States.Newyork.Abbreviation;
	  
	  Assert.assertEquals(actualAbbreviation,expectedAbbreviation,"State Abbreviation should be returned.");
 }
  
  @Test
  public void cangGetCityHouston() {
	  var expectedCity = "Houston";
	  
	  var actualCity = Cities.Houston.Name;
	  
	  Assert.assertEquals(actualCity,expectedCity,"City name should be returned.");
 }
  @Test
  public void cangGetCityQueens() {
	  var expectedCity = "Queens";
	  
	  var actualCity = Cities.Queens.Name;
	  
	  Assert.assertEquals(actualCity,expectedCity,"City name should be returned.");
 }
  @Test
  public void cangGetStateNameFromObjectLouisiana() {
	  var expectedState = "Louisiana";
	  
	  var actualState = getState(LocationObjectMothers.NewOrleans());
	  
	  Assert.assertEquals(actualState.Name,expectedState,"State should be returned.");
  }  
  @Test
  public void cangGetStateNameFromObjectTexas() {
	  var expectedState = "Texas";
	  
	  var actualState = getState(LocationObjectMothers.Houston());
	  
	  Assert.assertEquals(actualState.Name,expectedState,"State should be returned.");
  }  
  private State getState(LocationObjectMother mother) {
	  
	  return mother.State;
  }
  @Test
  public void cangGetNewyorkStateMotto() {
	  var expectedMotto = "Excelsior";
	  
	  var actualMotto = Motto.Excelsior.Name;
	  
	  Assert.assertEquals(actualMotto,expectedMotto,"State Motto should be returned.");
  }  
  
}