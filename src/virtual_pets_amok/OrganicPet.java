package virtual_pets_amok;

	public interface OrganicPet {

		String getName();

		void feed();

		void water();
		
		int tick();
		
		int getHunger();
		int getThirst();

	}

