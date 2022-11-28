package com.github.qpcrummer.elementalist.magic;

import com.github.qpcrummer.elementalist.tome.Tome;
import com.github.qpcrummer.elementalist.util.TargetEntityAccessor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Spell {
    protected final ServerPlayerEntity player;
    protected final World world;
    protected int cooldown;
    protected final String name = "";
    protected int distance;
    protected boolean noclip = false;

    public Spell(ServerPlayerEntity player, World world) {
        this.player = player;
        this.world = world;
    }

    public void castProjectile() {
        spawnCastingParticles();
    }

    public int getCooldown() {
        return cooldown;
    }

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }

    /**
     * Called every tick a spell has been cast
     */
    public void tick() {

    }

    public boolean noClip(boolean noclip) {
        return this.noclip = noclip;
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

    /**
     * Called when summoning the tracker entity
     */
    public void spawnTrackerEntity() {
        ArrowEntity entity = (ArrowEntity) EntityType.ARROW.spawnFromItemStack(player.getWorld(), ItemStack.EMPTY, player, player.getBlockPos().up(), SpawnReason.NATURAL, true, false);
        assert entity != null;
        ((TargetEntityAccessor)entity).setSpell(Tome.spell);
        //entity.setInvisible(true);
        entity.setNoClip(noclip);
        entity.setOwner(player);
        entity.setCustomName(Text.literal("Target"));
        entity.setPosition(player.getX(), player.getEyeY() - 0.25, player.getZ());
        ((TargetEntityAccessor)entity).setStartPos(entity.getPos());
        entity.setNoGravity(true);
        entity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0f, 0.5f, 1.0f);
    }
}
