package dev.albedianvorce.rustforge;

/** Optional JNI boundary. Minecraft must remain usable without the native library. */
public final class NativeBridge {
    private static final boolean AVAILABLE;

    static {
        boolean loaded;
        try {
            System.loadLibrary("rustforge_native");
            loaded = true;
        } catch (LinkageError | SecurityException exception) {
            loaded = false;
        }
        AVAILABLE = loaded;
    }

    private NativeBridge() {}

    public static boolean isAvailable() {
        return AVAILABLE;
    }

    public static String version() {
        if (!AVAILABLE) return "unavailable";
        try {
            return nativeVersion();
        } catch (LinkageError exception) {
            return "incompatible";
        }
    }

    private static native String nativeVersion();
}
