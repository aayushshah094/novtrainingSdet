
public class LocationBuilder {
	private LocationObjectMother mother;

	public LocationBuilder() {
		this.mother = new LocationObjectMother();
	}
	public LocationBuilder withState(State state) {
		this.mother.State = state;

		return this;
	}
	public LocationBuilder withCity(City City) {
		this.mother.City = City;

		return this;
	}
	public LocationBuilder withMotto(Motto Motto) {
		this.mother.Motto = Motto;

		return this;
	}
	public LocationObjectMother Build() {

		return this.mother;
	}

}
