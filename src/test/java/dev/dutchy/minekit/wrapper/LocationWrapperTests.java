package dev.dutchy.minekit.wrapper;

import org.bukkit.Location;
import org.bukkit.World;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

public class LocationWrapperTests
{

    @Test
    public void constructor()
    {
        Location location = Mockito.mock(Location.class);
        World world = Mockito.mock(World.class);

        when(location.getWorld()).thenReturn(world);
        when(location.getX()).thenReturn(100.0);
        when(location.getY()).thenReturn(100.0);
        when(location.getZ()).thenReturn(100.0);
        when(location.getPitch()).thenReturn(150.0f);
        when(location.getYaw()).thenReturn(120.0f);
        when(location.getWorld()).thenReturn(world);

        LocationWrapper wrapper = new LocationWrapper(location);

        assertEquals(world, wrapper.getWorld());
        assertEquals(100.0, wrapper.getX());
        assertEquals(100.0, wrapper.getY());
        assertEquals(100.0, wrapper.getZ());
        assertEquals(150.0f, wrapper.getPitch());
        assertEquals(120.0f, wrapper.getYaw());
    }

    @Test
    public void equals() {
        Location location = Mockito.mock(Location.class);
        World world = Mockito.mock(World.class);

        when(location.getWorld()).thenReturn(world);
        when(location.getX()).thenReturn(100.0);
        when(location.getY()).thenReturn(100.0);
        when(location.getZ()).thenReturn(100.0);
        when(location.getPitch()).thenReturn(150.0f);
        when(location.getYaw()).thenReturn(120.0f);
        when(location.getWorld()).thenReturn(world);

        LocationWrapper wrapper = new LocationWrapper(location);

        assertEquals(new LocationWrapper(location), wrapper);
        assertEquals(new LocationWrapper(world, 100.0, 100.0, 100.0, 150.0f, 120.0f), wrapper);
        assertNotEquals(new LocationWrapper(world, 100.0, 100.0, 100.0, 150.0f, 121.0f), wrapper);
        assertNotEquals(new LocationWrapper(world, 100.0, 100.0, 100.0, 151.0f, 120.0f), wrapper);
        assertNotEquals(new LocationWrapper(world, 100.0, 100.0, 101.0, 150.0f, 120.0f), wrapper);
        assertNotEquals(new LocationWrapper(world, 100.0, 101.0, 100.0, 150.0f, 120.0f), wrapper);
        assertNotEquals(new LocationWrapper(world, 101.0, 100.0, 100.0, 150.0f, 120.0f), wrapper);
        assertNotEquals(new Object(), wrapper);
    }
}
