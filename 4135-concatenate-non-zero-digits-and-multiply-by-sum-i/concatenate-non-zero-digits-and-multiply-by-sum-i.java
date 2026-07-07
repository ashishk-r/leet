class Solution {
    public long sumAndMultiply(int n) {
        if (n == 0) {
            return 0;
        }

        long x = 0;
        long sum = 0;
        long multiplier = 1;

        int temp = n;
        while (temp > 0) {
            int digit = temp % 10;
            if (digit != 0) {
                x += (long) digit * multiplier;
                sum += digit;
                multiplier *= 10;
            }
            temp /= 10;
        }

        return x * sum;
    }
}
