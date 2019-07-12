package com.hyper1423.somestuff.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Reference {
	public static final String MOD_ID = "somestuff";
	public static final String NAME = "Some Stuff";
	public static final String VERSION = "0.0.1";
	public static final String ACCEPTED_VERSIONS = "(1.12.2)";
	public static final String CLIENT_PROXY_CLASS = "com.hyper1423.somestuff.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "com.hyper1423.somestuff.proxy.ServerProxy";
	
	public static final int GUI_BLAST_FURNACE = 0;
	public static final int GUI_ALLOYING_MACHINE = 1;
	
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
}
