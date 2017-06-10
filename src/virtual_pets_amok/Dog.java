package virtual_pets_amok;

	public abstract class Dog extends VirtualPet {

		public Dog(String nameParam, String descriptionParam) {
			super(nameParam, descriptionParam);
		}
		
		public abstract void walk();
		
	}
