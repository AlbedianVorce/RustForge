package dev.albedianvorce.rustforge;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class RustForge implements ModInitializer {
    public static final String MOD_ID = "rustforge";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Starting RustForge");
        if (NativeBridge.isAvailable()) {
            LOGGER.info("Rust native runtime detected: {}", NativeBridge.version());
        } else {
            LOGGER.info("Rust native runtime not found; continuing in Java-only mode.");
        }
    }
}
