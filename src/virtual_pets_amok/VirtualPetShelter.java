package virtual_pets_amok;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class VirtualPetShelter {
	private int litterBox = 50;

	Map<String, VirtualPet> shelterPets = new HashMap<String, VirtualPet>();
	Map<String, OrganicPet> organicDogs = new HashMap<String, OrganicPet>();
	Map<String, OrganicPet> organicCats = new HashMap<String, OrganicPet>();
	Map<String, RoboPet> roboticDogs = new HashMap<String, RoboPet>();
	Map<String, RoboPet> roboticCats = new HashMap<String, RoboPet>();

	public int getLitterBox() {
		return litterBox;
	}

	public void cleanLitterBox() {
		litterBox = 0;
	}

	public void maintainAllRobo() {
		for (RoboPet d : roboticDogs.values()) {
			d.oilPet();
			d.recharge();
		}
		for (RoboPet c : roboticCats.values()) {
			c.oilPet();
			c.recharge();
		}
	}

	public void cleanDogCages() {
		for (OrganicPet d : organicDogs.values()) {
			((OrgDog) d).cleanCage();
		}
	}

	public void walkDogs() {
		for (OrganicPet d : organicDogs.values()) {
			((Dog) d).walk();
		}
		for (RoboPet d : roboticDogs.values()) {
			((Dog) d).walk();
		}

	}

	public void intake(OrganicPet pet) {
		shelterPets.put(pet.getName(), (VirtualPet) pet);
		if (pet instanceof OrgCat) {
			organicCats.put(pet.getName(), pet);
		}
		if (pet instanceof OrgDog) {
			organicDogs.put(pet.getName(), pet);
		}
	}

	public void intake(RoboPet pet) {
		shelterPets.put(pet.getName(), (VirtualPet) pet);
		if (pet instanceof RoboCat) {
			roboticCats.put(pet.getName(), pet);
		}
		if (pet instanceof RoboDog) {
			roboticDogs.put(pet.getName(), pet);
		}
	}

	public void feedAllOrganic() {
		for (OrganicPet p : organicDogs.values()) {
			p.feed();
		}
		for (OrganicPet p : organicCats.values()) {
			p.feed();
		}
		litterBox += 10;
	}

	public void waterAllOrganic() {
		for (OrganicPet p : organicDogs.values()) {
			p.water();
		}
		for (OrganicPet p : organicCats.values()) {
			p.water();
		}
		litterBox += 5;
	}

	// Tick method 
	void tickAllPets() {
		for (OrganicPet p : organicDogs.values()) {
			p.tick();
		}
		for (OrganicPet p : organicCats.values()) {
			litterBox += p.tick();
		}
		for (RoboPet d : roboticDogs.values()) {
			((RoboDog) d).tick();
		}
		for (RoboPet c : roboticCats.values()) {
			((RoboCat) c).tick();
		}
	}

	// Method returning collection of all pets in the shelter
	public Collection<VirtualPet> pets() {
		return shelterPets.values();
	}

	public Collection<OrganicPet> organicDogs() {
		return organicDogs.values();
	}

	public Collection<OrganicPet> organicCats() {
		return organicCats.values();
	}

	public Collection<RoboPet> roboticDogs() {
		return roboticDogs.values();
	}

	public Collection<RoboPet> roboticCats() {
		return roboticCats.values();
	}

	// Method to return a pet from the map by name
	public VirtualPet getPet(String name) {
		return shelterPets.get(name.toLowerCase());

	}

	// Method to play with one pet
	public void playOne(VirtualPet player) {
		player.play();
	}

	// Method to adopt a pet
	public void adoptPet(VirtualPet pet) {
		shelterPets.remove(pet.name.toLowerCase());

		if (pet instanceof RoboDog) {
			roboticDogs.remove(pet.name.toLowerCase(), pet);
		}
		if (pet instanceof RoboCat) {
			roboticCats.remove(pet.name.toLowerCase(), pet);
		}
		if (pet instanceof OrgDog) {
			organicDogs.remove(pet.name.toLowerCase(), pet);
		}
		if (pet instanceof OrgCat) {
			organicCats.remove(pet.name.toLowerCase(), pet);
		}
	}
}