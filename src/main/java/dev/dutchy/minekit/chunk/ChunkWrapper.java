package dev.dutchy.minekit.chunk;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * A wrapper class for the Minecraft {@link Chunk} class.
 * This class is immutable.
 * This class is lazy loaded.
 *
 */
public final class ChunkWrapper
{

    private final World world;
    private final int x;
    private final int z;
    private Chunk chunk;

    /**
     *
     * Creates a new ChunkWrapper instance.
     *
     * @param world The Minecraft World the chunk resides in.
     * @param x The X coordinate of the chunk.
     * @param z The Z coordinate of the chunk.
     */
    public ChunkWrapper(@NotNull World world, int x, int z)
    {
        this.world = world;
        this.x = x;
        this.z = z;
        this.chunk = null; // Lazy loaded
    }

    /**
     *
     * Creates a new ChunkWrapper instance.
     *
     * @param chunk The Minecraft Chunk that will represent this ChunkWrapper.
     *
     */
    public ChunkWrapper(@NotNull Chunk chunk)
    {
        this.world = chunk.getWorld();
        this.x = chunk.getX();
        this.z = chunk.getZ();
        this.chunk = chunk;
    }

    /**
     *
     * Convenience method.
     * Just literally calls the constructor with the passed world and coordinates.
     *
     * @param world The Minecraft World the chunk resides in.
     * @param x The X coordinate of the chunk.
     * @param z The Z coordinate of the chunk.
     * @return A new ChunkWrapper instance.
     */
    @Contract(value = "_, _, _ -> new", pure = true)
    public static @NotNull ChunkWrapper of(@NotNull World world, int x, int z)
    {
        return new ChunkWrapper(world, x, z);
    }

    /**
     *
     * Convenience method.
     * Just literally calls the constructor with the passed chunk.
     *
     * @param chunk The chunk
     * @return A new ChunkWrapper instance.
     */
    @Contract("_ -> new")
    @NotNull
    public static ChunkWrapper wrap(@NotNull Chunk chunk)
    {
        return new ChunkWrapper(chunk);
    }

    /**
     *
     * Convenience method.
     * Gets the {@link Entity}'s {@link Location} and then gets the {@link Chunk} and uses
     * that to create a new ChunkWrapper.
     *
     * @param entity The entity.
     * @return A new ChunkWrapper instance.
     */
    @Contract("_ -> new")
    @NotNull
    public static ChunkWrapper wrap(@NotNull Entity entity)
    {
        return new ChunkWrapper(entity.getLocation().getChunk());
    }

    /**
     *
     * Convenience method.
     * Gets the {@link Block}'s {@link Chunk} and uses
     * that to create a new ChunkWrapper.
     *
     * @param block The block.
     * @return A new ChunkWrapper instance.
     */
    @Contract("_ -> new")
    @NotNull
    public static ChunkWrapper wrap(@NotNull Block block)
    {
        return new ChunkWrapper(block.getChunk());
    }

    /**
     *
     * Convenience method.
     * Gets the {@link Location}'s {@link Chunk} and uses
     * that to create a new ChunkWrapper.
     *
     * @param location The location.
     * @return A new ChunkWrapper instance.
     */
    @Contract("_ -> new")
    @NotNull
    public static ChunkWrapper wrap(@NotNull Location location)
    {
        return new ChunkWrapper(location.getChunk());
    }

    /**
     *
     * Will return the Minecraft {@link World} the chunk resides in.
     *
     * @return the Minecraft {@link World}.
     */
    @NotNull
    public World getWorld()
    {
        return this.world;
    }

    /**
     *
     * Returns the X coordinate of the {@link Chunk}.
     *
     * @return The X coordinate.
     */
    public int getX()
    {
        return this.x;
    }

    /**
     *
     * Returns the Z coordinate of the {@link Chunk}.
     *
     * @return The Z coordinate.
     */
    public int getZ()
    {
        return this.z;
    }

    /**
     *
     * Returns the wrapped Minecraft {@link Chunk} object.
     * Depending on which constructor you've used, might call {@link World#getChunkAt(int, int)} the first time,
     * which is a pretty demanding task.
     *
     * @return The wrapped {@link Chunk} object
     */
    @NotNull
    public Chunk getChunk()
    {
        if (this.chunk == null)
            this.chunk = this.getWorld().getChunkAt(this.x , this.z);

        return this.chunk;
    }

    /**
     *
     * Returns whether the wrapped {@link Chunk} is loaded.
     *
     * @return True if the {@link Chunk} is loaded, otherwise false.
     */
    public boolean isLoaded()
    {
        return this.getChunk().isLoaded();
    }

    /**
     *
     * Attempts to load the wrapped {@link Chunk}
     *
     * @return True if the {@link Chunk} is successfully loaded, otherwise false.
     */
    public boolean load()
    {
        return this.getChunk().load();
    }

    /**
     *
     * Attempts to unload the wrapped {@link Chunk}
     *
     * @return True if the {@link Chunk} is successfully unloaded, otherwise false.
     */
    public boolean unload()
    {
        return this.getChunk().unload();
    }

    /**
     *
     * Returns a List of all the {@link Entity}'s inside of the {@link Chunk}.
     *
     * @return A list of all {@link Entity}'s inside of the {@link Chunk}.
     */
    @NotNull
    @UnmodifiableView
    public List<Entity> getEntities()
    {
        return List.of(this.getChunk().getEntities());
    }

    /**
     *
     * Returns a List of all the {@link Player}'s inside of the {@link Chunk}.
     * Convenience Method.
     *
     * @return A list of all {@link Player}'s inside of the {@link Chunk}.
     */
    @NotNull
    @UnmodifiableView
    public List<Player> getPlayers()
    {
        return this.getEntities()
                .stream()
                .filter(entity -> entity instanceof Player)
                .map(entity -> (Player) entity)
                .toList();
    }

    /**
     *
     * Returns the 8 ChunkWrappers adjacent to this ChunkWrapper.
     * The list doesn't include this ChunkWrapper, only the ones adjacent to it.
     * Note: These ChunkWrappers are all lazily loaded and calling the {@link #getChunk()}
     * method for the first time will call {@link World#getChunkAt(int, int)}.
     *
     * @return The 8 adjacent ChunkWrappers.
     */
    @NotNull
    @UnmodifiableView
    public List<ChunkWrapper> getAdjacentChunks()
    {
        List<ChunkWrapper> adjacent = new ArrayList<>(8);
        World world = this.getWorld();
        int x = this.getX();
        int z = this.getZ();

        int[] xOffsetArray = {-1, 0, 1, -1, 1, -1, 0, 1};
        int[] zOffsetArray = {-1, -1, -1, 0, 0, 1, 1, 1};

        for (int i = 0; i < 8; i++)
        {
            int surroundingX = x + xOffsetArray[i];
            int surroundingZ = z + zOffsetArray[i];

            ChunkWrapper wrapper = new ChunkWrapper(world, surroundingX, surroundingZ);
            adjacent.add(wrapper);
        }

        return Collections.unmodifiableList(adjacent);
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof ChunkWrapper that)) return false;
        return this.getX() == that.getX() && this.getZ() == that.getZ() && Objects.equals(this.getWorld(), that.getWorld());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.getWorld(), this.getX(), this.getZ());
    }

    @Override
    public String toString()
    {
        return "ChunkWrapper{" +
                "world=" + this.getWorld() +
                ", x=" + this.getX() +
                ", z=" + this.getZ() +
                '}';
    }
}