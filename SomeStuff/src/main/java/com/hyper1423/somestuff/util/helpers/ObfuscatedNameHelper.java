package com.hyper1423.somestuff.util.helpers;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import net.minecraft.client.gui.Gui;
import net.minecraft.launchwrapper.Launch;

/**
 * A class to search obfuscated name.<br/>
 * <i><span style="color:#999">Ignores whether input is static thing or not</span></i>
 */
public class ObfuscatedNameHelper {
	
	private static final Table<String, Class, String> rawFieldList = HashBasedTable.<String, Class, String>create();
	private static final Table<String, Class, String> rawMethodList = HashBasedTable.<String, Class, String>create();
	private static final Map<String, String> rawClassList = Maps.newHashMap();
	private Gui gui = new Gui();

	private static final boolean isDeObf = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");

	static {
		// Field Lists
		rawFieldList.put("zLevel", Gui.class, "field_73735_i");
		// Method Lists

		// Class Lists
	}

	private ObfuscatedNameHelper() {

	}
	
	/**
	 * This method helps you get obfuscated names of fields.
	 * 
	 * @param deObf String you want to obfuscate
	 * @param whichClass Where to get field
	 * @return If it is an obfuscated environment, returns obfuscated name. When not, returns <b>deObf</b>.
	 */
	public static String getFieldObfName(String deObf, Class whichClass) {
		if (!isDeObf)
			if (rawFieldList.containsRow(deObf)) {
				for (Entry<String, Map<Class, String>> rowEntry : rawFieldList.rowMap().entrySet()) {
					if (rowEntry.getKey().equals(deObf)) {
						for (Entry<Class, String> colEntry : rowEntry.getValue().entrySet()) {
							if (colEntry.getKey().equals(whichClass)) {
								return colEntry.getValue();
							}
						}
					}
				}
			}
		return deObf;
	}

	public static String getMethodObfName(String deObf, Class whichClass) {
		if (!isDeObf)
			if (rawMethodList.containsRow(deObf)) {
				for (Entry<String, Map<Class, String>> rowEntry : rawMethodList.rowMap().entrySet()) {
					if (rowEntry.getKey().equals(deObf)) {
						for (Entry<Class, String> colEntry : rowEntry.getValue().entrySet()) {
							if (colEntry.getKey().equals(whichClass)) {
								return colEntry.getValue();
							}
						}
					}
				}
			}
		return deObf;
	}

	public static String getClassObfName(String deObf) {
		if (!isDeObf)
			if (rawClassList.containsKey(deObf)) {
				for (Entry<String, String> entry : rawClassList.entrySet()) {
					if (entry.getKey().equals(deObf))
						return entry.getValue();
				}
			}
		return deObf;
	}
	
	public static void addNewFieldName(String deObf, Class whichClass, String obf) {
		if (rawFieldList.contains(deObf, whichClass)) throw new IllegalArgumentException("Already existing field");
		rawFieldList.put(deObf, whichClass, obf);
	}
}
