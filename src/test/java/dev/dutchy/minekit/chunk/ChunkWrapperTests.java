package dev.dutchy.minekit.chunk;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class ChunkWrapperTests
{

    @Test
    public void constructor()
    {
        Chunk chunk = Mockito.mock(Chunk.class);
        World world = Mockito.mock(World.class);

        Mockito.when(chunk.getWorld()).thenReturn(world);
        Mockito.when(chunk.getX()).thenReturn(0);
        Mockito.when(chunk.getZ()).thenReturn(0);

        ChunkWrapper wrapper = new ChunkWrapper(chunk);

        assertEquals(world, wrapper.getWorld());
        assertEquals(0, wrapper.getX());
        assertEquals(0, wrapper.getZ());
        assertEquals(chunk, wrapper.getChunk());
    }

    @Test
    public void equals()
    {
        Chunk chunk = Mockito.mock(Chunk.class);
        World world = Mockito.mock(World.class);

        Mockito.when(chunk.getWorld()).thenReturn(world);
        Mockito.when(chunk.getX()).thenReturn(0);
        Mockito.when(chunk.getZ()).thenReturn(0);

        ChunkWrapper wrapper = new ChunkWrapper(chunk);

        assertEquals(new ChunkWrapper(chunk), wrapper);
        assertNotEquals(new ChunkWrapper(world, 1, 0), wrapper);
        assertNotEquals(new ChunkWrapper(world, 0, 1), wrapper);
        assertNotEquals(new ChunkWrapper(world, 1, 1), wrapper);
        assertNotEquals(new Object(), wrapper);
    }

    @Test
    public void adjacentChunks()
    {
        Chunk chunk = Mockito.mock(Chunk.class);
        World world = Mockito.mock(World.class);

        Mockito.when(chunk.getWorld()).thenReturn(world);
        Mockito.when(chunk.getX()).thenReturn(0);
        Mockito.when(chunk.getZ()).thenReturn(0);

        ChunkWrapper wrapper = new ChunkWrapper(chunk);

        assertEquals(8, wrapper.getAdjacentChunks().size());

        assertTrue(wrapper.getAdjacentChunks().contains(new ChunkWrapper(world, -1, -1)));
        assertTrue(wrapper.getAdjacentChunks().contains(new ChunkWrapper(world, -1, 0)));
        assertTrue(wrapper.getAdjacentChunks().contains(new ChunkWrapper(world, -1, 1)));
        assertTrue(wrapper.getAdjacentChunks().contains(new ChunkWrapper(world, 0, -1)));
        assertTrue(wrapper.getAdjacentChunks().contains(new ChunkWrapper(world, 0, 1)));
        assertTrue(wrapper.getAdjacentChunks().contains(new ChunkWrapper(world, 1, -1)));
        assertTrue(wrapper.getAdjacentChunks().contains(new ChunkWrapper(world, 1, 0)));
        assertTrue(wrapper.getAdjacentChunks().contains(new ChunkWrapper(world, 1, 1)));
    }
}
