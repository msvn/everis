package com.codegenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
// Velocity
import org.apache.velocity.app.Velocity;

public class ClassGenerator {

	private static ArrayList classes;
	private static ClassDescriptorImporter cdImporter;

	static void init(String modelFile,String language) throws Exception {
		// Set the ClassDescriptorImporter as hadler
		cdImporter = new ClassDescriptorImporter(modelFile,language);

		// Init Velocity
		Velocity.init();
	}

	public static void start(String templateFile,String language) throws Exception {
		String dir = "code\\"+language+"\\";
		classes = cdImporter.getClasses(); // ClassDescriptor Array

		// showClasses();

		// Generate classes
		GeneratorUtility utility = new GeneratorUtility();
		for (int i = 0; i < classes.size(); i++) {

			VelocityContext context = new VelocityContext();
			ClassDescriptor cl = (ClassDescriptor) classes.get(i);
			context.put("class", cl);
			context.put("utility", utility);

			Template template = Velocity.getTemplate(templateFile);

			BufferedWriter writer = new BufferedWriter(new FileWriter(dir + cl.getName() + "."+language.toLowerCase()));

			template.merge(context, writer);
			writer.flush();
			writer.close();

			System.out.println("Class " + cl.getName() + " generated!");
			
//			break;
		}

	}

	private static void showClasses() {

		for (int i = 0; i < classes.size(); i++) {
			ClassDescriptor cl = (ClassDescriptor) classes.get(i);
			System.out.println(cl.getName());
			ArrayList attrs = cl.getAttributes();
			for (int j = 0; j < attrs.size(); j++) {
				AttributeDescriptor at = (AttributeDescriptor) attrs.get(j);
				System.out.print("\t" + at.getType());
				System.out.println(" " + at.getName());
			}
		}
	}

	public static void main(String args[]) throws Exception {

		if (args.length != 3) {
			System.out.print("Syntax: ClassGenerator <modelFile> <template> <language>");
			System.exit(1);
		}

		String modelFile = args[0];
		String templateFile = args[1];
		String language = args[2];

		ClassGenerator.init(modelFile,language);
		ClassGenerator.start(templateFile,language);

	}

}