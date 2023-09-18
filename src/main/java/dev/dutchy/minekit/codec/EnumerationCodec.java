package dev.dutchy.minekit.codec;

import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;

public class EnumerationCodec
{

    private EnumerationCodec()
    {
        throw new UnsupportedOperationException("Cannot instantiate a utility class.");
    }

    /**
     *
     * Encodes an EnumSet into an integer.
     *
     * @param set The EnumSet to encode.
     * @return The encoded EnumSet.
     * @param <E> The type of the Enum.
     */
    public static <E extends Enum<E>> long encode(@NotNull EnumSet<E> set)
    {
        long encodedValue = 0L;

        for (E val : set) {
            encodedValue |= 1L << val.ordinal();
        }

        return encodedValue;
    }


    /**
     *
     * Decodes an integer into an EnumSet.
     *
     * @param code The integer to decode.
     * @param type The type of the Enum.
     * @return The decoded EnumSet.
     * @param <E> The type of the Enum.
     */
    @NotNull
    public static <E extends Enum<E>> EnumSet<E> decode(long code, @NotNull Class<E> type)
    {
        E[] enums = type.getEnumConstants();
        EnumSet<E> result = EnumSet.noneOf(type);

        for (E e : enums) {
            long bitMask = 1L << e.ordinal();
            if ((code & bitMask) != 0) {
                result.add(e);
            }
        }

        return result;
    }

}
