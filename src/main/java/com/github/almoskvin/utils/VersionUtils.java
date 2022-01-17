package com.github.almoskvin.utils;

public final class VersionUtils {

    private VersionUtils() {}

    /**
     * Compares two version strings: version1 and version2.
     *
     * If version1 > version2 returns 1
     * If version1 < version2 returns -1
     * otherwise returns 0
     *
     * You may assume that the version strings are non-empty and contain only digits and the 'dot' character. The 'dot' character does not represent a
     * decimal point and is used to separate number sequences. For instance '2.5' is not "two and a half" or "half way to version three", it is the fifth
     * second-level revision of the second first-level revision.
     * Here is an example of version numbers ordering: 0.1 < 1.1 < 1.2 < 1.2.9.9.9.9 < 1.3 < 1.3.4 < 1.10
     */
    public static int compare(String v1, String v2) {
        final int nextDot1 = v1.indexOf(".");
        final int nextDot2 = v2.indexOf(".");
        if (nextDot1 == -1 && nextDot2 == -1) { // no sub-revisions at this point
            final int num1 = Integer.parseInt(v1);
            final int num2 = Integer.parseInt(v2);
            return Integer.compare(num1, num2);
        }
        if (nextDot1 == -1) { // no sub-revision for v1
            final int num1 = Integer.parseInt(v1);
            final int num2 = Integer.parseInt(v2.substring(0, nextDot2));
            final int result = Integer.compare(num1, num2);
            return result == 0 ? -1 : result;
        }
        if (nextDot2 == -1) { // no sub-revision for v2
            final int num1 = Integer.parseInt(v1.substring(0, nextDot1));
            final int num2 = Integer.parseInt(v2);
            final int result = Integer.compare(num1, num2);
            return result == 0 ? 1 : result;
        }
        final int num1 = Integer.parseInt(v1.substring(0, nextDot1));
        final int num2 = Integer.parseInt(v2.substring(0, nextDot2));
        return num1 > num2 ? 1 : (num1 < num2 ? -1 : compare(v1.substring(nextDot1 + 1), v2.substring(nextDot2 + 1)));
    }
}
