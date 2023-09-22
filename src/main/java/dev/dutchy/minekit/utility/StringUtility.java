package dev.dutchy.minekit.utility;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class StringUtility
{

    private static final Set<Character> PUNCTUATION = Set.of(',', '.', '!', '?', ';', ':');

    private StringUtility()
    {
        throw new UnsupportedOperationException("Cannot instantiate a utility class.");
    }


    /**
     * Wraps a given string input to ensure that each line does not exceed the specified maximum length.
     * The method also tries to keep lines uniformly filled by moving words between lines while respecting
     * punctuation rules. Words that individually exceed the maximum length are placed on their own line.
     *
     * @param input      The string input to be wrapped.
     * @param maxLength  The maximum allowed length for each line.
     * @return           The wrapped string where each line does not exceed the maxLength,
     *                   and words are not split between lines.
     * @throws IllegalArgumentException If the input is null or maxLength is less than or equal to zero.
     */
    @NotNull
    public static String wrap(@NotNull String input, int maxLength)
    {

        if (maxLength <= 0)
        {
            throw new IllegalArgumentException("Invalid input or length");
        }

        String[] words = input.split("\\s+");
        StringBuilder currentLine = new StringBuilder();
        List<StringBuilder> lines = new ArrayList<>();

        for (String word : words)
        {
            // Handle scenarios where a word alone exceeds the maxLength.
            if (word.length() > maxLength)
            {
                if (!currentLine.isEmpty())
                {
                    lines.add(currentLine);
                    currentLine = new StringBuilder();
                }
                lines.add(new StringBuilder(word));
                continue;
            }

            // Check if adding the word to the current line exceeds the maxLength.
            if (currentLine.length() + word.length() + 1 > maxLength)
            {
                lines.add(currentLine);
                currentLine = new StringBuilder();
            }

            currentLine.append(word).append(" ");
        }

        if (!currentLine.isEmpty())
        {
            lines.add(currentLine);
        }

        // Adjust lines for uniformity
        for (int i = 0; i < lines.size() - 1; i++)
        {
            boolean wordMoved = false;
            while (lines.get(i).length() < maxLength && !lines.get(i + 1).isEmpty())
            {
                // Check if we can move a word from the next line to the current line.
                String[] nextLineWords = lines.get(i + 1).toString().split("\\s+", 2);

                // Ensure punctuation remains attached to its word when moving.
                boolean canMove = !PUNCTUATION.contains(nextLineWords[0].charAt(0));
                if (canMove && lines.get(i).length() + nextLineWords[0].length() + 1 <= maxLength)
                {
                    lines.get(i).append(" ").append(nextLineWords[0]);

                    // Update the next line after moving the word.
                    if (nextLineWords.length > 1)
                    {
                        lines.get(i + 1).replace(0, nextLineWords[0].length() + 1, nextLineWords[1]);
                    }
                    else
                    {
                        lines.get(i + 1).setLength(0);
                    }

                    wordMoved = true;
                }
                else
                {
                    break;
                }
            }
            if (wordMoved) i--; // Recheck the same line
        }

        // Construct the final result.
        StringBuilder result = new StringBuilder();

        for (StringBuilder line : lines)
        {
            result.append(line.toString().trim()).append("\n");
        }

        return result.toString().trim();  // Remove trailing newline
    }
}
