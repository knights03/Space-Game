package util;

public class RareItemGenerator {
	public static RareItemGenerator instance = new RareItemGenerator();
	
	private static String[] descriptorA = { "Proton", "Nuclear", "Quantum", "Nano" };
	private static String[] descriptorB = { "Theorem", "Higgs", "Turing", "Neutron" };
	private static String[] descriptorC = { "Engine", "Drive", "System", "Arc" };
	
	public RareItemGenerator() {

	}
	
	public String newItemName() {
		StringBuilder itemBuilder = new StringBuilder();
		
		itemBuilder.append(descriptorA[RandomGenerator.instance.getInt(descriptorA.length-1)]);
		itemBuilder.append(" ");
		itemBuilder.append(descriptorB[RandomGenerator.instance.getInt(descriptorB.length-1)]);
		itemBuilder.append(" ");
		itemBuilder.append(descriptorC[RandomGenerator.instance.getInt(descriptorC.length-1)]);
		
		return itemBuilder.toString();
	}
}
