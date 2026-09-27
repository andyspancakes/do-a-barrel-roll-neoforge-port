package nl.enjarai.doabarrelroll.compat;

//? if fabric {
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.Version;
import net.fabricmc.loader.api.VersionParsingException;
//?} else {
/*import net.neoforged.fml.ModList;
*///?}

public class Compat {
    public static final String YACL_MIN_VERSION = "3.1.0";

    public static boolean isYACLLoaded() {
        return checkModLoaded("yet_another_config_lib_v3");
    }

    public static boolean isYACLUpToDate() {
        return isModVersionAtLeast("yet_another_config_lib_v3", YACL_MIN_VERSION);
    }

    public static boolean checkModLoaded(String modId) {
        //? if fabric {
        return FabricLoader.getInstance().isModLoaded(modId);
        //?} else {
        /*return ModList.get().isLoaded(modId);
        *///?}
    }

    public static boolean isModVersionAtLeast(String modId, String version) {
        //? if fabric {
        try {
            var parsed = Version.parse(version);
            return FabricLoader.getInstance().getModContainer("yet_another_config_lib_v3")
                    .filter(modContainer -> modContainer.getMetadata().getVersion().compareTo(parsed) >= 0)
                    .isPresent();
        } catch (VersionParsingException e) {
            throw new RuntimeException("Skill issue, bad version");
        }
        //?} else {
        /*return ModList.get().getModContainerById(modId)
                .map(container -> {
                    var actual = container.getModInfo().getVersion().toString();
                    return compareVersions(actual, version) >= 0;
                })
                .orElse(false);
        *///?}
    }

    //? if !fabric {
    /*private static int compareVersions(String a, String b) {
        String[] partsA = a.split("[.\\-]");
        String[] partsB = b.split("[.\\-]");
        for (int i = 0; i < Math.max(partsA.length, partsB.length); i++) {
            int numA = i < partsA.length ? parseIntSafe(partsA[i]) : 0;
            int numB = i < partsB.length ? parseIntSafe(partsB[i]) : 0;
            if (numA != numB) return numA - numB;
        }
        return 0;
    }

    private static int parseIntSafe(String s) {
        try { return Integer.parseInt(s); } catch (NumberFormatException e) { return 0; }
    }
    *///?}
}
