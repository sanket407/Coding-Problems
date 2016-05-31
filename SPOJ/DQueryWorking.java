
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String args[]) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Solver solver = new Solver();
        int testCount = 1;
        for (int i = 1; i <= testCount; i++) {
            solver.solve(in, out);
        }
        out.close();
    }

    static class Data {
        int index;
        int x;

        Data(int index, int x) {
            this.index = index;
            this.x = x;
        }

        @Override public String toString() {
            return "Data{" +
                    "index=" + index +
                    ", x=" + x +
                    '}';
        }
    }

    static class Bit {
        int tree[];
        int max;
        int pos[];

        Bit(int n) {
            max = n;
            tree = new int[max+1];
            pos = new int[1000001];
        }

        void updateTree(int idx, int val) {
            if (pos[val] != 0) {
                update(pos[val], -1);
            }
            pos[val] = idx;
            update(idx, 1);
        }

        void update(int idx ,int val){
            while (idx <= max){
                tree[idx] += val;
                idx += (idx & -idx);
            }
        }

        int read(int idx){
            int sum = 0;
            while (idx > 0){
                sum += tree[idx];
                idx -= (idx & -idx);
            }
            return sum;
        }

        @Override
        public String toString() {
            return "Bit{" +
                    "tree=" + Arrays.toString(tree) +
                    ", max=" + max +
                    ", pos=" + Arrays.toString(pos) +
                    '}';
        }
    }

    private static class Solver {

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();

            int a[] = new int[n+1];
            List<Data> ord[] = new ArrayList[n+1];
            for (int i=1; i<=n; i++) {
                a[i] = in.nextInt();
                ord[i] = new ArrayList<>();
            }

            int q = in.nextInt();

            for (int i=0; i<q; i++) {
                int l = in.nextInt();
                int r = in.nextInt();
                Data e = new Data(i, l);
                ord[r].add(e);
            }

            Bit bit = new Bit(n);

            int i = 1;
            int j = 1;
            int sol[] = new int[q];
            while (i <= n && j <= n) {
                if (i <= j) {
                    bit.updateTree(i, a[i]);
                    i++;
                } else {
                    for (Data d : ord[j]) {
                        sol[d.index] = bit.read(j) - bit.read(d.x - 1);
                    }
                    j++;
                }
            }
            while (j <= n) {
                for (Data d : ord[j]) {
                    sol[d.index] = bit.read(j) - bit.read(d.x - 1);
                }
                j++;
            }

            for (int s : sol) {
                out.println(s);
            }
        }

    }

    static class InputReader {

        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c & 15;
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public static boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

    }

    private static class InputReader1 {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        public InputReader1(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
