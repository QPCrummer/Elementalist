package com.github.qpcrummer.elementalist.magic;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Spell {
    protected final ServerPlayerEntity player;
    protected final World world;
    protected int cooldown;
    protected final String name = "";

    public Spell(ServerPlayerEntity player, World world) {
        this.player = player;
        this.world = world;
    }

    public void castProjectile(float roll, float speed, float divergence) {
        setVelocity(roll, speed, divergence);
        spawnCastingParticles();
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setVelocity(float roll, float speed, float divergence) {

    }

    public String getName() {
        return name;
    }

    /**
     * Called when the spell hits a block
     * @param result the block hit result
     * @return true if the spell should be destroyed
     */
    public boolean onHitBlock(final BlockHitResult result) {
        spawnBlockImpactParticle(result);
        return true;
    }

    /**
     * Called when the spell hits an entity
     * @param result the entity hit result
     * @return true if the spell should be destroyed
     */
    public boolean onHitEntity(final EntityHitResult result) {
        spawnEntityImpactParticle(result);
        return true;
    }

    /**
     * Called while the player is casting the spell
     */
    public void spawnCastingParticles() {
        // nothing
    }

    /**
     * Called every tick to spawn particles at the spell position
     * @param position the spell position
     */
    public void spawnTrailParticle(final Vec3d position) {
        // nothing
    }

    /**
     * Called when the spell hits a block
     * @param result the block hit result
     */
    public void spawnBlockImpactParticle(final BlockHitResult result) {
        // nothing
    }

    /**
     * Called when the spell hits an entity
     * @param result the entity hit result
     */
    public void spawnEntityImpactParticle(final EntityHitResult result) {
        // nothing
    }
}
