package draylar.identity.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IdentityConfig {

    
    public static boolean overlayIdentityUnlocks = true;

    
    public static boolean overlayIdentityRevokes = true;

    
    public static boolean revokeIdentityOnDeath = false;

    
    public static boolean identitiesEquipItems = true;

    
    public static boolean identitiesEquipArmor = true;

    
    public static boolean hostilesIgnoreHostileIdentityPlayer = true;

    
    public static boolean hostilesForgetNewHostileIdentityPlayer = false;

    
    public static boolean wolvesAttackIdentityPrey = true;

    
    public static boolean ownedWolvesAttackIdentityPrey = false;

    
    public static boolean villagersRunFromIdentities = true;

    
    public static boolean foxesAttackIdentityPrey = true;

    
    public static boolean useIdentitySounds = true;

    
    public static boolean playAmbientSounds = true;

    
    public static boolean hearSelfAmbient = false;

    
    public static boolean enableFlight = true;

    
    public static int hostilityTime = 20 * 15;

    
    public static List<String> advancementsRequiredForFlight = new ArrayList<>();

    
    public static boolean scalingHealth = true;

    
    public static int maxHealth = 40;
    
    public static boolean enableClientSwapMenu = true;

    
    public static boolean enableSwaps = true;

    
    public static int endermanAbilityTeleportDistance = 32;
    
    public static boolean showPlayerNametag = false;

    
    public static boolean forceChangeNew = false;

    
    public static boolean forceChangeAlways = false;

    
    public static boolean logCommands = true;

    public static float flySpeed = 0.05f;

    
    public static boolean killForIdentity = false;

    
    public static int requiredKillsForIdentity = 50;

    
    public static Map<String, Integer> requiredKillsByType = new HashMap<>() {
        {
            put("minecraft:ender_dragon", 1);
            put("minecraft:elder_guardian", 1);
            put("minecraft:wither", 1);
        }
    };

    public static Map<String, Integer> abilityCooldownMap = new HashMap<>() {
        {
            put("minecraft:ghast", 60);
            put("minecraft:blaze", 20);
            put("minecraft:ender_dragon", 20);
            put("minecraft:enderman", 100);
            put("minecraft:creeper", 100);
            put("minecraft:wither", 200);
            put("minecraft:snow_golem", 10);
            put("minecraft:witch", 200);
            put("minecraft:evoker", 10);
        }
    };
}
