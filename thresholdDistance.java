
    import java.util.Arrays;

    public class thresholdDistance {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], 10001); // Use 10001 as infinity (constraint: distanceThreshold <= 10^4)
            dist[i][i] = 0;
        }
        // Build initial graph
        for (int[] edge : edges) {
            dist[edge[0]][edge[1]] = edge[2];
            dist[edge[1]][edge[0]] = edge[2];
        }

        // Floyd-Warshall algorithm
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        int minReachableCities = n;
        int result = -1;

        // Find the city with the smallest number of reachable cities
        for (int i = 0; i < n; i++) {
            int reachableCities = 0;
            for (int j = 0; j < n; j++) {
                if (dist[i][j] <= distanceThreshold) {
                    reachableCities++;
                }
            }
            if (reachableCities <= minReachableCities) {
                minReachableCities = reachableCities;
                result = i;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        thresholdDistance solution = new thresholdDistance();
        int n = 4;
        int[][] edges = {
            {0, 1, 3},
            {1, 2, 1},
            {1, 3, 4},
            {2, 3, 1}
        };
        int distanceThreshold = 4;

        int result = solution.findTheCity(n, edges, distanceThreshold);
        System.out.println("The city with the smallest number of reachable cities is: " + result);
    }

}
