
public class LocationObjectMothers {
	public static LocationObjectMother NewOrleans() {
		return new LocationBuilder()
				.withState(States.Louisiana)
				.withCity(Cities.NewOrleans)
				.Build();

	}
	public static LocationObjectMother Houston() {
		return new LocationBuilder()
				.withState(States.Texas)
				.withCity(Cities.Houston)
				.Build();
	}
	public static LocationObjectMother Queens() {
		return new LocationBuilder()
				.withState(States.Newyork)
				.withCity(Cities.Queens)
				.Build();
	}
}
