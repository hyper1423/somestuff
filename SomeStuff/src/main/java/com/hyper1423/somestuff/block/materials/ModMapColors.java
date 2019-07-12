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
	private static final Logger LOGGER = LogManager.getLogger(Reference.MOD_ID);

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
			LOGGER.error("Cannot find constructor of class MapColor");
			e.printStackTrace();
		} catch (SecurityException e) {
			LOGGER.error("Could not access to the constructor");
			e.printStackTrace();
		} catch (InstantiationException e) {
			LOGGER.error("The class is abstract");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			LOGGER.error("Cannot access to class");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			LOGGER.error("Constructor arguments do not match");
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.getTargetException().printStackTrace();
		} catch (NoSuchFieldException e) {
			LOGGER.error("Cannot find field of class MapColor");
			e.printStackTrace();
		} catch (NullPointerException e) {
			LOGGER.error("N u l l . \0" + null + null);
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			LOGGER.error("Index is out of bounds.");
			e.printStackTrace();
		}
		return result;
	}
}
