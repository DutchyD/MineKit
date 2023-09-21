package dev.dutchy.minekit.wrapper;

import com.google.common.base.Preconditions;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


/**
 *
 * A wrapper class for the Minecraft {@link Location} class.
 * This class is immutable.
 *
 */
public final class LocationWrapper
{

    private final World world;
    private final double x;
    private final double y;
    private final double z;
    private final float pitch;
    private final float yaw;

    /**
     *
     * Creates a new LocationWrapper instance.
     *
     * @param world The Minecraft World the location resides in.
     * @param x The X coordinate of the location.
     * @param y The Y coordinate of the location.
     * @param z The Z coordinate of the location.
     * @param pitch The pitch of the location.
     * @param yaw The yaw of the location.
     *
     * @throws NullPointerException If the {@link World} is null.
     */
    public LocationWrapper(@NotNull World world, double x, double y, double z, float pitch, float yaw)
    {
        this.world = Preconditions.checkNotNull(world);
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Creates a new LocationWrapper instance.
     *
     * @param location The Minecraft Location that will represent this LocationWrapper.
     *
     * @throws NullPointerException If the {@link Location} or {@link Location#getWorld()} is null.
     */
    public LocationWrapper(@NotNull Location location)
    {
        this(Preconditions.checkNotNull(location.getWorld()), location.getX(), location.getY(), location.getZ(), location.getPitch(), location.getYaw());
    }

    /**
     *
     * Convenience method.
     * Creates a new LocationWrapper instance.
     *
     * @param location The Minecraft Location that will represent this LocationWrapper.
     *
     * @return The created LocationWrapper instance.
     *
     * @throws NullPointerException If the {@link Location} or {@link Location#getWorld()} is null.
     */
    @NotNull
    @Contract("_ -> new")
    public static LocationWrapper wrap(@NotNull Location location)
    {
        return new LocationWrapper(location);
    }


    /**
     *
     * Convenience method.
     * Creates a new LocationWrapper instance.
     * This method is equivalent to calling {@link #wrap(Location)} with
     * {@link Entity#getLocation()} as the parameter.
     *
     * @param entity The entity.
     *
     * @return The created LocationWrapper instance.
     *
     * @throws NullPointerException If the {@link Entity}, {@link Entity#getLocation()} or {@link Entity#getLocation()#getWorld()} is null.
     */
    @NotNull
    @Contract("_ -> new")
    public static LocationWrapper wrap(@NotNull Entity entity)
    {
        return new LocationWrapper(entity.getLocation());
    }

    /**
     *
     * Creates a new {@link Location} that this LocationWrapper represents.
     *
     * @return The created Location.
     */
    @NotNull
    @Contract(value = " -> new", pure = true)
    public Location getLocation()
    {
        return new Location(this.world, this.x, this.y, this.z, this.pitch, this.yaw);
    }

    /**
     *
     * Gets the {@link World} that this LocationWrapper represents.
     *
     * @return The World.
     */
    @NotNull
    public World getWorld()
    {
        return this.world;
    }

    /**
     *
     * Gets the X coordinate of the location.
     *
     * @return The X coordinate.
     */
    public double getX()
    {
        return this.x;
    }

    /**
     *
     * Gets the Y coordinate of the location.
     *
     * @return The Y coordinate.
     */
    public double getY()
    {
        return this.y;
    }

    /**
     *
     * Gets the Z coordinate of the location.
     *
     * @return The Z coordinate.
     */
    public double getZ()
    {
        return this.z;
    }

    /**
     *
     * Gets the pitch of the location.
     *
     * @return The pitch.
     */
    public float getPitch()
    {
        return this.pitch;
    }

    /**
     *
     * Gets the yaw of the location.
     *
     * @return The yaw.
     */
    public float getYaw()
    {
        return this.yaw;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationWrapper that = (LocationWrapper) o;
        return Double.compare(that.getX(), this.getX()) == 0 && Double.compare(that.getY(), this.getY()) == 0 && Double.compare(that.getZ(), this.getZ()) == 0 && Float.compare(that.getPitch(), this.getPitch()) == 0 && Float.compare(that.getYaw(), this.getYaw()) == 0 && Objects.equals(this.getWorld(), that.getWorld());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.getWorld(), this.getX(), this.getY(), this.getZ(), this.getPitch(), this.getYaw());
    }

    @Override
    public String toString()
    {
        return "LocationWrapper{" +
                "world=" + world +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", pitch=" + pitch +
                ", yaw=" + yaw +
                '}';
    }
}
