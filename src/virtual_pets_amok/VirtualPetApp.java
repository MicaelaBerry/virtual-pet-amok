package virtual_pets_amok;

import java.util.Scanner;

public class VirtualPetApp {

	public static void main(String[] args) {

		VirtualPetShelter shelter = new VirtualPetShelter();
		Scanner input = new Scanner(System.in);

		OrganicPet donna = new OrgDog("Donna", "Sassy German Shepard");
		shelter.intake(donna);
		RoboPet K9 = new RoboDog("K9", "K9 Mark I");
		shelter.intake(K9);
		OrganicPet river = new OrgCat("River", "Ginger Cat who likes time");
		shelter.intake(river);
		RoboPet sarah = new RoboCat("SarahBot", "RoboCat 2.0");
		shelter.intake(sarah);
		OrganicPet jack = new OrgDog("Jack", "Terrier with a trenchcoat");
		shelter.intake(jack);

		boolean quit = false;
		writeLine("Thank you for volunteering at The Doctor's Shelter for Pets! "
				+ "\nTake a look at all our pets below. Please keep in mind we have both robotic and organic pets.");

		do {
			if (shelter.getLitterBox() >= 100) {
				writeLine("The litterbox is really smelly. You should really clean it.");
				for (VirtualPet pet : shelter.pets()) {
					if (pet instanceof OrgCat) {
						((OrgCat) pet).decreaseHealth();
					}
				}
			}
			for (VirtualPet pet : shelter.pets()) {
				if (pet instanceof OrgDog) {

					if (((OrgDog) pet).getCageMessiness() >= 100) {
						writeLine(pet.getName() + "'s cage is getting messy and you should probably clean it.");
						((OrgDog) pet).decreaseHealth();
					}
				}
			}

			writeLine("\nHere is the status of all the pets at The Doctor's Shelter for Pets: ");
			writeLine("\nName\t|Mood\t|Health\t|Hunger\t|Thirst\t|OilLvl\t|CageMess");
			writeLine("--------|-------|-------|-------|-------|-------|-------");
			for (OrganicPet currentPet : shelter.organicCats()) {
				writeLine(currentPet.getName() + "\t|" + ((VirtualPet) currentPet).getMood() + "\t|"
						+ ((VirtualPet) currentPet).getHealth() + "\t|" + currentPet.getHunger() + "\t|"
						+ currentPet.getThirst() + "\t|" + "n/a" + "\t|" + "n/a");
			}
			for (OrganicPet currentPet : shelter.organicDogs()) {
				writeLine(currentPet.getName() + "\t|" + ((VirtualPet) currentPet).getMood() + "\t|"
						+ ((VirtualPet) currentPet).getHealth() + "\t|" + currentPet.getHunger() + "\t|"
						+ currentPet.getThirst() + "\t|" + "n/a" + "\t|" + ((OrgDog) currentPet).getCageMessiness());
			}
			for (RoboPet currentPet : shelter.roboticDogs()) {
				writeLine(currentPet.getName() + "\t|" + ((VirtualPet) currentPet).getMood() + "\t|"
						+ ((VirtualPet) currentPet).getHealth() + "\t|" + "n/a" + "\t|" + "n/a" + "\t|"
						+ currentPet.getOilLevel() + "\t|" + "n/a");
			}
			for (RoboPet currentPet : shelter.roboticCats()) {
				writeLine(currentPet.getName() + "|" + ((VirtualPet) currentPet).getMood() + "\t|"
						+ ((VirtualPet) currentPet).getHealth() + "\t|" + "n/a" + "\t|" + "n/a" + "\t|"
						+ currentPet.getOilLevel() + "\t|" + "n/a");
			}

			writeLine("\nThe litterbox is: " + shelter.getLitterBox());
			writeLine("\nWhat would you like to do next?");
			writeLine("\n1.Feed the organic pets \n2.Water the organic pets "
					+ "\n3.Play with a pet \n4.Adopt a pet \n5.Admit a pet "
					+ "\n6.Clean Cages \n7.Clean Litterbox \n8.Walk Dogs "
					+ "\n9.Maintain all RoboPets \n10.Do nothing \n11.Quit");
			String response = input.nextLine();

			switch (response) {
			case "1":
				shelter.feedAllOrganic();
				writeLine("You fed all the companion pets for the Doctor. Everyone appreciates it!");
				break;
			case "2":
				shelter.waterAllOrganic();
				writeLine("You watered all the companion pets for the Doctor. Everyone appreciates it!");
				break;
			case "3": // play
				writeLine("You want to play with one of the companion pets? Which one would you like to play with:\n");
				displayPets(shelter);
				writeLine("\nWhich pet would you like to play with? They are all good choices.");
				String petName = input.nextLine();
				shelter.playOne(shelter.getPet(petName));
				writeLine("You played with " + shelter.getPet(petName) + "! ");
				break;
			case "4": // adopt
				writeLine("You want to adopt a companion pet? Allons-y!\n");
				displayPets(shelter);
				writeLine("\nWhich pet would you like to adopt? They are all pretty awesome.");
				String petAdopt = input.nextLine();
				VirtualPet a = shelter.getPet(petAdopt);
				shelter.adoptPet(a);
				writeLine("You adopted " + petAdopt + ". Please take good care of them!");
				break;
			case "5":// intake
				writeLine("You want to give us a new pet? We will always make room for more companions."
						+ "Is it organic or robotic?");
				String response2 = input.next();
				if (response2.equalsIgnoreCase("organic")) {
					writeLine("Is your organic pet a cat or a dog?");
					String response3 = input.next();
					input.nextLine();
					if (response3.equalsIgnoreCase("dog")) {
						writeLine("What is the dog's name?");
						String name = input.nextLine();
						writeLine("Tell us more about the dog!");
						String description = input.nextLine();
						OrganicPet pet = new OrgDog(name, description);
						shelter.intake(pet);
						writeLine("Fantastic! We'll take good care of " + pet.getName() + ".");
					} else if (response3.equalsIgnoreCase("cat")) {
						writeLine("What is the cat's name?");
						String name = input.nextLine();

						writeLine("Tell us more about the cat!");
						String description = input.nextLine();
						OrganicPet pet = new OrgCat(name, description);
						shelter.intake(pet);
						writeLine("Fantastic! We'll take good care of " + pet.getName() + ".");
					}

				} else if (response2.equalsIgnoreCase("robotic")) {
					writeLine("Is your robotic pet a cat or a dog?");
					String response3 = input.next();
					input.nextLine();

					if (response3.equalsIgnoreCase("dog")) {
						writeLine("What is the dog's name?");
						String name = input.nextLine();
						writeLine("How about a short description of the dog");
						String description = input.nextLine();
						RoboPet pet = new RoboDog(name, description);
						shelter.intake(pet);
						writeLine("Affirmitive! We'll take good care of " + pet.getName() + ".");

					} else if (response3.equalsIgnoreCase("cat")) {
						writeLine("What is the cat's name?");
						String name = input.nextLine();
						writeLine("How about a short description of the cat?");
						String description = input.nextLine();
						RoboPet pet = new RoboCat(name, description);
						shelter.intake(pet);
						writeLine("Affirmitive! We'll take good care of " + pet.getName() + ".");
					}
				}
				break;
			case "6":
				shelter.cleanDogCages();
				writeLine("You clean all the dog cages! The companion dogs sure do appreciate it.");
				break;
			case "7":
				shelter.cleanLitterBox();
				writeLine("You cleaned the litterbox! The companion cats sure do appreciate it. ");
				break;
			case "8":
				shelter.walkDogs();
				writeLine("You took all the companion dogs on walk! They are much happier now.");
				break;
			case "9":
				shelter.maintainAllRobo();
				writeLine("You gave all the robotic pets some oil! They feel much better now.");
				break;
			case "10":
				// tick
				break;
			case "11":
				writeLine("Thanks for volunteering and hope to see you again soon!");
				System.exit(0);
			default:
				writeLine("Sorry, does not compute. Please try again.");
				break;

			}
			shelter.tickAllPets();

		} while (!quit);
		input.close();

	}

	private static void displayPets(VirtualPetShelter shelter) {
		for (VirtualPet currentPet : shelter.pets()) {
			writeLine("" + currentPet);
		}
	}

	public static void writeLine(String message) {
		System.out.println(message);
	}

}
