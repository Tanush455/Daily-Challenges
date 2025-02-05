class Solution {
    class DisjointSet {
        ArrayList<Integer> parent = new ArrayList<>();
        ArrayList<Integer> size = new ArrayList<>();

        public DisjointSet(int n) {
            for (int i = 0; i < n; i++) {
                parent.add(i);
                size.add(1);
            }
        }

        public int findPar(int node) {
            if (node == parent.get(node)) {
                return node;
            }
            int ulp = findPar(parent.get(node));
            parent.set(node, ulp);
            return ulp;
        }

        public void unionBySize(int u, int v) {
            int ulp_u = findPar(u);
            int ulp_v = findPar(v);

            if (ulp_u == ulp_v) {
                return;
            }

            if (size.get(ulp_u) > size.get(ulp_v)) {
                parent.set(ulp_v, ulp_u);
                size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
            } else {
                parent.set(ulp_u, ulp_v);
                size.set(ulp_v, size.get(ulp_v) + size.get(ulp_u));
            }
        }
    }

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        DisjointSet ds = new DisjointSet(n * m);

        // Step 1: Union connected components of 1s
        int[] dr = { -1, 0, 1, 0 };
        int[] dc = { 0, -1, 0, 1 };

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (grid[row][col] == 0) continue;

                for (int i = 0; i < 4; i++) {
                    int newRow = row + dr[i];
                    int newCol = col + dc[i];

                    if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && grid[newRow][newCol] == 1) {
                        int node1 = row * m + col;
                        int node2 = newRow * m + newCol;
                        ds.unionBySize(node1, node2);
                    }
                }
            }
        }

        // Step 2: Check for the largest island by flipping a single 0
        int maxIsland = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (grid[row][col] == 0) {
                    Set<Integer> uniqueParents = new HashSet<>();

                    for (int i = 0; i < 4; i++) {
                        int newRow = row + dr[i];
                        int newCol = col + dc[i];

                        if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && grid[newRow][newCol] == 1) {
                            uniqueParents.add(ds.findPar(newRow * m + newCol));
                        }
                    }

                    int newSize = 1; // Flipping the current 0 to 1
                    for (int parent : uniqueParents) {
                        newSize += ds.size.get(parent);
                    }
                    maxIsland = Math.max(maxIsland, newSize);
                }
            }
        }

        // Step 3: If no 0 exists or grid is fully connected, check max size of connected components
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (grid[row][col] == 1) {
                    maxIsland = Math.max(maxIsland, ds.size.get(ds.findPar(row * m + col)));
                }
            }
        }

        return maxIsland;
    }
}
