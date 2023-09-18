package dev.dutchy.minekit.codec;

import org.junit.jupiter.api.Test;

import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumerationCodecTests
{

    @Test
    public void encode()
    {
        EnumSet<DaysOfTheWeek> days = EnumSet.of(DaysOfTheWeek.MONDAY, DaysOfTheWeek.TUESDAY, DaysOfTheWeek.FRIDAY);

        long encoded = EnumerationCodec.encode(days); // 1 + 2 + 16 = 19. So this should be 19.

        assertEquals(19, encoded);
    }

    @Test
    public void decode()
    {
        EnumSet<DaysOfTheWeek> days = EnumSet.of(DaysOfTheWeek.MONDAY, DaysOfTheWeek.TUESDAY, DaysOfTheWeek.FRIDAY);

        long encoded = EnumerationCodec.encode(days);

        EnumSet<DaysOfTheWeek> decoded = EnumerationCodec.decode(encoded, DaysOfTheWeek.class);

        assertEquals(days, decoded);
    }

    public enum DaysOfTheWeek
    {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }
}
