public class RandomGenerator {

    static class Random {
        private int next = 1;
        // диапазон псевдослучайных чисел от -randMax до randMax
        private int randMax;

        public Random(int randMax) {
            this.randMax = randMax;
        }

        // генератор псевдослучайных чисел
        public int getNextRandom() {
            next = next * 1103515245 + 12345;
            return (next/65536) % (randMax + 1);
        }
    }

    public static void main(String[] args) {
        Random rand = new Random(6);
        for(int i=0; i<10; i++) {
            System.out.println(rand.getNextRandom());
        }

    }
}
