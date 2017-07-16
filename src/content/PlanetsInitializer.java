package content;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.TextLoader;
import content.objects.Planet;

public class PlanetsInitializer {

	TextLoader dataLoader;
	List<Planet> planets;

	protected PlanetsInitializer(String path) {
		dataLoader = new TextLoader("data");
		planets = new ArrayList<Planet>();
		init();
	}

	private void init() {
		String loaded = dataLoader.readTextFromFile("planets.gd").trim();
		// TODO: instantiate Planet in the way described in the data file using
		// for loop [x|y|width|path]
		int c = 0;
		int i = 0;
		String[] token = new String[4];
		token[0] = "";
		token[1] = "";
		token[2] = "";
		token[3] = "";
		while (c < loaded.length()) {
			if (loaded.charAt(c) == '[') {
				i = 0;
			} else if (loaded.charAt(c) == '|') {
				i++;
			} else if (loaded.charAt(c) == ']') {
				try	{
					planets.add(new Planet(Float.valueOf(token[0].trim()), Float
							.valueOf(token[1].trim()), Integer.valueOf(token[2].trim()), token[3].trim()));
				} catch(Exception ex)	{
					System.out.println("Error loading planets data. Created planets: " + planets.size());
					break;
				}
				token[0] = "";
				token[1] = "";
				token[2] = "";
				token[3] = "";
			} else {
				token[i] += loaded.charAt(c);
			}
			c++;
		}
		for(Planet p : planets)	{
			p.init();
		}
		System.out.println( planets.size());
	}

	public void update() {
		for(Planet p : planets)	{
			p.update();
		}
	}

	public void draw() {
		for(Planet p : planets)	{
			p.draw();
		}
	}

}
