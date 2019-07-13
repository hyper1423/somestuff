package com.hyper1423.somestuff.block.materials;

import java.lang.reflect.*;
import java.lang.reflect.InvocationTargetException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.LogManager;

import com.hyper1423.somestuff.util.IHasModel;
import com.hyper1423.somestuff.util.Reference;

import net.minecraft.block.material.MapColor;

public class ModMapColors {
	public static final MapColor MOLTEN_BRONZE = createNewMapColor(0xff6600);
	public static final MapColor MOLTEN_IRON = createNewMapColor(0x993333);
	
	/**
	 * Returns a new MapColor using Reflection. Returns null when an exception is caught.
	 * @param color Color
	 * @return Custom MapColor
	 */
	public static MapColor createNewMapColor(int color) {
		Class cls;
		Constructor<MapColor> constructor;
		Field field;
		int index = -1;
		MapColor result = null;
		try {
			cls = MapColor.class;
			field = cls.getDeclaredField("COLORS");
			field.setAccessible(true);
			
			MapColor[] mapColors = (MapColor[]) field.get(null);
			for (int i = 0; i < mapColors.length; i++) {
				if (mapColors[i] == null) {
					index = i;
				}
			}
			constructor = cls.getDeclaredConstructor(new Class[] { int.class, int.class });
			constructor.setAccessible(true);
			result = (MapColor)constructor.newInstance(index, color);
		} catch (NoSuchMethodException e) {
			Reference.LOGGER.error("Cannot find constructor of class MapColor");
			e.printStackTrace();
		} catch (SecurityException e) {
			Reference.LOGGER.error("Could not access to the constructor");
			e.printStackTrace();
		} catch (InstantiationException e) {
			Reference.LOGGER.error("The class is abstract");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Reference.LOGGER.error("Cannot access to class");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			Reference.LOGGER.error("Constructor arguments do not match");
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.getTargetException().printStackTrace();
		} catch (NoSuchFieldException e) {
			Reference.LOGGER.error("Cannot find field of class MapColor");
			e.printStackTrace();
		} catch (NullPointerException e) {
			Reference.LOGGER.error("N u l l . \0" + null + null);
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			Reference.LOGGER.error("Index is out of bounds.");
			e.printStackTrace();
		}
		return result;
	}
}
