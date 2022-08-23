package draylar.identity.network;

import draylar.identity.Identity;
import draylar.identity.ability.AbilityRegistry;
import draylar.identity.config.IdentityConfig;
import draylar.identity.registry.Components;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.registry.Registry;

public class ServerNetworking implements NetworkHandler {

    public static void init() {
        registerIdentityRequestPacketHandler();
        registerFavoritePacketHandler();
        registerUseAbilityPacketHandler();
    }

    private static void registerUseAbilityPacketHandler() {
        ServerPlayNetworking.registerGlobalReceiver(USE_ABILITY, (server, player, handler, buf, responseSender) -> {
            LivingEntity identity = Components.CURRENT_IDENTITY.get(player).getIdentity();

            server.execute(() -> {
                // Verify we should use ability for the player's current identity
                if(identity != null) {
                    EntityType<?> identityType = identity.getType();

                    if(AbilityRegistry.has(identityType)) {

                        // Check cooldown
                        if(Components.ABILITY.get(player).canUseAbility()) {
                            AbilityRegistry.get(identityType).onUse(player, identity, player.world);
                            Components.ABILITY.get(player).setCooldown(AbilityRegistry.get(identityType).getCooldown(identity));
                        }
                    }
                }
            });
        });
    }

    private static void registerIdentityRequestPacketHandler() {
        ServerPlayNetworking.registerGlobalReceiver(IDENTITY_REQUEST, (context, player, handler, buf, sender) -> {
            EntityType<?> type = Registry.ENTITY_TYPE.get(buf.readIdentifier());

            context.execute(() -> {
                // Ensure player has permission to switch identities
                if (IdentityConfig.enableSwaps || player.hasPermissionLevel(3)) {
                    if (type.equals(EntityType.PLAYER)) {
                        Components.CURRENT_IDENTITY.get(player).setIdentity(null);
                    } else {
                        Components.CURRENT_IDENTITY.get(player).setIdentity((LivingEntity) type.create(player.world));
                    }

                    // Refresh player dimensions
                    player.calculateDimensions();
                }
            });
        });
    }

    private static void registerFavoritePacketHandler() {
        ServerPlayNetworking.registerGlobalReceiver(FAVORITE_UPDATE, (context, player, handler, buf, sender) -> {
            EntityType<?> type = Registry.ENTITY_TYPE.get(buf.readIdentifier());
            boolean favorite = buf.readBoolean();

            context.execute(() -> {
                if(favorite) {
                    Components.FAVORITE_IDENTITIES.get(player).favorite(type);
                } else {
                    Components.FAVORITE_IDENTITIES.get(player).unfavorite(type);
                }
            });
        });
    }

    private ServerNetworking() {
        // NO-OP
    }
}
