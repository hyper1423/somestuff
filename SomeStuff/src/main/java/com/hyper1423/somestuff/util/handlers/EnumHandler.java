package com.hyper1423.somestuff.util.handlers;

import net.minecraft.util.IStringSerializable;

public class EnumHandler {
	
	public static enum CircuitTypes implements IStringSerializable {
		BASIC("basic", 0),
		ADVANCED("advanced", 1),
		HEAT("heat", 2),
		HEATPROOF("heatproof", 3);
		
		private int ID;
		private String name;
		
		private CircuitTypes(String name, int ID) {
			this.ID = ID;
			this.name = name;
		}
		
		@Override
		public String getName() {
			return null;
		}
		
		public int getID() {
			return ID;
		}
		
		@Override
		public String toString() {
			return getName();
		}
		
	}
}
