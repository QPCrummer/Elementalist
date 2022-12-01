package com.github.qpcrummer.elementalist.magic;

import com.github.qpcrummer.elementalist.tome.Tome;
import com.github.qpcrummer.elementalist.util.TargetEntityAccessor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class Spell {
    protected final ServerPlayerEntity player;
    protected final World world;
    protected int cooldown;
    protected String name = "";
    protected int distance;
    protected int entity_count = 1;
    protected float speed = 0.65F;
    protected float divergence = 1.0F;
    protected float pitch_offset = 0.0F;
    protected float yaw_offset = 0.0F;
    protected boolean noclip = false;
    protected boolean custom_entity_summoning = false;
    protected boolean accumulating_offsets = false;

    public Spell(ServerPlayerEntity player, World world) {
        this.player = player;
        this.world = world;
    }

    public Spell(ServerPlayerEntity player, World world, String name, int cooldown, int distance) {
        this(player, world);
        this.name = name;
        this.cooldown = cooldown;
        this.distance = distance;
    }

    /**
     * If custom_entity_summoning = true, override this method to alter your summoning
     */
    public void castProjectile() {
        PersistentProjectileEntity entity;
        if (!custom_entity_summoning) {
            float offset_yaw = yaw_offset;
            float offset_pitch = pitch_offset;
            for (int i = 0; i < entity_count; i++) {
               entity = spawnTrackerEntity(pitch_offset, yaw_offset);
               spawnCastingParticles(entity);
               if (accumulating_offsets) {
                   offset_yaw = offset_yaw * 2;
                   offset_pitch = offset_pitch * 2;
               }
            }
        }
    }

    /**
     * @return the cooldown after using the spell
     */
    public int getCooldown() {
        return cooldown;
    }

    /**
     * @return the display name of the spell
     */
    public String getName() {
        return name;
    }

    /**
     * @return the maximum travel distance of the spell
     */
    public int getDistance() {
        return distance;
    }

    /**
     * @return the movement speed in blocks per tick
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * @return the inaccuracy of the spell launch angle
     */
    public float getDivergence() {
        return divergence;
    }

    /**
     * Called every tick a spell has been cast
     */
    public void tick(PersistentProjectileEntity entity) {
        spawnTrailParticle(entity);
    }

    /**
     * @return true if the entity has noclip
     */
    public boolean isNoClip() {
        return this.noclip;
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
     * @param entity the target entity
     */
    public void spawnCastingParticles(PersistentProjectileEntity entity) {
        // nothing
    }

    /**
     * Called every tick to spawn particles at the spell position
     * @param targetEntity the target entity
     */
    public void spawnTrailParticle(final PersistentProjectileEntity targetEntity) {
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
     * @param pitch_offset sets the offset for pitch; Use 0 for none
     * @param yaw_offset sets the offset for the yam; Use 0 for none
     * @return the tracker entity
     */
    public PersistentProjectileEntity spawnTrackerEntity(float pitch_offset, float yaw_offset) {
        ArrowEntity entity = (ArrowEntity) EntityType.ARROW.spawnFromItemStack(player.getWorld(), ItemStack.EMPTY, player, player.getBlockPos().up(), SpawnReason.NATURAL, true, false);
        assert entity != null;
        ((TargetEntityAccessor)entity).setSpell(Tome.spell);
        //entity.setInvisible(true);
        entity.setNoClip(isNoClip());
        entity.setOwner(player);
        entity.setCustomName(Text.literal("Target"));
        entity.setPosition(player.getX(), player.getEyeY() - 0.25, player.getZ());
        ((TargetEntityAccessor)entity).setStartPos(entity.getPos());
        entity.setNoGravity(true);
        entity.setVelocity(player, player.getPitch() + pitch_offset, player.getYaw() + yaw_offset, 0.0f, getSpeed(), getDivergence());
        return entity;
    }
}
